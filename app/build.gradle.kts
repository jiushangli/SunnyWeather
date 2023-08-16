@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.jiushang.mysunnyweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jiushang.mysunnyweather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)


    implementation ("androidx.recyclerview:recyclerview:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation ("com.google.android.material:material:1.1.0")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")
    implementation ("com.squareup.retrofit2:retrofit:2.6.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1")



}