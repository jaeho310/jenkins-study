pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh ./mvnw clean install
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