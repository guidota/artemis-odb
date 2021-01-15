/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("com.artemis.java-conventions")
    id("com.artemis.github-conventions")
}

dependencies {
    implementation("org.ow2.asm:asm:8.0.1")
    implementation("org.ow2.asm:asm-tree:8.0.1")
    implementation("org.ow2.asm:asm-util:8.0.1")
    implementation("org.ow2.asm:asm-analysis:8.0.1")
    implementation("org.ow2.asm:asm-commons:8.0.1")
    implementation(project(":artemis-odb"))
}

description = "artemis-odb-component-weaver"
