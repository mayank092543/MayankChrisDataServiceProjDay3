package com.bah.msd.mcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MayankchrisdataserviceprojectDay3Application {
	//comment
	public static void main(String[] args) {
		SpringApplication.run(MayankchrisdataserviceprojectDay3Application.class, args);
	}

}
/*
 * node { stage('Checkout') { git branch: 'main', url:
 * '/home/osboxes/git/MayankChrisDataServiceProjDay3' } stage('Gradle build') {
 * sh 'gradle -b /home/osboxes/git/MayankChrisDataServiceProjDay3/build.gradle
 * build' } }
 */