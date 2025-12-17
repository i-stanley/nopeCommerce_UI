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
          // Jenkins-installed Gradle
          def gradleHome = tool 'gradle-8.8'

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

          if (env.JENKINS_URL) {
            echo "Running on Jenkins → using Jenkins credentials"

            withCredentials([
              usernamePassword(
                credentialsId: 'nopcommerce-creds',
                usernameVariable: 'TEST_USER',
                passwordVariable: 'TEST_PASS'
              )
            ]) {
              // Use env vars to avoid secret interpolation warning
              withEnv([
                "USER=${TEST_USER}",
                "PASS=${TEST_PASS}"
              ]) {
                if (isUnix()) {
                  sh "${gradleHome}/bin/gradle ${args.join(' ')} -Duser=\$USER -Dpass=\$PASS"
                } else {
                  bat "\"${gradleHome}\\bin\\gradle.bat\" ${args.join(' ')} -Duser=%USER% -Dpass=%PASS%"
                }
              }
            }

          } else {
            echo "Running locally → using test.properties"

            if (isUnix()) {
              sh "${gradleHome}/bin/gradle ${args.join(' ')}"
            } else {
              bat "\"${gradleHome}\\bin\\gradle.bat\" ${args.join(' ')}"
            }
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
