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
                dir('DiCoScanner') {
                    sh "./mvnw clean install"
                }
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
