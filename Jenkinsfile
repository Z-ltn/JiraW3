pipeline {
    agent any
    environment {
        USER1 = credentials('user1')
        USER2 = credentials('user2')
        USER3 = credentials('user3')
        USER4 = credentials('user4')
        GRID_USER = credentials('gridUser')
        PASSWORD = credentials('password')
    }
    parameters {
        string(name: 'browser', defaultValue: 'chrome', description: '')
        string(name: 'baseURL', defaultValue: 'https://jira-auto.codecool.metastage.net/', description: '')
        string(name: 'timeout', defaultValue: '30', description: '')
    }
    stages {
        stage('Build') {
            agent any
            steps {
                echo 'Building...'
                sh "java -version"
                cleanWs()
            }
        }
        stage('Test') {
            agent any
            steps {
                echo 'Testing...'
                sh "mvn test -Dbrowser=${params.browser} -DbaseURL=${params.baseURL} -DgridUser=${GRID_USER} -Dpassword=${PASSWORD} -Dtimeout=${params.timeout} -Duser1=${USER1} -Duser2=${USER2} -Duser3=${USER3} -Duser4=${USER4}"
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage('Clean') {
            agent any
            steps {
                echo 'Cleaning...'
                cleanWs()
            }
        }
    }
}
