
node{
  stage("Checkout"){
    checkout scm
  }

  stage ("Prepare"){
    sh 'chmod +x ./gradlew'
  }

    stage("Build"){
      sh './gradlew clean assembleDebug' // builds app/build/outputs/apk/app-debug.apk
    }

}
