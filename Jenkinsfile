pipeline {
    agent any

    parameters {
        string(
            name: 'FIBONACCI_COUNT',
            defaultValue: '10',
            description: 'Enter number of Fibonacci terms to generate. Try 10 (SUCCESS) or -5 (FAILURE CASE).'
        )
    }

    environment {
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

        stage('Execute Program with Input') {
            steps {
                echo "========================================="
                echo "Executing Fibonacci with input: ${params.FIBONACCI_COUNT}"
                echo "========================================="
                script {
                    if (isUnix()) {
                        sh "java -jar target/fibonacci-series-1.0-SNAPSHOT.jar ${params.FIBONACCI_COUNT}"
                    } else {
                        bat "java -jar target/fibonacci-series-1.0-SNAPSHOT.jar ${params.FIBONACCI_COUNT}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Archiving JUnit test result reports...'
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
        }
        success {
            echo '==================================================='
            echo 'BUILD & EXECUTION SUCCEEDED!'
            echo '==================================================='
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo '==================================================='
            echo 'BUILD / TEST FAILURE DETECTED!'
            echo 'Inspect the console output above or the "Test Result" link in the sidebar for failure details.'
            echo '==================================================='
        }
    }
}
