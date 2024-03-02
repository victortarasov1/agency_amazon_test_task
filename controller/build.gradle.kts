dependencies {
    implementation(project(":service"))
    implementation(project(":model"))
    implementation(project(":security"))
    implementation(project(":batch"))
}

tasks.bootJar {
    enabled = true
}