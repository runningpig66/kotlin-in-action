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
    "**/ch06/6.1.4_2_ElvisOperator1.kt",
    "**/ch06/6.1.6_NotnullAssertions.kt",
    "**/ch06/6.1.11.1_2_PlatformTypes.kt",
    "**/ch06/6.2.6_NothingType.kt",
)

kotlin {
    jvmToolchain(21)

    sourceSets {
        val main by getting {
            kotlin.exclude(willNotCompile)
        }
    }
}

// 见 OneNote 《控制台中文日文乱码汇总》
tasks.withType<JavaExec>().configureEach {
    jvmArgs(
        "-Dsun.stdout.encoding=UTF-8",
        "-Dsun.stderr.encoding=UTF-8",
        "-Dfile.encoding=UTF-8"
    )
}
