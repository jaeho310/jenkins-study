node {
    environment {
        PRODUCT_NAME = 'JAGUAR'
        GIT_ACCESS_KEY = credentials('86a5c3b2-7d10-4e8e-b6bb-2c166a430af6')  // credentials name that has private key of server
    }
    try {
        stage('Clone Repository') {
            echo 'clone'
            checkout scm
        }
        stage('Build Image Check') {
            echo 'build image check'
            def dockerfile = 'docker/dockerfile.buildbase'
            if (true) {
                def dockerImage = docker.build("cache-service:${env.BRANCH_NAME}.${env.BUILD_ID}", "-f ${dockerfile} .")
            }
        }
    } catch {
        echo 'fail'
    } finally {
        echo 'success'
    }
}