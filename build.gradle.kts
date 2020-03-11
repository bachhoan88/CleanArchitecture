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
        classpath(BuildPlugins.googleService)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = URI.create(Url.jitpack)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}