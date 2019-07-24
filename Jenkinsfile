
pipeline{

  agent any


stages{
  stage ("Prepare"){
    steps{
        sh 'chmod +x ./gradlew'
    }
  }

    stage("Lint Analysis"){
       steps{
        sh 'rm -r report/'
        sh 'mkdir report'
        sh 'mkdir report/test-results'
        sh './gradlew lint'
        sh 'cp app/build/reports/lint-results.xml report/'
        }
    }

    stage('Unit Test'){
        steps{
            sh './gradlew testDevelopmentDebugUnitTest'
        }
    }

     stage("Sonarqube Analysis"){
            steps{
            withSonarQubeEnv(){
              sh './gradlew sonarqube' // builds app/build/outputs/apk/app-debug.apk
              }

              script{
                               waitForQualityGate abortPipeline: true
            }
            }
            }




     stage("Build"){
        steps{
          sh './gradlew clean assembleDebug' // builds app/build/outputs/apk/app-debug.apk
          }
        }

    stage('Archive') {
           steps{
          archiveArtifacts 'app/build/outputs/apk/development/debug/*'
          }
    }

    }


    post {
        failure {
          // Notify developer team of the failure
          mail to: 'lajesh.dineshkumar@nagarro.com', subject: 'Build Failed', body: "Build ${env.BUILD_NUMBER} failed; ${env.BUILD_URL}"
        }

        success{
            mail to: 'lajesh.dineshkumar@nagarro.com', subject: 'Build Succeeded!', body: "Build ${env.BUILD_NUMBER} Succeded; ${env.BUILD_URL}"
        }
      }



}
