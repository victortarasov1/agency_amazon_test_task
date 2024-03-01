dependencies {
    implementation(project(":service"))
    implementation(project(":model"))
    implementation(project(":security"))
}

tasks.bootJar {
    enabled = true
}