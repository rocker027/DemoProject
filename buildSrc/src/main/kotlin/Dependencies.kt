import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.exclude
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate

object Dependencies {
    /**
     * To define plugins
     */
    object BuildPlugins {
        val hiltGradle by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }
        val androidGradle by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
        val kotlinGradle by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    }

    /**
     * To define dependencies
     */
    object Deps {
        val androidJunit5Runner by lazy { "de.mannodermaus.junit5:android-test-runner:${Versions.androidJunit5}" }
        val androidJunit5Core by lazy { "de.mannodermaus.junit5:android-test-core:${Versions.androidJunit5}" }
        val junit5Engine by lazy { "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}" }
        val junit5Jupiter by lazy { "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}" }
        val koTestProperty by lazy { "io.kotest:kotest-property:${Versions.kotest}" }
        val koTestAssertions by lazy { "io.kotest:kotest-assertions-core:${Versions.kotest}" }
        val koTestRunner by lazy { "io.kotest:kotest-runner-junit5:${Versions.kotest}" }
        val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}" }
        val coliCompose by lazy { "io.coil-kt:coil-compose:${Versions.coliCompose}" }
        val composeUnitTest by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.composeUi}" }
        val composeUiLiveData by lazy { "androidx.compose.runtime:runtime-livedata:${Versions.composeUi}" }
        val composeMaterialDesignIconExtended by lazy { "androidx.compose.material:material-icons-extended:${Versions.composeUi}" }
        val composeMaterialDesignIcon by lazy { "androidx.compose.material:material-icons-core:${Versions.composeUi}" }
        val composeMaterialDesign by lazy { "androidx.compose.material:material:${Versions.composeUi}" }
        val composeFoundation by lazy { "androidx.compose.foundation:foundation:${Versions.composeUi}" }
        val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.composeUi}" }
        val composeUi by lazy { "androidx.compose.ui:ui:${Versions.composeUi}" }
        val composeConstraintLayout by lazy { "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}" }

        val androidTestCoreKtx by lazy { "androidx.test:core-ktx:${Versions.androidTest}" }
        val androidTestCore by lazy { "androidx.test:core:${Versions.androidTest}" }
        val androidRules by lazy { "androidx.test:rules:${Versions.androidRules}" }
        val androidRunner by lazy { "androidx.test:runner:${Versions.androidRunner}" }
        val androidJunitKtx by lazy { "androidx.test.ext:junit-ktx:${Versions.androidJunit}" }

        val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
        val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

        val kotlinCoroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}" }
        val kotlinCoroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}" }
        val kotlinCoroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}" }
        val kotlinDateTime by lazy { "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinDateTime}" }
        val androidCoreKtx by lazy { "androidx.core:core-ktx:${Versions.androidxCoreKtx}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
        val activityKtx by lazy { "androidx.activity:activity-ktx:${Versions.activityKtx}" }
        val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }
        val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
        val squareupLogcat by lazy { "com.squareup.logcat:logcat:${Versions.squareupLogcat}" }
        val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}" }
        val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
        val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }

        val navigationFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
        val navigationUiKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }

        // lifecycle
        val lifecycleKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}" }
        val lifecycleViewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        val lifecycleLiveDataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        val lifecycleSaveState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
        val lifecycleProcess =
            "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
        val lifecycleReactLiveStreamsKtx =
            "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycle}"

        val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
        val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"

        val archCore = "androidx.arch.core:core-testing:${Versions.archCore}"
        val truth = "com.google.truth:truth:${Versions.truth}"
        val truthExt = "androidx.test.ext:truth:${Versions.truthExt}"
        val mockkCore = "io.mockk:mockk:${Versions.mockk}"
        val mockkJvm by lazy { "io.mockk:mockk-agent-jvm:${Versions.mockk}" }
        val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
        val junit by lazy { "junit:junit:${Versions.jUnit}" }
        val androidJunit by lazy { "androidx.test.ext:junit:${Versions.androidJunit}" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
        val androidLegacy by lazy { "androidx.legacy:legacy-support-v4:${Versions.androidLegacy}" }

    }
}

val commonPlugins = arrayOf(
    "kotlin-android",
    "kotlin-parcelize",
    "kotlin-kapt"
)

