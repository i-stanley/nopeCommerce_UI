pipeline {
  agent any

  parameters {
    booleanParam(name: 'HEADLESS', defaultValue: true, description: '')
    string(name: 'BROWSER', defaultValue: 'chrome', description: '')
    string(name: 'REMOTE', defaultValue: '', description: '')
  }

  options {
    timestamps()
  }

  stages {
    stage('Build & Test') {
      steps {
        script {
          def gradleCmd = isUnix() ? './gradlew' : 'gradlew.bat'

          def args = [
            'clean',
            'test',
            '--no-daemon',
            "-Dheadless=${params.HEADLESS}",
            "-Dbrowser=${params.BROWSER}"
          ]

          if (params.REMOTE?.trim()) {
            args << "-Dremote=${params.REMOTE.trim()}"
          }

          // ===== CI vs LOCAL credentials logic =====
          if (env.JENKINS_URL) {
            echo "Running on Jenkins → using Jenkins credentials"

            withCredentials([
              usernamePassword(
                credentialsId: 'nopcommerce-creds',
                usernameVariable: 'TEST_USER',
                passwordVariable: 'TEST_PASS'
              )
            ]) {
              args << "-Duser=${TEST_USER}"
              args << "-Dpass=${TEST_PASS}"

              runGradle(gradleCmd, args)
            }

          } else {
            echo "Running locally → using credentials.properties"
            runGradle(gradleCmd, args)
          }
        }
      }
    }
  }

  post {
    always {
      junit allowEmptyResults: true,
            testResults: 'build/test-results/test/*.xml'

      archiveArtifacts allowEmptyArchive: true,
            artifacts: 'build/reports/tests/**/*, build/allure-results/**/*',
            fingerprint: true

      allure(
        includeProperties: false,
        jdk: '',
        results: [[path: 'build/allure-results']]
      )
    }
  }
}

// ===== helper =====
def runGradle(cmd, args) {
  if (isUnix()) {
    sh 'chmod +x gradlew'
    sh "${cmd} ${args.join(' ')}"
  } else {
    bat "${cmd} ${args.join(' ')}"
  }
}
