plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"  // Ensure KSP compatibility
    id("com.google.gms.google-services") version "4.4.2" // Check the version compatibility
}

android {
    namespace = "com.example.testapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.testapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
    buildFeatures {
        compose = true
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
    implementation(libs.androidx.runtime.livedata)

    implementation("com.google.firebase:firebase-analytics-ktx:21.1.0") // Downgrade to a version compatible with Kotlin 1.9.0
    implementation("com.google.firebase:firebase-auth-ktx:21.1.0") // Downgrade to a compatible version
    implementation("com.google.firebase:firebase-firestore-ktx:24.0.1") // Downgrade to a compatible version
    implementation("com.google.android.gms:play-services-measurement-api:21.1.0")

    // Room dependencies
    implementation(libs.androidx.room.runtime) // Room runtime library
    ksp(libs.androidx.room.compiler) // KSP compiler for Room
    implementation(libs.androidx.room.ktx) // Room extensions

    // Material Design and UI libraries
    implementation(libs.material3) // Material 3 components
    implementation(libs.androidx.lifecycle.viewmodel.compose) // ViewModel Compose integration
    implementation(libs.androidx.navigation.compose) // Navigation for Compose
    implementation(libs.androidx.core.ktx) // Core KTX extensions
    implementation(libs.androidx.lifecycle.runtime.ktx) // Lifecycle extensions
    implementation(libs.androidx.activity.compose) // Activity Compose integration

    // Jetpack Compose libraries (using BOM)
    implementation(platform(libs.androidx.compose.bom)) // Automatically resolves versions for Compose libraries
    implementation(libs.androidx.ui) // Compose UI
    implementation(libs.androidx.ui.graphics) // Compose UI Graphics
    implementation(libs.androidx.ui.tooling.preview) // Compose Preview

    // Testing dependencies
    testImplementation(libs.junit) // JUnit testing framework
    androidTestImplementation(libs.androidx.junit) // AndroidX JUnit testing
    androidTestImplementation(libs.androidx.espresso.core) // Espresso for UI tests
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Use Compose BOM for testing
    androidTestImplementation(libs.androidx.ui.test.junit4) // Compose testing with JUnit4
    debugImplementation(libs.androidx.ui.tooling) // Compose Tooling for debugging
    debugImplementation(libs.androidx.ui.test.manifest) // Compose test manifest for debugging
}