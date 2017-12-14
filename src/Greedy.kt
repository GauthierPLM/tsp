class Greedy(private val graph: Graph) {
    private val visitedNodes: MutableList<Node> = mutableListOf()

    private fun getClosest(node: Node): Node {
        return node.neighbors
                .filter { it.value != 0 && !visitedNodes.contains(it.key) }
                .minBy { it.value }!!.key
    }

    fun solve(): List<Node> {
        var node = graph.first
        visitedNodes.add(graph.first)

        while (visitedNodes.size != graph.size) {
            node = getClosest(node)
            visitedNodes.add(node)
        }

        return visitedNodes.toList()
    }
}