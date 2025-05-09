plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.cloneui_socialmedia"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cloneui_socialmedia"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.camera.lifecycle)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Glide & Lottie
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.airbnb.android:lottie:3.4.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    //CameraX - https://developer.android.com/jetpack/androidx/releases/camera#kts
    implementation("androidx.camera:camera-core:<latest_version>")
    implementation("androidx.camera:camera-camera2:<latest_version>")
    implementation("androidx.camera:camera-lifecycle:<latest_version>")
    implementation("androidx.camera:camera-view:<latest_version>")

    //ViewPager2
    implementation("androidx.viewpager2:viewpager2:<latest_version>")

    //Pull Refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}