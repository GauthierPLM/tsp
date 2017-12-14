import java.io.File

fun outputResult(result: List<Node>, outputPath: String) {
    var nodeList = ""
    result.forEach { nodeList += "${it.id}\n" }
    File(outputPath).apply {
        createNewFile()
        writeText(nodeList)
    }
}