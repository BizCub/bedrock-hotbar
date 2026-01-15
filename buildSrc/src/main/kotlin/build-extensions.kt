import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import dev.kikugie.stonecutter.controller.StonecutterControllerExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.language.jvm.tasks.ProcessResources

val Project.mod: ModData get() = ModData(this)
fun Project.prop(key: String): String? = findProperty(key)?.toString()
fun String.upperCaseFirst() = replaceFirstChar { if (it.isLowerCase()) it.uppercaseChar() else it }

val Project.scb get() = extensions.getByType<StonecutterBuildExtension>()
val Project.scc get() = extensions.getByType<StonecutterControllerExtension>()

val Project.loader: String get() = scb.current.project.substringAfterLast("-")
val Project.isFabric: Boolean get() = loader == "fabric"
val Project.isForge: Boolean get() = loader == "forge"
val Project.isNeoForge: Boolean get() = loader == "neoforge"
val Project.isClothConfigAvailable: Boolean get() = !(isForge && scb.current.parsed >= "1.21.4")

fun ProcessResources.properties(files: Iterable<String>, vararg properties: Pair<String, Any>) {
    for ((name, value) in properties) inputs.property(name, value)
    filesMatching(files) {
        expand(properties.toMap())
    }
}

@JvmInline
value class ModData(private val project: Project) {
    val mc: String get() = depOrNull("minecraft") ?: project.scb.current.version
    val id: String get() = modProp("id")
    val mixin: String get() = modProp("id").replace("_", "-")
    val name: String get() = modProp("name")
    val description: String get() = modProp("description")
    val version: String get() = modProp("version")
    val pub_start: String get() = propIfExist("pub.start", mc).toString()
    val pub_end: String get() = propIfExist("pub.end", mc).toString()
    val modrinth: String get() = modProp("modrinth")
    val curseforge: String get() = modProp("curseforge")
    val github: String get() = modProp("github")
    val cloth_config: String get() = modProp("cloth_config")
    val modmenu: String get() = modProp("modmenu")

    fun propIfExist(key: String, fallback: String) = if (project.prop(key) != null) project.prop(key) else fallback
    fun modPropOrNull(key: String) = project.prop("mod.$key")
    fun modProp(key: String) = requireNotNull(modPropOrNull(key)) { "Missing 'mod.$key'" }
    fun depOrNull(key: String): String? = project.prop("deps.$key")?.takeIf { it.isNotEmpty() && it != "" }
    fun dep(key: String) = requireNotNull(depOrNull(key)) { "Missing 'deps.$key'" }
}
