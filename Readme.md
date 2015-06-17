WeltN24 Spring Boot Conventions Gradle Plugin
========================================

## Project integration

Build script snippet for use in all Gradle versions:

    buildscript {
      repositories {
        maven {
          url "https://plugins.gradle.org/m2/"
        }
      }
      dependencies {
        classpath "gradle.plugin.de.weltn24.gradle:spring-boot-conventions:1.1.0"
      }
    }
    
    apply plugin: "de.weltn24.spring-boot-conventions"
    
Build script snippet for new, incubating, plugin mechanism introduced in Gradle 2.1:

    plugins {
      id "de.weltn24.spring-boot-conventions" version "1.1.0"
    }

## This Plugin adds the following features to your Project:

- Gradle plugins
    - java
    
- Dependencies
    - org.springframework.boot:spring-boot-starter-actuator
    - org.springframework.boot:spring-boot-starter-test
    - io.spring.platform:platform-versions (version: see properties)

- Configuration
    All configurations are optional
    
    Example:
    
        weltn24SpringBootConventions {
            generateGitProperties = false
        }
    
    | Type | Name | Default Value | Description |
    | ---- | ---- | ------------- | ----------- |
    |Boolean| generateGitProperties| true | switches generation of git.properties on/off |
    
- Tasks
    - generateBuildProperties : extends application.yml with gradle.properties (generateBuildProperties only exists if "src/main/ressources/config/application.yml" exists)
    - generateGitProperties : creates and fills automatically "resources/main/git.properties"

- Properties (can be changed by modifying gradle.properties in your own project)
    - tomcatVersion : sets version of all org.apache.tomcat.embed (default 8.0.21)
    - springIOVersion : sets version of io.spring.platform:platform-versions (default: 1.1.2.RELEASE)

- Others
    - if "src/main/ressources/config/application.yml" exists, it will be AUTOMATICALLY extended by gradle.properties (on processResources by generateBuildProperties)


## Development

### Build the project

    ./gradlew build

### Test the code  

    ./gradlew test

### Show all tasks

    ./gradlew tasks

## Copyright (c) 2015 WeltN24 GmbH

Released under the [MIT license](https://tldrlegal.com/license/mit-license).
