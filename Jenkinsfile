
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

    stage("Lint Analysis"){
    sh 'rm -r artifacts/'
    sh 'rm -r report/'
    sh 'mkdir artifacts'
    sh 'mkdir report'
    sh 'mkdir report/test-results'
    sh './gradlew lint'
    sh 'cp app/build/reports/lint-results.xml report/'
    }

}
