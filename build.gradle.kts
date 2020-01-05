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

repositories {
    mavenCentral()
}

sourceSets {
    register("integTest") {
        compileClasspath += main.get().output + test.get().output
        runtimeClasspath += main.get().output + test.get().output
    }
}

dependencies {
    compileOnly("net.sourceforge.pmd:pmd-dist:6.10.0")

    testImplementation("net.sourceforge.pmd:pmd-dist:6.10.0")
    testImplementation("com.google.guava:guava:28.1-jre")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("org.assertj:assertj-core:3.13.2")
    testImplementation("org.mockito:mockito-core:3.1.0")
    testImplementation("org.mockito:mockito-junit-jupiter:3.1.0")

    "integTestImplementation"("org.assertj:assertj-core:3.13.2")
    "integTestImplementation"("org.junit.vintage:junit-vintage-engine:5.5.2")
    "integTestImplementation"("org.spockframework:spock-core:1.3-groovy-2.4") {
        exclude(module = "groovy-all")
    }
}

tasks {
    named<Javadoc>("javadoc") {
        if (JavaVersion.current().isJava9Compatible) {
            (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
        }
    }
}

apply(from = "legacy-build.gradle")
