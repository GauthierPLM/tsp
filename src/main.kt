import java.io.FileNotFoundException
import java.util.concurrent.*
import kotlin.io.*

fun addNeighbors(line: String, graph: Graph, lineNumber: Int): Node {
    val node = graph[lineNumber]
    line.split(',').forEachIndexed { index, value ->
        node.neighbors.put(graph[index], value.trim().toInt())
    }
    return node
}

fun getCost(solution: List<Node>): Int {
    var cost = 0
    solution.forEachIndexed { index, node ->
        val nextElement = solution.elementAtOrNull(index + 1)
        if (nextElement != null) {
            cost += node.neighbors[nextElement]!!
        }
    }

    return cost + solution.last().neighbors[solution.first()]!!
}

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("usage: tsp <input file> <output file>")
        return
    }

    val service = Executors.newSingleThreadExecutor()
    var solution: List<Node>
    try {
        val r = Runnable {
            val graph = parseFile(args.first())
            solution = Greedy(graph).solve()
            solution = Lk(graph, solution).run() + graph.first
            outputResult(solution, args.last())
        }

        service.submit(r).get(28, TimeUnit.SECONDS)
    }
    catch (e: FileNotFoundException) {
        println("Error: cannot find file ${args.first()}")
    }
    catch (e: InterruptedException) {
        // The thread was interrupted during sleep, wait or join
        Lk.timedOut = true
    }
    catch (e: TimeoutException) {
        // Took too long!
        Lk.timedOut = true
    }
    finally {
        service.shutdown()
    }
}