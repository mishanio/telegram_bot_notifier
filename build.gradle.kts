plugins {
	java
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
}

group = "com.michael"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
//	Spring
	implementation("org.springframework.boot:spring-boot-starter")

	implementation("com.squareup.okhttp3:okhttp:4.11.0")
	implementation("org.telegram:telegrambots:6.7.0")


//	Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}