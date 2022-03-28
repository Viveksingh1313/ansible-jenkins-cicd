pipeline {
    agent any
    tools {
        maven 'mvn'
        //version 3.0.5
    }
    parameters {
        choice(
            name: 'envSelected',
            choices: ['dev', 'test', 'prod'],
            description: 'Please choose en environment where you want to run?'
        )
    }
     stages {
//       stage('git clone') {
//         steps {
//           git branch: 'dev', url: 'https://github.com/Viveksingh1313/springboot-test.git'
//         }
//       }
          stage('unit test') {
            steps {
                echo "Environment selected: ${params.envSelected}"
                sh 'mvn test -Punit-tests'
            }
            post {
                failure {
                    mail to: 'viveks@azuga.com',
                        subject: 'Dude your Azuga-RUC Pipeline failed. Check your Unit Tests',
                        body: 'Unit Test Cases Failure'
                }
            }
          }
          stage('integration test') {
            steps {
                echo "Environment selected: ${params.envSelected}"
                sh 'mvn test -Pintegration-tests'
            }
            post {
                failure {
                    mail to: 'viveks@azuga.com',
                        subject: 'Dude your Azuga-RUC Pipeline failed. Check your integration tests',
                        body: 'Integration Test Cases Failure'
                }
            }
          }
          stage('SonarQube Analysis') {
            steps {
                //def mvn = tool 'mvn';
                withSonarQubeEnv('sonar') {
                    sh "mvn sonar:sonar"
                }
    //             timeout(time: 4, unit: 'MINUTES') {
    //                 waitForQualityGate abortPipeline: true
    //             }
            }
          }
          stage('Build Jars') {
            steps {
                sh 'mvn clean package'
            }
          }
          stage('Run Spring Boot App') {
            steps {
                script {
                    if (env.envSelected == "dev" || env.envSelected == "test") {
                        echo 'triggered by dev or test'
                        ansiblePlaybook installation: 'ansible2', inventory: 'dev.inv', playbook: 'ansible.yml', disableHostKeyChecking: true
                    } else {
                        echo 'triggered by prod'
                        input "Continue Deployment to Prod ? Are you Sure ?"
                        ansiblePlaybook installation: 'ansible2', inventory: 'dev.inv', playbook: 'ansible.yml', disableHostKeyChecking: true
                        // check below code for IP ssh based deployment
                        // for different Ips
                        // IP address and role goes in dev.inv
                        /**[webservers]
                          IP-address ansible_user=ec2-user
                          **/
                        // command changes to include crendeitalsId
                        // private-key values if your jenkins configured key to connect to server IP
                        // check the screenshot you have
                        // ansiblePlaybook crendeitalsId: 'private-key', installation: 'ansible2', inventory: 'dev.inv', playbook: 'ansible.yml', disableHostKeyChecking: true
                    }
                }

            }
          }
    }
}