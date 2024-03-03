plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin") version "2.7.1"
    id("com.google.devtools.ksp")
}


android {
    namespace = "com.example.fragment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fragment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
//    packagingOptions {
//        exclude("META-INF/rxjava.properties")
//    }





    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

//    androidExtensions {
//        experimental = true
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
        viewBinding=true
        compose= true

    }
    composeOptions {
        kotlinCompilerExtensionVersion="1.4.2"
    }

}

dependencies {
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    //versions
    val fragment_version = "1.6.2"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    // Java language implementation
//    implementation("androidx.fragment:fragment:$fragment_version")
    // Kotlin
//    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.4.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    // coil
    implementation("io.coil-kt:coil:0.13.0")

    val navVersion =  "2.2.1"

    // Navigatiom
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Feature module Support
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

    implementation("com.intuit.sdp:sdp-android:1.0.6")
// For SSP, use this:
    implementation("com.intuit.ssp:ssp-android:1.0.6")


    //Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:${room_version}")


    val epoxyVersion="4.6.4"

    implementation("com.airbnb.android:epoxy:$epoxyVersion")
    // Add the annotation processor if you are using Epoxy's annotations (recommended)
//    annotationProcessor("com.airbnb.android:epoxy-processor:$epoxyVersion")
    kapt("com.airbnb.android:epoxy-processor:$epoxyVersion")

//    kapt("groupId:artifactId:version")

    // RetroFit
    val retrofit_version = "2.5.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofit_version")
    implementation("com.squareup.okhttp3:logging-interceptor:3.8.0")

    // RxJava
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.8")

    //compose
    implementation("androidx.compose.ui:ui:1.6.2")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("androidx.compose.material:material:1.6.2")
    implementation("androidx.compose.runtime:runtime:1.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose:compose-bom:2024.02.01")


}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}