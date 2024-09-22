plugins {
    id("java")
}

group = "org.app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.18.1")
    implementation("com.google.code.gson:gson:2.7")
    implementation("org.seleniumhq.selenium:selenium-java:2.41.0")
    implementation("io.github.bonigarcia:webdrivermanager:5.4.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
