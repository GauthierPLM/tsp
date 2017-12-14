import java.io.File

fun parseFile(fileName: String): Graph {
    val graph = Graph()

    File(fileName).useLines { lines ->
        lines.filter { !it.isBlank() }.forEachIndexed { lineNumber, line ->
            addNeighbors(line, graph, lineNumber)
        }
    }

    return graph
}