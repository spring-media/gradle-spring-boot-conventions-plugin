package de.weltn24.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

class SpringBootConventionsPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.apply(plugin: 'java')

        project.repositories {
            jcenter()
        }

        project.afterEvaluate {
            project.dependencies.add(JavaPlugin.COMPILE_CONFIGURATION_NAME, "org.springframework.boot:spring-boot-starter-actuator")
            project.dependencies.add(JavaPlugin.COMPILE_CONFIGURATION_NAME, "org.springframework.boot:spring-boot-devtools")
            project.dependencies.add(JavaPlugin.COMPILE_CONFIGURATION_NAME, "org.springframework.boot:spring-boot-starter-security")
            project.dependencies.add(JavaPlugin.RUNTIME_CONFIGURATION_NAME, "org.jolokia:jolokia-core")
            project.dependencies.add(JavaPlugin.TEST_COMPILE_CONFIGURATION_NAME, "org.springframework.boot:spring-boot-starter-test")
        }
    }
}
