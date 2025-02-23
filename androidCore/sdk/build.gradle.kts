plugins {
    id("com.android.library")
    kotlin("android")
    id("publish-module-android")
    id("com.google.devtools.ksp") version kspVersion
}

project.apply {
    extra[KEY_PUBLISH_ARTIFACT_ID] = "android-core"
    extra[KEY_PUBLISH_VERSION] = CORE_VERSION
    extra[KEY_SDK_NAME] = "Android Core"
}

android {
    compileSdk = COMPILE_SDK

    defaultConfig {
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK

        buildConfigField(type = "String", name = "SDK_VERSION", value = "\"${requireNotNull(extra.get(KEY_PUBLISH_VERSION))}\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        File("${rootDir.path}/gradle/consumer-rules").listFiles()?.forEach { proguardFile ->
            consumerProguardFiles(proguardFile.path)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "${rootDir.path}/gradle/proguard-rules/sdk-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = jvmVersion
        targetCompatibility = jvmVersion
    }

    kotlinOptions {
        jvmTarget = jvmVersion.toString()
    }
}

dependencies {
    debugApi(project(":foundation"))
    releaseApi("com.walletconnect:foundation:$FOUNDATION_VERSION")

    bouncyCastle()
    coroutines()
    moshiKsp()
    moshi()
    scarlet()
    scarletAndroid()
    sqlDelightAndroid()
    sqlCipher()
    security()
    koinAndroid()
    multibaseJava()
    timber()

    jUnit5()
    jUnit5Android()
    mockk()
}