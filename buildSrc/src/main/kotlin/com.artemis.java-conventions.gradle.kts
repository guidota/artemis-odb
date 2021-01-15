plugins {
    `java-library`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }

    maven {
        url = uri("https://repo.spring.io/libs-release/")
    }

    maven {
        url = uri("https://repo.gradle.org/gradle/libs-releases-local")
    }
}

dependencies {
    testImplementation("junit:junit:4.11")
}

java.sourceCompatibility = JavaVersion.VERSION_11

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
