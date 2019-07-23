
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
    rm -r artifacts/
    rm -r report/
    mkdir artifacts
    mkdir report
    mkdir report/test-results
     sh './gradlew lint'
     # copy lint results
     if [ ! -e "app/build/reports/lint-results.xml" ]; then
         echo "ERROR: File not exists: (app/build/reports/lint-results.xml)"
         exit 1
     fi
     cp app/build/reports/lint-results.xml report/
    }

}
