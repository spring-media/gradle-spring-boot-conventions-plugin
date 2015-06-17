package de.weltn24.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertThat
import static org.junit.Assert.assertTrue


class SpringBootConventionsPluginTest {

    private static final String PLUGIN_NAME = 'weltn24-spring-boot-conventions'

    Project project

    @Before
    public void setUp(){
        project = ProjectBuilder.builder().build()
        project.apply plugin: PLUGIN_NAME
    }

    @Test
    public void javaPluginIsApplied() {
        println 'javaPluginIsApplied'
        assertTrue(project.plugins.hasPlugin('java'))
    }

    @Test
    public void springBootWebPluginAddsGenerateGitPropertiesTask() {
        assertTrue(project.tasks.findByName('generateGitProperties') instanceof Task)
    }

    @Test
    public void springBootPluginAddGenerateBuildPropertiesTaskToProject() {
        Project testproject = ProjectBuilder.builder().build()
        testproject.file("src/main/resources/config").mkdirs()
        testproject.file("src/main/resources/config/application.yml").createNewFile()
        testproject.apply plugin: PLUGIN_NAME
        assertTrue(testproject.tasks.findByName('generateBuildProperties')  instanceof Task)
    }

    @Test
    public void springBootPluginNOTAddGenerateBuildPropertiesTaskToProject() {
        assertTrue(project.tasks.findByName('generateBuildProperties') == null)
    }

}
