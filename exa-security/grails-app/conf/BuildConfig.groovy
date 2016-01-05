grails.project.work.dir = "target"

grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {
    inherits "global"
    log "warn"
    repositories {
        mavenLocal()
        grailsCentral()
        mavenCentral()
    }

    plugins {
        build(":release:3.1.2",
              ":rest-client-builder:2.1.1") {
            export = false
        }
        compile ":plugin-config:0.2.1"
        compile ":asset-pipeline:1.9.9"
        compile ":scaffolding:2.1.2"
    }
}
