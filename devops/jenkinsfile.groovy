node {
    environment {
        PRODUCT_NAME = 'JAGUAR'
        GIT_ACCESS_KEY = credentials('86a5c3b2-7d10-4e8e-b6bb-2c166a430af6')  // credentials name that has private key of server
    }
    try {
        stage('Clone Repository') {
            checkout scm
        }
        stage('Base Build Image Check') {
            def dockerfile = 'docker/'
        }
    }
}