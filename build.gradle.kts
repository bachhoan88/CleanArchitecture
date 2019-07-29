import java.net.URI

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(kotlin(module = "gradle-plugin", version = Versions.kotlin))
        classpath(BuildPlugins.androidPlugin)
        classpath(BuildPlugins.navigationSafe)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = URI.create("https://jitpack.io")
        }

        maven {
            url = URI.create("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
