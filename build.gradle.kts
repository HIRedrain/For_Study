plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "For_Study"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//	testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
//	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	doFirst {
//		println("📦 테스트 클래스 경로: ${classpath.asPath}")
		println("🧩 테스트 대상 class 파일 목록 :")
		fileTree("build/classes/kotlin/test").forEach {
			if (it.name.endsWith(".class")) println(" - ${it}")
		}
	}

	testLogging {
		events("passed", "failed", "skipped", "standardOut", "standardError")
		showStandardStreams = true
		exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
	}
}

sourceSets {
	test {
		kotlin.srcDirs("src/test/kotlin")
	}
}