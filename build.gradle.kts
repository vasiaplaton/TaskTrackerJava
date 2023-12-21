plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.xerial:sqlite-jdbc:3.34.0")

    implementation("org.postgresql:postgresql:42.6.0")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.apache.tomcat.embed:tomcat-embed-core:9.0.73")
    implementation("org.apache.tomcat:tomcat-jasper:9.0.73")
    implementation("org.apache.tomcat:tomcat-catalina:9.0.73")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")
    annotationProcessor("javax.servlet:javax.servlet-api:4.0.1")

    implementation("org.projectlombok:lombok:1.18.28")
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    implementation("org.reflections:reflections:0.10.2")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.slf4j:slf4j-simple:2.0.9")

}

tasks.test {
    useJUnitPlatform()
}