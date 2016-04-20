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
                'org.springframework.boot:spring-boot-gradle-plugin:1.4.0.M3',
                'gradle.plugin.de.weltn24:spring-boot-conventions:4.0.0',
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
|compile| org.springframework.boot:spring-boot-starter-security| default [spring security](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security) configuration |
|compile| org.springframework.boot:spring-boot-devtools| [tools](https://spring.io/blog/2015/06/17/devtools-in-spring-boot-1-3) supporting rapid development of boot apps  (restarting etc.) |
|runtime| org.jolokia:jolokia-core| [JMX-HTTP](http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-jmx.html) bridge to access JMX beans |
|testCompile| org.springframework.boot:spring-boot-starter-test | best practices of libraries to write automated tests for spring apps |

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
