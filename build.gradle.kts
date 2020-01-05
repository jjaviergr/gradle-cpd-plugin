plugins {
    groovy
    jacoco
    id("com.github.kt3k.coveralls") version "2.6.3"

    `java-gradle-plugin`
    id("io.freefair.maven-jars") version "2.9.5" // <- can be replace with java { with*Jar() } since Gradle 6.0

    maven
    `maven-publish`
    signing
    id("com.gradle.plugin-publish") version "0.10.1"
    id("com.jfrog.bintray") version "1.8.4"
}

description = "Gradle plugin to find duplicate code using PMDs copy/paste detection (= CPD)"
group = "de.aaschmid"
version = "3.2-SNAPSHOT"

val isBuildOnJenkins by extra(System.getenv("BUILD_TAG")?.startsWith("jenkins-") ?: false)

apply(from = "legacy-build.gradle")
