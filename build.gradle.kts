plugins {
    kotlin("multiplatform") version "1.4.31"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
//    google()
    jcenter()
    mavenCentral()
    maven("http://repository.jetbrains.com/all")
}

kotlin {
    jvm{
        withJava()
    }
    js{
        browser{}
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
                implementation(kotlin("reflect"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                //only junit4, junit5 don't work
                implementation(kotlin("test-junit"))
                implementation(kotlin("reflect"))
            }
        }

    }
}