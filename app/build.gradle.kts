plugins {
    id("com.android.application")
//    id("com.android.library")
    commonPlugins.forEach { id(it) }
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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

importCommonDependencies()
importHiltDependencies()

dependencies {


}