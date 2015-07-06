Gradle Spring Boot Conventions Plugin
========================================

![Build Status](https://snap-ci.com/WeltN24/gradle-spring-boot-conventions-plugin/branch/master/build_image)

## Usage

Build script snippet for use in all Gradle versions:

    buildscript {
      repositories {
        maven {
          url "https://plugins.gradle.org/m2/"
        }
      }
      dependencies {
        classpath "gradle.plugin.de.weltn24:spring-boot-conventions:1.2.0"
      }
    }
    
    apply plugin: "de.weltn24.spring-boot-conventions"
    
Build script snippet for new, incubating, plugin mechanism introduced in Gradle 2.1:

    plugins {
      id "de.weltn24.spring-boot-conventions" version "1.2.0"
    }

## This Plugin adds the following features to your Project:

### Gradle plugins
- java
    
### Dependencies
- org.springframework.boot:spring-boot-starter-actuator
- org.springframework.boot:spring-boot-starter-test

### Configuration
All configurations are optional

Example:

    weltn24SpringBootConventions {
        generateGitProperties = false
    }

| Type | Name | Default Value | Description |
| ---- | ---- | ------------- | ----------- |
|Boolean| generateGitProperties| true | switches generation of git.properties on/off |
    
### Tasks
- generateBuildProperties : extends application.yml with gradle.properties (generateBuildProperties only exists if "src/main/ressources/config/application.yml" exists)
- generateGitProperties : creates and fills automatically "resources/main/git.properties"

### Others
- if "src/main/ressources/config/application.yml" exists, it will be AUTOMATICALLY extended by gradle.properties (on processResources by generateBuildProperties)
    
## Publishing

Publishing is automatically done by snap-ci after a commit with increased version.

## Copyright (c) 2015 WeltN24 GmbH

Released under the [MIT license](https://tldrlegal.com/license/mit-license).
