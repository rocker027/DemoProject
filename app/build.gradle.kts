plugins {
    id("com.android.application")
    commonPlugins.forEach { id(it) }
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp").version("1.6.10-1.0.4")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdk = ConfigExtensions.compileSdkVersion
    buildToolsVersion = ConfigExtensions.buildToolsVersion

    defaultConfig {
        applicationId = "com.coors.demoproject"
        minSdk = ConfigExtensions.minSdkVersion
        targetSdk = ConfigExtensions.targetSdkVersion
        versionCode = ConfigExtensions.versionCode
        versionName = ConfigExtensions.versionName

        // 1) Make sure to use the AndroidJUnitRunner, or a subclass of it. This requires a dependency on androidx.test:runner, too!
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // 2) Connect JUnit 5 to the runner
        testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

//    defaultConfig{
//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments += ["room.schemaLocation": "$projectDir/schemas".toString()]
//            }
//        }
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    packagingOptions {
        exclude("META-INF/*")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeUi
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

importCommonDependencies()
importHiltDependencies()
importKotlinCoroutines()
importTestDependencies()
importThirdPartyDependencies()

dependencies {
    implementation(project(":common"))
}