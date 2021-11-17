pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
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