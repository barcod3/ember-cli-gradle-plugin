package com.kiefer.gradle

import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class EmberBuildConfigurationTest extends EmberCliPluginSupport {
    final String PROJECT_NAME = "projectName";

    @BeforeMethod
    void setUp() {
        project = ProjectBuilder.builder().withName(PROJECT_NAME).build()
        project.pluginManager.apply "com.kiefer.gradle.embercli"

        project.embercli {
            environment = "development"
        }

        createPackageJson()

        project.evaluate()
    }

    @Test
    void taskExecutesAppropriateCommand() {
        def task = project.tasks.emberBuild
        assert project.rootDir == task.workingDir
        assert task.executable.contains("ember")
        assert ["build", "--environment", "development"] == task.args
    }
}
