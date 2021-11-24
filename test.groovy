// import org.jenkinsci.plugins.docker.workflow.*
// import org.jenkinsci.plugins.workflow.cps.CpsScript

class Test {
    static void main(String[] args) {

        def out = new ByteArrayOutputStream(4096)
        def err = new ByteArrayOutputStream(4096)
        def proc =  "git diff HEAD HEAD^ abcd".execute()
        proc.consumeProcessOutput(out, err)
        proc.waitFor()
        def stdOut = out.toString()
        def errOut = err.toString()
        if (stdOut) {
            println("there are dependencies change")
            // new docker build and push to harbor
        } else if (errOut) {
            println("there are some errors to execute git command")
            println(errOut)
            // go.mod, go.sum이 없는경우에도 이 스코프로 들어온다.
            // 다시 빌드하는게 맞을까?
        } else {
            println("there are no dependencies change")
        }
        out.close()
        err.close()
        println('finish')
    }
    // static def buildBaseImage() {
    //     def docker sc
    //     def dockerfile = 'dockerfile.buildbase'
    //     def dockerImage = docker.build("jaeho:1", "-f ${dockerfile} .")
    //     print(dockerImage.getClass())
    // }
}