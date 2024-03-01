dependencies {
    implementation(project(":repository"))
    implementation(project(":model"))
}

tasks.bootJar {
    enabled = true
}