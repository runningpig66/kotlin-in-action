plugins {
    kotlin("jvm") version "2.2.20"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

// 跳过编译的文件
val willNotCompile = listOf(
    "**/ch04/4.1.3_1_VisibilityModifiers.kt",
)

kotlin {
    jvmToolchain(21)

    sourceSets {
        val main by getting {
            kotlin.exclude(willNotCompile)
        }
    }
}
