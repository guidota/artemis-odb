plugins {
    id("maven-publish")
    id("java")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url =
                uri("https://maven.pkg.github.com/${System.getenv("GITHUB_REPOSITORY")}")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register("gpr", MavenPublication::class) {
            from(components["java"])
        }
    }
}