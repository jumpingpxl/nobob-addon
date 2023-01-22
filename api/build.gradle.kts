version = "0.1.0"

plugins {
    id("java-library")
}

dependencies {
    labyProcessor("processor")
    labyApi("api")
}

labyModProcessor {
    referenceType = net.labymod.gradle.core.processor.ReferenceType.INTERFACE
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}