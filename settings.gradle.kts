rootProject.name = "DemoProject"
include(":app")

include(":common")
project(":common").projectDir = File(rootDir ,"CommonCore/app")