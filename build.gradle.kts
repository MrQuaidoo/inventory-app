plugins {
    id("java")
    id("org.springframework.boot") version "3.1.11"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.inventory"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Core Spring Boot dependencies
    implementation("org.springframework.boot:spring-boot-starter-mail:3.2.4")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.3.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Security
//    implementation ("org.springframework.boot:spring-boot-starter-security")
//    implementation("org.springframework.security:spring-security-core:6.4.2")
//    implementation("org.springframework.security:spring-security-config:6.4.2")
//    implementation("org.springframework.security:spring-security-web:6.4.2")


    // H2 Database for in-memory data
    implementation("com.h2database:h2")

    // Lombok for boilerplate reduction
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.3")
    implementation("org.springframework.boot:spring-boot-starter-logging")

    // Testing dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")


    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}

tasks.withType<Test> {
    useJUnitPlatform()
}