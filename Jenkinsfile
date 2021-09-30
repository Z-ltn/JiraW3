pipeline {
    agent none
    environment {
        USER1 = credentials('user1')
        USER2 = credentials('user2')
        USER3 = credentials('user3')
        USER4 = credentials('user4')
        GRID_USER = credentials('gridUser')
        PASSWORD = credentials('password')
    }
    parameters {
        string(name: "browser", defaultValue: "chrome", description: '')
        string(name: "baseURL", defaultValue: "https://jira-auto.codecool.metastage.net/", description: '')
        string(name: "timeout", defaultValue: "30", description: '')
    }
    stages {
        stage('Build') {
            agent any {
                steps {
                    echo 'Building...'
                    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                    echo 'This user is ${USER1} and other is ${user1}'
                    checkout scm
                    sh 'make'
                    stash includes: '**/target/*.jar', name: 'app'
                }
            }
        }
        stage('Test on Linux') {
            agent {
                label: 'linux'
            }
                steps {
                    echo 'Testing on Linux...'
                    unstash 'app'
                    sh 'make check || true'
                    mvn test -Dbrowser=parameters.browser -DbaseURL=parameters.baseURL -DgridUser=${GRID_USER} -Dpassword=${PASSWORD} -Dtimeout=parameters.timeout -Duser1=${USER1} -Duser2=${USER2} -Duser3=${USER3} -Duser4=${USER4}
                }
        }stage('Test on Windows') {
            agent {
                label: 'windows'
            }
                steps {
                    echo 'Testing on Windows...'
                    unstash 'app'
                    sh 'make check || true'
                    mvn test -Dbrowser=parameters.browser -DbaseURL=parameters.baseURL -DgridUser=${GRID_USER} -Dpassword=${PASSWORD} -Dtimeout=parameters.timeout -Duser1=${USER1} -Duser2=${USER2} -Duser3=${USER3} -Duser4=${USER4}
                }
        }
    }
     post {
            always {
                echo 'One way or another, I have finished'
                junit '**/build/test-reports/*.xml'
                deleteDir()
            }
            success {
                echo 'I succeeded!'
            }
            unstable {
                echo 'I am unstable :/'
            }
            failure {
                echo 'I failed :('
            }
            changed {
                echo 'Things were different before...'
            }
        }
}