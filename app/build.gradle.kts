import com.coors.plugin.version.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp").version("1.6.10-1.0.4")
    id("de.mannodermaus.android-junit5")
    id("com.coors.plugin.version")
}

android {
    compileSdk = AndroidConfig.compileSdkVersion
    buildToolsVersion = AndroidConfig.buildToolsVersion

    defaultConfig {
        applicationId = "com.coors.demoproject"
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

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
        kotlinCompilerExtensionVersion = "1.1.1"
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

//importCommonDependencies()
//importHiltDependencies()
//importKotlinCoroutines()
//importTestDependencies()
//importThirdPartyDependencies()

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2022.12.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(project(":common"))
    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltAndroidCompiler)

    implementation(Libs.androidCoreKtx)
    implementation(Libs.appCompat)
    implementation(Libs.materialDesign)
    implementation(Libs.constraintLayout)
    implementation(Libs.activityKtx)
    implementation(Libs.fragmentKtx)
    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUiKtx)
    implementation(Libs.androidLegacy)
    // lifecycle
    implementation(Libs.lifecycleKtx)
    implementation(Libs.lifecycleViewModelKtx)
    implementation(Libs.lifecycleSaveState)
    implementation(Libs.lifecycleLiveDataKtx)
    implementation(Libs.lifecycleProcess)
    implementation(Libs.lifecycleReactLiveStreamsKtx)

    // compose
    implementation(Libs.composeUi)
    implementation(Libs.composeMaterial3)
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation(Libs.composeFoundation)
    debugImplementation(Libs.composeManifest)
    implementation(Libs.composePreview)
    debugImplementation(Libs.composeTooling)
    implementation(Libs.composeIconCore)
    implementation(Libs.composeIconExtended)
    implementation(Libs.composeWindowSizeClass)
    implementation(Libs.composeLiveData)
    implementation(Libs.composeLottie)

    // Integration with activities
    implementation(Libs.composeActivity)
    // Integration with ViewModels
    implementation(Libs.composeViewModel)
    implementation(Libs.coliCompose)
    implementation(Libs.hiltNavigationCompose)

    implementation(Libs.kotlinCoroutinesCore)
    implementation(Libs.kotlinCoroutinesAndroid)

    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltAndroidCompiler)

    implementation(Libs.squareUpLogcat)
    implementation(Libs.moshiKotlin)
    ksp(Libs.moshiCodegen)
    implementation(Libs.moshiAdapters)

    // Core library
    androidTestImplementation(Libs.androidTestCore)
    androidTestImplementation(Libs.androidTestCoreKtx)

    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation(Libs.androidRunner)
    androidTestImplementation(Libs.androidRules)

    // Assertions
    androidTestImplementation(Libs.androidJunit)
    androidTestImplementation(Libs.androidJunitKtx)
    androidTestImplementation(Libs.archCore)

    androidTestImplementation(Libs.kotlinCoroutinesTest)

    testImplementation(Libs.junit)
    testImplementation(Libs.mockkCore)
    testImplementation(Libs.mockkJvm)
    androidTestImplementation(Libs.mockkJvm)
    androidTestImplementation(Libs.mockkAndroid)
    androidTestImplementation(Libs.androidJunit)
    androidTestImplementation(Libs.espresso)

    // compose UI Tests
    androidTestImplementation(Libs.composeJunit4)

    // koTest
    testImplementation(Libs.koTestRunner)
    testImplementation(Libs.koTestAssertions)
    testImplementation(Libs.koTestProperty)
    androidTestImplementation(Libs.koTestRunner)
    androidTestImplementation(Libs.koTestAssertions)
    androidTestImplementation(Libs.koTestProperty)

    // junit 5
    testImplementation(Libs.junit5Jupiter)
    testRuntimeOnly(Libs.junit5Engine)
    androidTestImplementation(Libs.androidJunit5Core)
    androidTestRuntimeOnly(Libs.androidJunit5Runner)

    implementation(Libs.retrofit)
    implementation(Libs.retrofitMoshi)
    implementation(Libs.okHttpInterceptor)
}