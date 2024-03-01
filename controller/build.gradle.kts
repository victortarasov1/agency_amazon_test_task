dependencies {
    implementation(project(":repository"))
    implementation(project(":model"))
    implementation(project(":security"))
}

tasks.bootJar {
    enabled = true
}