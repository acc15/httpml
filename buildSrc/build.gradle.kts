plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
}
