pipeline {
    stages {
        stage('Master') {

            steps {
                when {
                branch 'test'
            }
                git 'https://github.com/codeforreference/MultibranchPipeline.git'
            }
        }
    }
}