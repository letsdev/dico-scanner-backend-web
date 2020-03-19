#!/usr/bin/env groovy

pipeline {
    agent {
        dockerfile {
            label 'docker'
            reuseNode true
            args "--network=host -v /var/lib/jenkins/.m2:/home/jenkins/.m2"
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
