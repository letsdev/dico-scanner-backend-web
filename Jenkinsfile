#!/usr/bin/env groovy

pipeline {
    agent {
        label 'docker'
    }
    options {
        timestamps()
        ansiColor('xterm')
        buildDiscarder logRotator(numToKeepStr: '20')
    }
    stages {
        stage('Build & Test') {
            agent {
                dockerfile {
                    label 'docker'
                    reuseNode true
                    //noinspection GroovyAssignabilityCheck
                    args "--network=host -v /var/lib/jenkins/.m2:/home/jenkins/.m2"
                }
            }
            steps {
                sh "mvn clean install"
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar',
                            fingerprint: true,
                            onlyIfSuccessful: true,
                            allowEmptyArchive: true
                }
            }
        }
        stage('Deploy') {
            when {
                branch 'deploy'
            }
            environment {
                IMAGE_NAME = 'letsdev/dico'
                IMAGE_VERSION = ''
                TARGET_HOST = '172.27.27.12'
            }
            steps {
                script {
                    sh "cp target/*.jar container/"

                    def pom = readMavenPom()
                    env.IMAGE_VERSION = pom.getVersion()

                    dir('container') {
                        // build image
                        def image = docker.build(env.IMAGE_NAME)
                        image.tag(env.IMAGE_VERSION)

                        // deploy image
                        GString tarFile = "dico-${env.IMAGE_VERSION}.tar"
                        sh "docker image save -o ${tarFile} ${env.IMAGE_NAME}:${env.IMAGE_VERSION}"
                        sh "scp -o StrictHostKeyChecking=no ${tarFile} start@${env.TARGET_HOST}:/tmp/${tarFile}"
                        sh "ssh -o StrictHostKeyChecking=no start@${env.TARGET_HOST} \"docker image load -i " +
                                "/tmp/${tarFile}\""
                        sh "ssh -o StrictHostKeyChecking=no start@${env.TARGET_HOST} \"rm /tmp/${tarFile}\""

                        // update docker-compose.yml
                        String file = 'docker-compose.yml'
                        replaceWord(file: file, key: '_VERSION_', value: env.IMAGE_VERSION)

                        // deploy docker-compose.yml
                        GString hostComposeFile = "/home/start/dico/dico-${env.IMAGE_VERSION}.yml"
                        sh "scp -o StrictHostKeyChecking=no ${file} start@${env.TARGET_HOST}:${hostComposeFile}"

                        // start container
                        sh "ssh -o StrictHostKeyChecking=no start@${env.TARGET_HOST} " +
                                "\"docker-compose -f ${hostComposeFile} up -d --force-recreate --build\""
                    }
                }
            }
        }
    }
}
