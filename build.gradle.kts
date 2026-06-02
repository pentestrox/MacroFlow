plugins {
    id("java")
}

group   = "com.pentest.rox"
version = "1.0.2"

// Java 11 — BApp Store compatibility requirement
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    // Burp Extender API — compile-only, Burp provides this at runtime
    compileOnly(files("libs/burp-extender-api-1.7.22.jar"))

    // Bundled dependencies — included in the fat JAR
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.json:json:20240303")
}

// ── Fat JAR — merges all runtime dependencies into one file ───────────────
tasks.jar {
    archiveBaseName.set("MacroFlow")
    archiveVersion.set(version.toString())
    archiveClassifier.set("")

    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith(".jar") }
            .map { zipTree(it) }
    }) {
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
    }

    // Bundle the logo PNG at the JAR root so getResourceAsStream finds it
    from("src/main/resources") {
        include("pentext.rox.png")
        into("")
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes(
            "Implementation-Title"   to "MacroFlow",
            "Implementation-Version" to version
        )
    }
}

tasks.test {
    useJUnitPlatform()
}