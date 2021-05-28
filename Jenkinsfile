pipeline {
    agent any

    stages {
        stage('test') {
            steps {
                    sh ("./gradlew test")
            }
        }
        stage('build') {
            steps {
                    sh ("./gradlew clean build")
                }
        }
        stage('fatJar') {
            steps {
                sh ("./gradlew fatJar")
            }
        }
    }
}