include(":artemis-odb")
include(":artemis-fluid-gradle-plugin")
include(":artemis-odb-weaver")
include(":artemis-fluid-core-utility-sources")
include(":artemis-fluid-core")
include(":artemis-build-tools-root")
include(":artemis-core-root")
include(":artemis-odb-gradle-plugin")
include(":artemis-fluid-root")
include(":artemis-odb-cli")
project(":artemis-odb").projectDir = file("artemis-core/artemis")
project(":artemis-fluid-gradle-plugin").projectDir = file("artemis-fluid/artemis-fluid-gradle-plugin")
project(":artemis-odb-weaver").projectDir = file("artemis-build-tools/artemis-weaver")
project(":artemis-fluid-core-utility-sources").projectDir = file("artemis-fluid/artemis-fluid-core-utility-sources")
project(":artemis-fluid-core").projectDir = file("artemis-fluid/artemis-fluid-core")
project(":artemis-build-tools-root").projectDir = file("artemis-build-tools")
project(":artemis-core-root").projectDir = file("artemis-core")
project(":artemis-odb-gradle-plugin").projectDir = file("artemis-build-tools/artemis-gradle")
project(":artemis-fluid-root").projectDir = file("artemis-fluid")
project(":artemis-odb-cli").projectDir = file("artemis-build-tools/artemis-cli")
