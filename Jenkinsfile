pipeline {
    agent any


    environment {
        // You can define environment variables here if needed
        PROJECT_NAME = 'fibonacci-series'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Verify Environment') {
            steps {
                echo 'Verifying Java and Maven versions...'
                // Handles cross-platform execution (Windows or Linux/Unix agents)
                script {
                    if (isUnix()) {
                        sh 'java -version'
                        sh 'mvn -version'
                    } else {
                        bat 'java -version'
                        bat 'mvn -version'
                    }
                }
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling source code...'
                script {
                    if (isUnix()) {
                        sh 'mvn clean compile'
                    } else {
                        bat 'mvn clean compile'
                    }
                }
            }
        }

        stage('Run Unit Tests') {
            steps {
                echo 'Running JUnit 5 test cases...'
                script {
                    if (isUnix()) {
                        sh 'mvn test'
                    } else {
                        bat 'mvn test'
                    }
                }
            }
        }

        stage('Package Application') {
            steps {
                echo 'Packaging application into JAR...'
                script {
                    if (isUnix()) {
                        sh 'mvn package -DskipTests'
                    } else {
                        bat 'mvn package -DskipTests'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Archiving JUnit test result reports...'
            // Publishes test results in Jenkins UI
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
        }
        success {
            echo 'Build and tests succeeded! Archiving artifact JAR...'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo 'Build or tests failed. Please inspect test reports and console output.'
        }
    }
}
