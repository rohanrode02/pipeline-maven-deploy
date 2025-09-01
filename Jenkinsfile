pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.9'  // Define Maven tool installed in Jenkins
        jdk 'JDK17'    // Define JDK installed in Jenkins
    }

    environment {
        TOMCAT_USER = 'rohan'
        TOMCAT_PASS = 'pass@1234'
        TOMCAT_URL  = 'http://localhost:8091/manager/text'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/rohanrode02/pipeline-maven-deploy.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    def warFile = sh(script: "ls target/*.war", returnStdout: true).trim()
                    sh """
                        curl -u $TOMCAT_USER:$TOMCAT_PASS \
                        --upload-file $warFile \
                        "$TOMCAT_URL/deploy?path=/pipelineapp&update=true"
                    """
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build and Deploy Successful!"
        }
        failure {
            echo "❌ Build or Deploy Failed!"
        }
    }
}