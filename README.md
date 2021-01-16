# artemis-odb

This is a fork of [Artemis Odb](https://github.com/junkdog/artemis-odb) with custom classes generated and uses gradle as build automation tool.

## Development 

In order to setup this project, is recommended to use IntelliJ and any JDK 11+

### Publish to Github Packages

Every push on `main` branch will trigger publish task to Github Packages, so no configuration is required.

While developing new plugins/features, snapshot is recommended and only use fixed version for releases.

After a release, a bump version is required in order to continue with development.
