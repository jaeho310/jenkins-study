pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                mvnw clean install
            }
        }
        stage('Test') {
            steps {
                mvnw test
            }
        }
        stage('Deploy') {
            steps {

            }
        }
    }
}