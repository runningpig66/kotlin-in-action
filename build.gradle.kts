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
    val kotlinxHtmlVersion = "0.12.0"
    // include for JVM target
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
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

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.apply {
        add("-Xlint:unchecked")
        add("-Xlint:deprecation")
        add("-Xdiags:verbose")
        // add("-Werror")
    }
    options.encoding = "UTF-8"
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs(
        "-Dsun.stdout.encoding=UTF-8",
        "-Dsun.stderr.encoding=UTF-8",
        "-Dfile.encoding=UTF-8"
    )
}
