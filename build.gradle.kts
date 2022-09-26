import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "me.ashinisagaran"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//// Minimum jvmTarget of 1.8 needed since Kotlin 1.1
//compileKotlin {
//    kotlinOptions.jvmTarget= 1.8
//}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation ("io.github.microutils:kotlin-logging:2.1.23")
    implementation("com.google.code.gson:gson:2.9.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}