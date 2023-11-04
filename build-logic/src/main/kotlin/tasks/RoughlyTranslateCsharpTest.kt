package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import java.io.File
import java.nio.file.StandardOpenOption
import javax.inject.Inject
import kotlin.io.path.Path
import kotlin.io.path.createFile
import kotlin.io.path.notExists
import kotlin.io.path.writeText

/**
 * @author https://www.baeldung.com/kotlin/convert-camel-case-snake-case#1-using-the-replace-function-with-regex
 */
fun String.camelToSnakeCase(): String {
    val pattern = "(?<=.)[A-Z]".toRegex()
    return this.replace(pattern, " $0").lowercase()
}

abstract class RoughlyTranslateCsharpTest : DefaultTask() {
    @set:Option(option = "file", description = "Configures the file to translate.")
    @get:Input
    abstract var source: String

    @set:Option(option = "output", description = "Configures the output destination.")
    @get:Input
    var output: String = ""

    @TaskAction
    fun translate() {
        val fileContent = File(source).readText()

        val translated = fileContent
            .replace("[Fact]", "")
            .replace("new ", "")
            .replace("""public void Should(.*)\(\)""".toRegex()) {
                val (methodName) = it.destructured

                "should(\"${methodName.camelToSnakeCase().replace('_', ' ')}\")"
            }
            .replace("""Assert.(False|True)\((.*)\);""".toRegex()) {
                val (boolStr, assertion) = it.destructured

                "$assertion shouldBe ${boolStr.lowercase()}"
            }
            .replace("""Assert.Equal\((.*),(.*)\);""".toRegex()) {
                val (expected, actual) = it.destructured

                "${actual.lowercase()} shouldBe $expected"
            }
            .replace("var", "val")
            .filterNot { c -> c == ';' }

        if (output.isBlank()) {
            logger.quiet(translated)
        } else {
            with(Path(output)) {
                if (notExists()) {
                    createFile()
                }

                writeText(translated, options = arrayOf(StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING))
            }
        }
    }
}
