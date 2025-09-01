pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.9'  
        jdk 'JDK17'    
    }

    environment {
        TOMCAT_USER = 'rohan'
        TOMCAT_PASS = 'pass@1234'
        TOMCAT_URL  = 'http://localhost:8080/manager/text'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/rohanrode02/pipeline-maven-deploy.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
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
            echo "✅ Build + Test + Deploy Successful!"
        }
        failure {
            echo "❌ Pipeline Failed!"
        }
    }
}