#!/usr/bin/env groovy

pipeline {
    agent {
        dockerfile {
            label 'docker'
            reuseNode true
        }
    }
    options {
        timestamps()
        ansiColor('xterm')
        buildDiscarder logRotator(numToKeepStr: '20')
    }
    stages {
        stage('Build & Test') {
            steps {
                sh "mvn clean install"
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.jar',
                            fingerprint: true,
                            onlyIfSuccessful: true,
                            allowEmptyArchive: true
                }
            }
        }
    }
}
