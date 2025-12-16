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
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        script {
          def gradleCmd = isUnix() ? './gradlew' : 'gradlew.bat'
          def args = "clean test --no-daemon -Dheadless=${params.HEADLESS} -Dbrowser=${params.BROWSER}"
          if (params.REMOTE?.trim()) {
            args += " -Dremote=${params.REMOTE.trim()}"
          }
          if (isUnix()) {
            sh 'chmod +x gradlew'
            sh "${gradleCmd} ${args}"
          } else {
            bat "${gradleCmd} ${args}"
          }
        }
      }
    }
  }

  post {
    always {
      junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'

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
