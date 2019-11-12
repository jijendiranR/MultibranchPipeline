pipeline {
    stages {
        stage('Master') {

            steps {
                when {
                branch 'master'
            }
                git 'https://github.com/codeforreference/MultibranchPipeline.git'
            }
        }
    }
}
