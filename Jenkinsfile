pipeline {
  //Donde se va a ejecutar el Pipeline
  agent {
    label 'Slave_Induccion'
  }

  options {
      	buildDiscarder(logRotator(numToKeepStr: '3'))
   	disableConcurrentBuilds()
    }


	tools {
    jdk 'JDK11_Centos' //Preinstalada en la Configuración del Master
    //gradle 'Gradle5.6_Centos' //Preinstalada en la Configuración del   Master
  }

	//Aquí comienzan los “items” del Pipeline
  stages{
    stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
		checkout([
			$class: 'GitSCM',
			branches: [[name: '*/master']],
			doGenerateSubmoduleConfigurations: false,
			extensions: [],
			gitTool: 'Default',
			submoduleCfg: [],
			userRemoteConfigs: [[
			credentialsId: 'GitHub_diegoferose',
			url:'https://github.com/diegoferose/reservaTennis'
			]]
		])
      }
    }

    stage('Compile & Unit Tests') {
      steps{
        echo "------------>Unit Tests<------------"
		sh 'gradle --b ./microservicio/build.gradle clean'

		echo "------------>Unit Test<------------"
		sh 'gradle --b ./microservicio/build.gradle test jacocoTestReport'

      }
    }

    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'
        withSonarQubeEnv('Sonar') {
		sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
        }
      }
    }

    stage('Build') {
      steps {
        echo "------------>Build<------------"

		sh 'gradle --b ./microservicio/build.gradle build -x test'
      }
    }
  }

  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
    }
    failure {
      echo 'This will run only if failed'
    }

    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}