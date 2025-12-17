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
          def gradleCmd = tool 'gradle-8.8'

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
            ])   {
                 withEnv([
                   "USER=${TEST_USER}",
                   "PASS=${TEST_PASS}"
                 ]) {
                  args << "-Duser=%USER%"
                  args << "-Dpass=%PASS%"
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
    sh "${cmd}/bin/gradle ${args.join(' ')}"
  } else {
    bat "\"${cmd}\\bin\\gradle.bat\" ${args.join(' ')}"
  }
}


