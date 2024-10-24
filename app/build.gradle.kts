import org.jetbrains.kotlin.load.kotlin.signatures
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization")
    alias(libs.plugins.kotlin.compose)

}

android {
    namespace = "com.tatweer.smartdrivingtest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tatweer.smartdrivingtest"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        val keyStoreProperties =
            getKeystoreProperties(rootProject.file("tempKeystoreProperties.properties"))
        getByName("debug") {
            keyAlias = keyStoreProperties.getProperty("keyAlias")
            keyPassword = keyStoreProperties.getProperty("keyPassword")
            storeFile = rootProject.file(keyStoreProperties.getProperty("storeFile"))
            storePassword = keyStoreProperties.getProperty("storePassword")
        }
        create("release") {
            keyAlias = keyStoreProperties.getProperty("keyAlias")
            keyPassword = keyStoreProperties.getProperty("keyPassword")
            storeFile = rootProject.file(keyStoreProperties.getProperty("storeFile"))
            storePassword = keyStoreProperties.getProperty("storePassword")
        }
    }
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packaging {
        resources {
            excludes += listOf("META-INF/INDEX.LIST", "META-INF/io.netty.versions.properties")
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Kotlin
    implementation(libs.kotlinx.serialization.json)

    // Google
    implementation(libs.accompanist.permissions)

    // Koin
    implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.0.0"))
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)

    // Ktor
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.resources)
    implementation(libs.ktor.client.logging)

    // hiveMQ - MQTT
    implementation(libs.hivemq.mqtt.client)
    implementation(kotlin("reflect"))

    // Coil - Image Loader
    implementation(libs.coil.compose)
}

fun getKeystoreProperties(file: File): Properties {
    val props = Properties()
    if (file.exists()) {
        FileInputStream(file).use { props.load(it) }
    }
    return props
}
