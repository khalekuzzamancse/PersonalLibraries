plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsCompose)
    id("maven-publish")
}


android {
    namespace = "khalekuzzaman_cse_libraries.common_ui"
    compileSdk = 34


    defaultConfig {
        minSdk = 27


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(compose.ui)
    implementation(compose.material3)
    implementation(compose.animation)
    implementation(compose.animationGraphics)
    implementation(compose.materialIconsExtended)
    implementation(compose.foundation)
    implementation(compose.runtime)
    implementation(libs.windowSize)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)


}


publishing {


    publications {
        create<MavenPublication>(name = "KhalekuzzamanCSE") {
            groupId = "khalekuzzamancse.cse"
            artifactId = "cmp-components"
            version = "1.0.0"
            // Explicitly specify the AAR file to publish
            artifact("${buildDir}/outputs/aar/${project.name}-release.aar")
        }
    }


    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/khalekuzzamancse/PersonalLibraries")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
