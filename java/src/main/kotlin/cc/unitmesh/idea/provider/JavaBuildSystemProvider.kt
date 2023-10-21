package cc.unitmesh.idea.provider

import cc.unitmesh.devti.provider.BuildSystemProvider
import cc.unitmesh.devti.template.DockerfileContext
import com.intellij.openapi.externalSystem.service.project.ProjectDataManager
import com.intellij.openapi.project.Project
import com.intellij.util.lang.JavaVersion
import org.jetbrains.plugins.gradle.util.GradleConstants


open class JavaBuildSystemProvider : BuildSystemProvider() {
    override fun collect(project: Project): DockerfileContext {
        val projectDataManager = ProjectDataManager.getInstance()
        var buildToolName = ""
        val gradleInfo = projectDataManager.getExternalProjectsData(project, GradleConstants.SYSTEM_ID)
        if (gradleInfo.isNotEmpty()) {
            buildToolName = "Gradle"
        } else {
            buildToolName = "Maven"
        }

        val javaVersion = JavaVersion.current()
        val javaVersionStr = "${javaVersion.feature}"

        return DockerfileContext(
            buildToolVersion = "",
            buildToolName = buildToolName,
            languageName = "Java",
            languageVersion = javaVersionStr
        )
    }
}