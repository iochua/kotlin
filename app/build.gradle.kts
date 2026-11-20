val supabase_version = "2.5.2"
val ktor_version = "2.3.12"

plugins {
    alias(libs.plugins.android.application)
    //Plugin
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.24"
}

android {
    namespace = "com.example.practica7"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.practica7"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // Plataforma BOM para mantener versiones consistentes
    implementation(platform("io.github.jan-tennert.supabase:bom:$supabase_version"))

    // Módulos de Supabase-KT
    implementation("io.github.jan-tennert.supabase:postgrest-kt")

    // Cliente HTTP (obligatorio)
    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    // Coroutines (para usar suspend functions)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
}