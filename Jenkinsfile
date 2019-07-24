
pipeline{

  agent any


stages{
  stage("Checkout"){
    checkout scm
  }

  stage ("Prepare"){
    steps{
        sh 'chmod +x ./gradlew'
    }
  }

    stage("Lint Analysis"){
       step{
        sh 'rm -r report/'
        sh 'mkdir report'
        sh 'mkdir report/test-results'
        sh './gradlew lint'
        sh 'cp app/build/reports/lint-results.xml report/'
        }
    }

    stage('Unit Test'){
        step{
            sh './gradlew testDevelopmentDebugUnitTest'
        }
    }

     stage("Build"){
        step{
          sh './gradlew clean assembleDebug' // builds app/build/outputs/apk/app-debug.apk
          }
        }

    stage('Archive') {
           step{
          archiveArtifacts 'app/build/outputs/apk/development/debug/*'
          }
    }

    }


    post {
        failure {
          // Notify developer team of the failure
          mail to: 'lajesh.dineshkumar@nagarro.com', subject: 'Oops!', body: "Build ${env.BUILD_NUMBER} failed; ${env.BUILD_URL}"
        }
      }



}
