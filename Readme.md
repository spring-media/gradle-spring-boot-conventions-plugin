Gradle Spring Boot Conventions Plugin ![Build Status](https://snap-ci.com/WeltN24/gradle-spring-boot-conventions-plugin/branch/master/build_image)
========================================

This plugin applies best practices and conventions for developing [Spring Boot](http://projects.spring.io/spring-boot/) microservices at [welt](https://github.com/WeltN24).

## Usage

Build script snippet for use in all Gradle versions:

    buildscript {
      repositories {
        maven {
          url "https://plugins.gradle.org/m2/"
        }
      }
      dependencies {
        classpath "gradle.plugin.de.weltn24:spring-boot-conventions:1.3.0"
      }
    }
    
    apply plugin: "de.weltn24.spring-boot-conventions"
    
Build script snippet for new, incubating, plugin mechanism introduced in Gradle 2.1:

    plugins {
      id "de.weltn24.spring-boot-conventions" version "1.3.0"
    }

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
|compile| org.springframework.cloud:spring-cloud-starter-hystrix | [Spring Cloud](http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html) abstraction of the [Hystrix](https://github.com/Netflix/Hystrix) project [tools](https://spring.io/blog/2015/06/17/devtools-in-spring-boot-1-3) supporting rapid development of boot apps  |
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


## Publishing

Publishing is automatically done by snap-ci after a commit with increased version.

## Copyright (c) 2015 WeltN24 GmbH

Released under the [MIT license](https://tldrlegal.com/license/mit-license).
