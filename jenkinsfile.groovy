node {
    environment {
        PRODUCT_NAME = 'jaeho-test'
        GIT_ACCESS_KEY = credentials('86a5c3b2-7d10-4e8e-b6bb-2c166a430af6')  // credentials name that has private key of server
    }
    try {
        stage('Clone Repository') {
            echo 'clone'
            checkout scm
            def proc = "git branch".execute()
            def b = new StringBuffer()
            proc.consumeProcessErrorStream(b)

            println proc.text
            println b.toString()
        }
        // 이 빌드용 이미지가 있고 추가로 라이브러리를 넣은게 없으면 이 이미지를 그대로 쓸수있다.
        // git에서 헤드올려서 파일(pip 관련 모듈 정보)하나 diff 바뀌었으면 다시 빌드하고 이전 해시
        stage('Build Image Check') {
            echo 'build image check'
            // def dockerfile = 'dockerfile.buildbase'
            if (true) {
                // def dockerImage = docker.build("cache-service:${env.BRANCH_NAME}", "-f ${dockerfile} .")

                // 도커 푸쉬
                // git add 그파일
            }
        }
        stage('Test') {
            // def job_folder = "${env.WORKSPACE}".split('workspace/')[1]
        }
    } catch (e) {
        echo 'fail'
        throw e
    } finally {
        echo 'success'
    }
}