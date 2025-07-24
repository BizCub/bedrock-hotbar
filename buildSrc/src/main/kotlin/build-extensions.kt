import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.expand
import org.gradle.kotlin.dsl.maven
import org.gradle.language.jvm.tasks.ProcessResources
import java.util.*

val Project.mod: ModData get() = ModData(this)
fun Project.prop(key: String): String? = findProperty(key)?.toString()
fun String.upperCaseFirst() = replaceFirstChar { if (it.isLowerCase()) it.uppercaseChar() else it }

fun RepositoryHandler.strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
    forRepository { maven(url) { name = alias } }
    filter { groups.forEach(::includeGroup) }
}

fun ProcessResources.properties(files: Iterable<String>, vararg properties: Pair<String, Any>) {
    for ((name, value) in properties) inputs.property(name, value)
    filesMatching(files) {
        expand(properties.toMap())
    }
}

@JvmInline
value class ModData(private val project: Project) {
    val id: String get() = requireNotNull(project.prop("mod.id")) { "Missing 'mod.id'" }
    val name: String get() = requireNotNull(project.prop("mod.name")) { "Missing 'mod.name'" }
    val description: String get() = requireNotNull(project.prop("mod.description")) { "Missing 'mod.description'" }
    val version: String get() = requireNotNull(project.prop("mod.version")) { "Missing 'mod.version'" }
    val group: String get() = requireNotNull(project.prop("mod.group")) { "Missing 'mod.group'" }
    val modrinth: String get() = requireNotNull(project.prop("mod.modrinth")) { "Missing 'mod.modrinth'" }
    val curseforge: String get() = requireNotNull(project.prop("mod.curseforge")) { "Missing 'mod.curseforge'" }
    val github: String get() = requireNotNull(project.prop("mod.github")) { "Missing 'mod.github'" }
    val mc_start: String get() = requireNotNull(project.prop("mod.mc_start")) { "Missing 'mod.mc_start'" }
    val mc_end: String get() = requireNotNull(project.prop("mod.mc_end")) { "Missing 'mod.mc_end'" }
    val cloth_config: String get() = requireNotNull(project.prop("mod.cloth_config")) { "Missing 'mod.cloth_config'" }
    val modmenu: String get() = requireNotNull(project.prop("mod.modmenu")) { "Missing 'mod.modmenu'" }

    fun prop(key: String) = requireNotNull(project.prop("mod.$key")) { "Missing 'mod.$key'" }
    fun dep(key: String) = requireNotNull(project.prop("deps.$key")) { "Missing 'deps.$key'" }
}