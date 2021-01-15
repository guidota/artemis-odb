plugins {
    id("com.artemis.java-conventions")
    id("com.artemis.github-conventions")
    id("java-gradle-plugin")
}

dependencies {
    implementation(project(":artemis-odb"))
    implementation(project(":artemis-fluid-core"))
    implementation("org.gradle:gradle-tooling-api:2.1")
    implementation("org.gradle:gradle-core:2.1")
    implementation("org.codehaus.groovy:groovy-all:2.3.6")
    implementation("org.slf4j:slf4j-api:1.7.5")
    implementation(project(":artemis-odb-weaver"))
    implementation("net.onedaybeard.ecs:matrix-artemis:0.2.0")
}

description = "artemis-fluid-gradle-plugin"
