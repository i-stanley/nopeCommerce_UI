pipeline {
  agent any

  parameters {
    booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run browser in headless mode')
    string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser name')
    string(name: 'REMOTE', defaultValue: '', description: 'Remote WebDriver URL (optional)')
  }

  options {
    timestamps()
  }

  stages {
    stage('Build & Test') {
      steps {
        script {
          // Jenkins-installed Gradle (name from Manage Jenkins → Tools)
          def gradleHome = tool 'Gradle'

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

          echo "Running tests on Jenkins with Gradle"

          withCredentials([
            usernamePassword(
              credentialsId: 'nopcommerce-creds',
              usernameVariable: 'TEST_USER',
              passwordVariable: 'TEST_PASS'
            )
          ]) {

            if (isUnix()) {
              sh """
                "${gradleHome}/bin/gradle" ${args.join(' ')} \
                -Duser=\$TEST_USER \
                -Dpass=\$TEST_PASS
              """
            } else {
              bat """
                "${gradleHome}\\bin\\gradle.bat" ${args.join(' ')} ^
                -Duser=%TEST_USER% ^
                -Dpass=%TEST_PASS%
              """
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
