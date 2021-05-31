pipeline {
    agent any

    stages{
        stage('test') {
            steps{
                bat ("gradlew test")
            }
        }
        stage('build') {
            steps{
                bat ("gradlew clean build")
            }
        }
        stage('artifactoryPublish') {
                    steps{
                        bat ("gradlew artifactoryPublish")
                    }
                }
    }
}

