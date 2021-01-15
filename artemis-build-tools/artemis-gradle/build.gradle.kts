plugins {
    id("com.artemis.java-conventions")
    id("com.artemis.github-conventions")
    id("java-gradle-plugin")
}

dependencies {
    implementation(project(":artemis-odb-weaver"))
}

description = "artemis-odb-gradle-plugin"
