Spring Boot Conventions Gradle Plugin
========================================

### This Plugin adds the following features to your Project:

- gradle plugins:
    - java
    
- Dependencies:
    - org.springframework.boot:spring-boot-starter-actuator
    - org.springframework.boot:spring-boot-starter-test
    - io.spring.platform:platform-versions (version: see properties)

- Configuration
    All configurations are optional
    
    Example:
    
        project.weltn24SpringBootConventions {
            generateGitProperties = false
        }
    
    | Type | Name | Default Value | Description |
    | ---- | ---- | ------------- | ----------- |
    |Boolean| generateGitProperties| true | switches generation of git.properties on/off |
    
- Tasks:
    - generateBuildProperties : extends application.yml with gradle.properties (generateBuildProperties only exists if "src/main/ressources/config/application.yml" exists)
    - generateGitProperties : creates and fills automatically "resources/main/git.properties"

- Properties (can be changed by modifying gradle.properties in your own project):
    - tomcatVersion : sets version of all org.apache.tomcat.embed (default 8.0.21)
    - springIOVersion : sets version of io.spring.platform:platform-versions (default: 1.1.2.RELEASE)

- Others:
    - if "src/main/ressources/config/application.yml" exists, it will be AUTOMATICALLY extended by gradle.properties (on processResources by generateBuildProperties)

    
###Copyright (c) 2015 WeltN24 GmbH

Released under the [MIT license](https://tldrlegal.com/license/mit-license).
