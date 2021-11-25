node {
    environment {
        PRODUCT_NAME = 'jaeho-test'
        GIT_ACCESS_KEY = credentials('86a5c3b2-7d10-4e8e-b6bb-2c166a430af6')  // credentials name that has private key of server
    }
    try {
        stage('Clone Repository') {
            // echo ${env.WORKSPACE}
            print('clone')
            checkout scm
        }
        // 이전에 빌드를 한 적이 있고
        // 추가로 가져온 의존성이 없다면 베이스 이미지를 다시 빌드할 필요가 없다.
        // stage('Build Image Check') {
        //     def out = new ByteArrayOutputStream(4096)
        //     def err = new ByteArrayOutputStream(4096)
        //     def proc =  "git diff HEAD HEAD^ go.mod".execute()
        //     print("asdfkjsdakljsdafklsdafj;lksdaj;fklsdaj;fkljsda;f")
        //     proc.consumeProcessOutput(out, err)
        //     proc.waitFor()
        //     def stdOut = out.toString()
        //     def errOut = err.toString()

        //     if (stdOut) {
        //         print("there are dependencies change")
        //         buildBaseImage()
        //         // new docker build and push to harbor
        //     } else if (errOut) {
        //         print("there are some errors to execute git command")
        //         // go.mod, go.sum이 없는경우에도 standarderr가 나오고 이 스코프로 들어온다.
        //         // 다시 빌드하는게 맞을까?
        //         buildBaseImage()
        //     } else {
        //         print("there are no dependencies change")
        //     }
        // }
        stage('Build') {
            def dockerfile = 'dockerfile'
            def dockerImage = docker.build("jaeho-study:${env.BRANCH_NAME}", "-f ${dockerfile} .")
            // docker.build("jaeho-study-build")
            // docker.image("jaeho-study-base:${env.BRANCH_NAME}").inside("-v ${test_results_path}:/app/test-results") {
            //     sh '''/app/run-test.sh ${BRANCH_NAME}'''
            // }
        }
        stage('Test') {
            // /var/jenkins_home/workspace/jaeho-multlibranch-pipeline_RC
            print('111111111111111111111111111111111111111111111111111')
            // def job_folder = "${env.WORKSPACE}/jenkins-sutdy"
            print('2222222222222222222222222222222222222222222222222222')
            // def dockerfile = 'dockerfile.test'
            docker.image("jaeho-study:${env.BRANCH_NAME}").inside {
                sh '/app/entrypoint-test.sh'
            }
            print('333333333333333333333333333333333333333333333333333')
        }
        stage('Sonarqube') {
            print("sonarqube!!")
        }

    } catch (e) {
        echo 'fail'
        throw e

    } finally {
        def currentResult = currentBuild.result ?: 'SUCCESS'
        if (currentResult == 'UNSTABLE') {
            echo 'This will run only if the run was marked as unstable'
        }

        def previousResult = currentBuild.getPreviousBuild()?.result
        if (previousResult != null && previousResult != currentResult) {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }

        echo 'This will always run'
        stage('Clean workspace & delete Image') {
            cleanWs deleteDirs: true, notFailBuild: true
            sh "docker rmi jaeho-study:${env.BRANCH_NAME}"
        }

    }
}

void print(message) {
    echo "${message}"
}

def buildBaseImage() {
    def dockerfile = 'dockerfile.buildbase'
    def dockerImage = docker.build("jaeho-study-base:${env.BRANCH_NAME}", "-f ${dockerfile} .")
    print(dockerImage.getClass()) // 지금 얘가 뭐가나오는지 알수 없다.
    // docker.image("$IMAGE").push() // 일단 명령어는 이거
}