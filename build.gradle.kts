// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath(Dependencies.BuildPlugins.androidGradle)
        classpath(Dependencies.BuildPlugins.kotlinGradle)
        classpath(Dependencies.BuildPlugins.hiltGradle)
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
    allprojects {
        repositories {
            google()
            mavenCentral()
            maven(url = "https://jitpack.io")
        }
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}