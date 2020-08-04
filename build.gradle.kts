plugins {
    java
    application
    id("org.openjfx.javafxplugin") version("0.0.9")
}

group = "de.articdive"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

application {
    applicationDefaultJvmArgs = listOf("-Dgreeting.language=en")
    mainClassName = "de.articdive.sortingvisualizer.SortingVisualizer"
}

javafx {
    version = "11.0.2"
    modules("javafx.controls")
}

dependencies {
    // Jetbrains annotations
    compileOnly("org.jetbrains:annotations:19.0.0")
    // JUnit testing framework
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
