pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'chmod 777 mvnw'
                sh './mvnw clean install'
            }
        }
        stage('Test') {
            steps {
                bat './mvnw test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'hello~~~~~'
            }
        }
    }
}