import java.net.URI

buildscript {
    repositories {
        google()
        jcenter()
        maven {
            url = uri(Url.fabric)
        }
    }

    dependencies {
        classpath(kotlin(module = "gradle-plugin", version = Versions.kotlin))
        classpath(BuildPlugins.androidPlugin)
        classpath(BuildPlugins.navigationSafe)
        classpath(BuildPlugins.googleService)
        classpath(BuildPlugins.fabric)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = URI.create(Url.jitpack)
        }
        maven {
            url = uri(Url.fabric)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}