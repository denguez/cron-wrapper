plugins {
    kotlin("jvm") version "1.5.0"
    `maven-publish`
    `java-library`
}

allprojects {
    repositories {
        maven("https://jitpack.io")
    }
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    // Cron
    implementation("it.justwrote:kjob-core:0.2.0")
    implementation("it.justwrote:kjob-mongo:0.2.0")
    implementation("it.justwrote:kjob-kron:0.2.0")

    api("com.cronutils:cron-utils:9.1.5")
    
    // Kotlin
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))
}

publishing {
    publications {
        val mavenJava by creating(MavenPublication::class) {
            groupId = "com.github.denguez"
            artifactId = "reify.cron"
            version = "1.0"
            from(components["java"])
        }
    }
}