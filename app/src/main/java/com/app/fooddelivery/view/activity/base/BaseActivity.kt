package com.app.fooddelivery.view.activity.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.fooddelivery.view.listeners.BackButtonHandlerListener
import com.app.fooddelivery.view.listeners.BackPressListener
import com.app.fooddelivery.viewmodel.BaseViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import java.lang.ref.WeakReference
import java.util.*
import javax.inject.Inject

/****
 * All the activity should be extended from this parent class.
 * All the common functionalities across activities should be kept here
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
abstract class BaseActivity<V : ViewModel, D : ViewDataBinding> : AppCompatActivity(), HasSupportFragmentInjector,
    BackButtonHandlerListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    private val backClickListenersList = ArrayList<WeakReference<BackPressListener>>()

    protected var isUseCustomeViewModelFactory: Boolean = true

    protected lateinit var viewModel: V

    protected lateinit var dataBinding: D

    @get:LayoutRes
    protected abstract val layoutRes: Int

    abstract val bindingVariable: Int

    protected abstract fun getViewModel(): Class<V>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding.lifecycleOwner = this

        viewModel = if (isUseCustomeViewModelFactory) {
            ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
        } else {
            ViewModelProviders.of(this).get(getViewModel())
        }
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()

        observeBackPressAction()
    }

    /**
     * Method which listens to back button press in toolbar
     */
    private fun observeBackPressAction() {
        (viewModel as BaseViewModel).backPressAction.observe(this, Observer {
            onBackPressed()
        })
    }

    /**
     * Method which sets the title in the header
     */
    fun setTitle(title: String) {
        (viewModel as BaseViewModel).setTitle(title)
        actionBar?.title = title
    }

    /**
     * Methods which handles the hardware back button / navigation back view
     */
    override fun onBackPressed() {
        if (!fragmentsBackKeyIntercept()) {
            super.onBackPressed()
        }
    }

    /**
     * Add the back navigation listener here.
     * Call this method from onAttach of your fragment
     * @param listner - back navigation listener
     */
    override fun addBackpressListener(listner: BackPressListener) {
        backClickListenersList.add(WeakReference(listner))
    }

    /**
     * remove the back navigation listener here.
     * Call this method from onDetach of your fragment
     * @param listner - back navigation listener
     */
    override fun removeBackpressListener(listner: BackPressListener) {
        val iterator = backClickListenersList.iterator()
        while (iterator.hasNext()) {
            val weakRef = iterator.next()
            if (weakRef.get() === listner) {
                iterator.remove()
            }
        }
    }

    /**
     * This method checks if any frgament is overriding the back button behavior or not
     * @return true/false
     */
    private fun fragmentsBackKeyIntercept(): Boolean {
        var isIntercept = false
        for (weakRef in backClickListenersList) {
            val backpressListner = weakRef.get()
            if (backpressListner != null) {
                val isFragmIntercept: Boolean = backpressListner.onBackPress()
                if (!isIntercept)
                    isIntercept = isFragmIntercept
            }
        }
        return isIntercept
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}