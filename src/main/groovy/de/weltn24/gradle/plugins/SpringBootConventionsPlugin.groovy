package de.weltn24.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.DependencyResolveDetails
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.Copy
import org.ajoberstar.grgit.Grgit

class SpringBootConventionsPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.apply(plugin: 'java')

        project.repositories {
            jcenter()
        }

        SpringBootConventionsPluginExtension pluginVariables = project.extensions.create('weltn24SpringBootConventions', SpringBootConventionsPluginExtension)

        project.afterEvaluate {
            project.dependencies.add(JavaPlugin.COMPILE_CONFIGURATION_NAME, "org.springframework.boot:spring-boot-starter-actuator")
            project.dependencies.add(JavaPlugin.COMPILE_CONFIGURATION_NAME, "org.springframework.boot:spring-boot-devtools")
            project.dependencies.add(JavaPlugin.COMPILE_CONFIGURATION_NAME, "org.springframework.cloud:spring-cloud-starter-hystrix:1.0.3.RELEASE")
            project.dependencies.add(JavaPlugin.TEST_COMPILE_CONFIGURATION_NAME, "org.springframework.boot:spring-boot-starter-test")
        }

        project.configurations.all {
            // com.netflix.archaius:archaius-core:0.4.1 is a transitive dependency of spring-cloud-starter-hystrix
            // but leads to CNF exceptions on com.netflix.config.DeploymentContext$ContextKey
            // using a newer version seems to fix this problem (see https://github.com/spring-cloud/spring-cloud-starters/issues/20)
            resolutionStrategy.eachDependency { DependencyResolveDetails details ->
            if (details.requested.name == 'archaius-core') {
                details.useVersion('0.7.1')
            }
            }
        }

        project.task('generateBuildProperties',
            type: Copy,
            group: 'build',
            description: 'Generates build properties file.') {


            from('src/main/resources/config') {
                include 'application.yml'
                expand(project.properties)

            }
            duplicatesStrategy = 'include'
            into 'build/resources/main/config'
        }
        project.tasks.findByName('processResources').finalizedBy('generateBuildProperties')
        project.tasks.findByName('generateBuildProperties').onlyIf { project.file("src/main/resources/config/application.yml").exists() }

        project.task('generateGitProperties',
                group: 'build',
                description: 'Generates Git properties file.') << {

            try {
                def repo = Grgit.open(project.file(project.rootDir))
                def dir = new File(project.buildDir, "resources/main")
                def file = new File(project.buildDir, "resources/main/git.properties")
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                if (!file.exists()) {
                    file.createNewFile()
                }
                def map = ["git.branch"                : repo.branch.current.name
                           , "git.commit.id"           : repo.head().id
                           , "git.commit.id.abbrev"    : repo.head().abbreviatedId
                           , "git.commit.user.name"    : repo.head().author.name
                           , "git.commit.user.email"   : repo.head().author.email
                           , "git.commit.message.short": repo.head().shortMessage
                           , "git.commit.message.full" : repo.head().fullMessage
                           , "git.commit.time"         : repo.head().time.toString()]
                def props = new Properties()
                props.putAll(map)
                props.store(file.newWriter(), "")
            } catch (Exception ex) {
                project.logger.warn "Git repository not found, skipping generation of git.properties"
            }
        }
        project.tasks.findByName('generateGitProperties').onlyIf { pluginVariables.generateGitProperties }
        project.tasks.findByName('classes').dependsOn('generateGitProperties')

    }
}
