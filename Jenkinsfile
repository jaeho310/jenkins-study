pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat './mvnw clean install'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'hello~~~~~'
            }
        }
    }
}