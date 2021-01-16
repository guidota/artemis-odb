# artemis-odb

This is a fork of [Artemis Odb](https://github.com/junkdog/artemis-odb) with custom classes generated and uses gradle as build automation tool.

## Development 

In order to setup this project, is recommended to use IntelliJ and any JDK 11+

### Publish to Github Packages

Every push on `main` branch will trigger publish task to Github Packages, so no configuration is required.

While developing new plugins/features, snapshot is recommended and only use fixed version for releases.

After a release, a bump version is required in order to continue with development.

## How to use artemis plugins

### Using Gradle

Add this repository to your configuration and provide a token (Personal Access Token with `read:packages` permission should be fine).

```
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/guidota/artemis-odb")
        credentials {
            username = "token"
            password = "<your token here>"
        }
    }
}
```

Creating a token for this purpose should be safe to make it public if `read:packages` is the only permission, so consider doing that following this [guide](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token)
