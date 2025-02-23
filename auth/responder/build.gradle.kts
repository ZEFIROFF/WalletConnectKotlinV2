plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = COMPILE_SDK

    defaultConfig {
        applicationId = "com.walletconnect.responder"
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = jvmVersion
        targetCompatibility = jvmVersion
    }
    kotlinOptions {
        jvmTarget = jvmVersion.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":sign:samples_common")) //todo: Move samples common module out of signSDK

    debugImplementation(project(":androidCore:sdk"))
    debugImplementation(project(":auth:sdk"))

    releaseImplementation(platform("com.walletconnect:android-bom:$BOM_VERSION"))
    releaseImplementation("com.walletconnect:android-core")
    releaseImplementation("com.walletconnect:auth")

    glide_N_kapt()
    scanner()
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    api("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    lifecycle()
    navigationComponent()
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}