fun Project.importTestDependencies() {
    dependencies {
        val testImplementation by configurations
        val androidTestImplementation by configurations
        val testRuntimeOnly by configurations
        val androidTestRuntimeOnly by configurations

        /**
         * @link https://developer.android.com/training/testing/instrumented-tests/androidx-test-libraries/test-setup
         */

        // Core library
        androidTestImplementation(Dependencies.Deps.androidTestCore)
        androidTestImplementation(Dependencies.Deps.androidTestCoreKtx)

        // AndroidJUnitRunner and JUnit Rules
        androidTestImplementation(Dependencies.Deps.androidRunner)
        androidTestImplementation(Dependencies.Deps.androidRules)

        // Assertions
        androidTestImplementation(Dependencies.Deps.androidJunit)
        androidTestImplementation(Dependencies.Deps.androidJunitKtx)
        androidTestImplementation(Dependencies.Deps.truth)
        androidTestImplementation(Dependencies.Deps.truthExt)
        androidTestImplementation(Dependencies.Deps.archCore)

        androidTestImplementation(Dependencies.Deps.kotlinCoroutinesTest)

        testImplementation(Dependencies.Deps.junit)
        testImplementation(Dependencies.Deps.mockkCore)
        testImplementation(Dependencies.Deps.mockkJvm)
        androidTestImplementation(Dependencies.Deps.mockkJvm)
        androidTestImplementation(Dependencies.Deps.mockkAndroid)
        androidTestImplementation(Dependencies.Deps.androidJunit)
        androidTestImplementation(Dependencies.Deps.espresso)

        // compose UI Tests
        androidTestImplementation(Dependencies.Deps.composeUnitTest)

        // koTest
        testImplementation(Dependencies.Deps.koTestRunner)
        testImplementation(Dependencies.Deps.koTestAssertions)
        testImplementation(Dependencies.Deps.koTestProperty)
        androidTestImplementation(Dependencies.Deps.koTestRunner)
        androidTestImplementation(Dependencies.Deps.koTestAssertions)
        androidTestImplementation(Dependencies.Deps.koTestProperty)

        // junit 5
        testImplementation(Dependencies.Deps.junit5Jupiter)
        testRuntimeOnly(Dependencies.Deps.junit5Engine)
        androidTestImplementation(Dependencies.Deps.androidJunit5Core)
        androidTestRuntimeOnly(Dependencies.Deps.androidJunit5Runner)
    }

    // fix mockk 1.12.4 issue
    allprojects {
        afterEvaluate {
            configurations.findByName("androidTestImplementation")?.run {
                exclude(group = "io.mockk", module = "mockk-agent-jvm")
            }
        }
    }
}

fun Project.importCommonDependencies() {
    dependencies {
        val implementation by configurations

        implementation(Dependencies.Deps.androidCoreKtx)
        implementation(Dependencies.Deps.appCompat)
        implementation(Dependencies.Deps.materialDesign)
        implementation(Dependencies.Deps.constraintLayout)
        implementation(Dependencies.Deps.activityKtx)
        implementation(Dependencies.Deps.fragmentKtx)
        implementation(Dependencies.Deps.navigationFragmentKtx)
        implementation(Dependencies.Deps.navigationUiKtx)
        implementation(Dependencies.Deps.androidLegacy)
        // lifecycle
        implementation(Dependencies.Deps.lifecycleKtx)
        implementation(Dependencies.Deps.lifecycleViewModelKtx)
        implementation(Dependencies.Deps.lifecycleSaveState)
        implementation(Dependencies.Deps.lifecycleLiveDataKtx)
        implementation(Dependencies.Deps.lifecycleProcess)
        implementation(Dependencies.Deps.lifecycleReactLiveStreamsKtx)

        // compose
        implementation(Dependencies.Deps.composeUi)
        implementation(Dependencies.Deps.composeConstraintLayout)
        // Tooling support (Previews, etc.)
        implementation(Dependencies.Deps.composeUiTooling)
        // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
        implementation(Dependencies.Deps.composeFoundation)
        // Integration with activities
        implementation("androidx.activity:activity-compose:${Versions.activityCompose}")
        // Animations
        implementation("androidx.compose.animation:animation:${Versions.composeUi}")
        // Integration with ViewModels
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelCompose}")
        // Material Design
        implementation(Dependencies.Deps.composeMaterialDesign)
        // Material design icons
        implementation(Dependencies.Deps.composeMaterialDesignIcon)
        implementation(Dependencies.Deps.composeMaterialDesignIconExtended)
        // Integration with observables
        implementation(Dependencies.Deps.composeUiLiveData)
//        implementation("androidx.compose.runtime:runtime-rxjava2:${Versions.composeUi}")
        implementation(Dependencies.Deps.coliCompose)
        implementation(Dependencies.Deps.hiltNavigationCompose)
        implementation(Dependencies.Deps.composeConstraintLayout)
    }
}

fun Project.importHiltDependencies() {
    dependencies {
        val implementation by configurations
        val kapt by configurations

        implementation(Dependencies.Deps.hiltAndroid)
        kapt(Dependencies.Deps.hiltAndroidCompiler)
    }
}

fun Project.importThirdPartyDependencies() {
    dependencies {
        val implementation by configurations
        val ksp by configurations

//        implementation(Dependencies.Deps.timber)
        implementation(Dependencies.Deps.squareupLogcat)
        implementation(Dependencies.Deps.moshiKotlin)
        ksp(Dependencies.Deps.moshiCodegen)
        implementation(Dependencies.Deps.moshiAdapters)
    }
}

fun Project.importKotlinCoroutines() {
    dependencies {
        val implementation by configurations

        implementation(Dependencies.Deps.kotlinCoroutinesCore)
        implementation(Dependencies.Deps.kotlinCoroutinesAndroid)
    }
}