plugins {
	java
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	id("io.freefair.lombok") version "8.4"
}


allprojects {
	group = "agency.amazon"
	version = "0.0.1-SNAPSHOT"
	repositories {
		mavenCentral()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<JavaCompile> {
		sourceCompatibility = "17"
		targetCompatibility = "17"
	}

	apply(plugin = "java")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "io.freefair.lombok")

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
		implementation("org.springframework.boot:spring-boot-starter-security")
		implementation("org.springframework.boot:spring-boot-starter-web")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.springframework.security:spring-security-test")
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
		testImplementation("org.mockito:mockito-core:5.10.0")
		testImplementation("org.assertj:assertj-core:3.25.3")

	}
	tasks.bootJar {
		enabled = false
	}

}

tasks.withType<Test> {
	useJUnitPlatform()
}
