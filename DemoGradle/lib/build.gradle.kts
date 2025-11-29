plugins {
    application
    java
}

repositories {
    mavenCentral()
}

dependencies {
    // ✅ Log4j2 dependencies
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")

    // Optional: JUnit for testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

application {
    // Replace with your main class package
    mainClass.set("com.example.LoggingExample")
}

tasks.test {
    useJUnitPlatform()
}
