package com.app.fooddelivery.view.fragment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.fooddelivery.FoodDeliveryApp
import com.app.fooddelivery.R
import com.app.fooddelivery.di.Injectable
import com.app.fooddelivery.view.listeners.BackButtonHandlerListener
import com.app.fooddelivery.view.listeners.BackPressListener
import com.app.fooddelivery.viewmodel.BaseViewModel
import com.app.fooddelivery.viewmodel.SharedViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/****
 * Parent for all the UI fragments. All the common things to be kept here
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
abstract class BaseFragment<V : ViewModel, D : ViewDataBinding> : androidx.fragment.app.Fragment(), Injectable, BackPressListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: V

    protected lateinit var dataBinding: D

    protected var isUseCustomeViewModelFactory: Boolean = true


    protected lateinit var sharedViewModel: SharedViewModel

    private var backButtonHandler: BackButtonHandlerListener? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    abstract val bindingVariable: Int

    protected abstract fun getViewModel(): Class<V>

    abstract fun getTitle(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = if (isUseCustomeViewModelFactory) {
            ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
        } else {
            ViewModelProviders.of(this).get(getViewModel())
        }

        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setSharedViewModel()

        observeResponse()

        observeLoadingStatus()

        (viewModel as BaseViewModel).serviceError.observe(this, Observer {
            it?.let {
                showError(it)
            }

        })

        (viewModel as BaseViewModel).headerTitle.value = getTitle()
    }




    /**
     * Method which indicates if the fragment has header
     * Default (true): considering all fragment has header
     */
    open fun hasHeader(): Boolean {
        return true
    }

    /**
     * Method which sets the sharedview to baseviewmodel
     */
    private fun setSharedViewModel() {
        (viewModel as BaseViewModel).sharedViewModel = sharedViewModel
    }

    /**
     * Method to override the backpress behaviour on indivitual fragment
     */
    override fun onBackPress(): Boolean {
        return false
    }

    override fun onResume() {
        super.onResume()
        backButtonHandler?.addBackpressListener(this)
    }

    override fun onPause() {
        super.onPause()
        backButtonHandler?.removeBackpressListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        backButtonHandler = context as BackButtonHandlerListener
    }

    override fun onDetach() {
        super.onDetach()
        backButtonHandler?.removeBackpressListener(this)
        backButtonHandler = null
    }

    open fun observeResponse() {
        // Implementation goes on the child fragments
    }


    open fun observeLoadingStatus() {
        // Implementation goes on the child fragments
    }

    private fun showError(message: String){
        val dialogBuilder = AlertDialog.Builder(activity!!)

        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton(FoodDeliveryApp.applicationContext().getString(R.string.proceed)) { dialog, _ -> dialog.dismiss() }

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(FoodDeliveryApp.applicationContext().getString(R.string.error))
        // show alert dialog
        alert.show()
    }

    override fun onStop() {
        super.onStop()
        activity?.let {
            (viewModel as BaseViewModel).serviceError.removeObservers(it)
            (viewModel as BaseViewModel).loadingStatus.removeObservers(it)
            (viewModel as BaseViewModel).backPressAction.removeObservers(it)
        }
    }

}