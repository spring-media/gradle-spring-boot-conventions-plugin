Gradle Spring Boot Conventions Plugin ![Build Status](https://snap-ci.com/WeltN24/gradle-spring-boot-conventions-plugin/branch/master/build_image)
========================================

This plugin applies best practices and conventions for developing [Spring Boot](http://projects.spring.io/spring-boot/) microservices at [welt](https://github.com/WeltN24).

## Usage

See [plugin portal](https://plugins.gradle.org/plugin/de.weltn24.spring-boot-conventions).

The plugin applies only conventions and best practices. The necessary Gradle configuration for developing Spring Boot applications must be done in the project itself.

Example:

    buildscript {
        repositories { 
            jcenter() 
            maven { url "https://plugins.gradle.org/m2/" }
            maven { url "http://repo.spring.io/milestone" }
        }
        
        dependencies {
            classpath(
                'org.springframework.boot:spring-boot-gradle-plugin:1.3.0.M2',
                'gradle.plugin.de.weltn24:spring-boot-conventions:2.0.0',
                'org.ajoberstar:gradle-git:1.3.0' 
            )
        }
    }
    apply plugin: 'spring-boot'
    apply plugin: 'de.weltn24.spring-boot-conventions'

## Preconditions
- currently this plugin supports only [Spring Boot](http://projects.spring.io/spring-boot/) projects 1.3.0M2+
- plugin tested with [Gradle](http://gradle.org/) 2.4+ in single and multi project setups
 
## Conventions

### Project

The following standard Gradle plugins will be applied automatically:

+ [java](https://docs.gradle.org/current/userguide/java_plugin.html)

### Dependencies

The following dependencies will be added to the classpath:

| Scope | Dependency | Description |
| ---- | ---- | ------------- |
|compile| org.springframework.boot:spring-boot-starter-actuator| production ready [HTTP endpoints](http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html) |
|compile| org.springframework.boot:spring-boot-devtools| [tools](https://spring.io/blog/2015/06/17/devtools-in-spring-boot-1-3) supporting rapid development of boot apps  (restarting etc.) |
|runtime| org.jolokia:jolokia-core| [JMX-HTTP](http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-jmx.html) bridge to access JMX beans |
|testCompile| org.springframework.boot:spring-boot-starter-test | best practices of libraries to write automated tests for spring apps |

### Custom tasks

#### Git properties

The task `generateGitProperties` is added which will create a `git.properties` when compiling the production code. The VCS information will be displayed in the `/info` endpoint automatically (see [reference guide](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-application-info) for details).

| Type | Name | Default Value | Description |
| ---- | ---- | ------------- | ----------- |
|Boolean| generateGitProperties| true | switches generation of git.properties on/off |
    
#### Build properties

The task `generateBuildProperties` which will expand build info placeholders in `src/main/resources/config/application.yml` The build information will be displayed in the `/info` endpoint automatically (see [reference guide](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-application-info) for details).

Example of application.yml with placeholders:

    info:
        build:
            name: ${name}
            description: ${description}
            version: ${version}
            gradleVersion: ${gradleVersion}
            javaVersion: ${javaVersion}

## Testing

Testing the functionality of the plugin during the development can be achieved manually by executing the following steps:

 1. Publish your new changes to your local maven repository with `./gradlew publishToMavenLocal` (don't forget to increment the version number)
 2. Add the local Maven cache as a repository in an another gradle project than can integrate with the plugin:
 
    ```
    buildscript {
        repositories {
            mavenLocal()
        }
    }    
    ```

 3. Use the updated version of the plugin as a local dependency with the gradle project from the step before and run the plugin
 
## Publishing

Publishing is automatically done by [SnapCI](https://snap-ci.com/WeltN24/gradle-spring-boot-conventions-plugin/branch/master) after a commit with increased version.

## Contributing

Contributions are more than welcome. Please follow the pull request [pro tips](https://guides.github.com/activities/contributing-to-open-source/#contributing) in order to submit your changes.

## License 

Copyright (c) 2015 WeltN24 GmbH

Licensed under the [MIT license](https://tldrlegal.com/license/mit-license).
