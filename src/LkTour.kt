class LkTour(private val tour: List<Node>) {
    companion object {
        lateinit var graph: Graph

        fun initGraph(graph: Graph) {
            this.graph = graph
        }
    }

    fun cut(node: Node): LkPath {
        val index = tour.indexOf(node) + 1
        val path = listOf(tour.toList().let {
             it.subList(index, it.size)
        }, tour.toList().subList(0, index)).flatten()
        return LkPath(path)
    }

    fun toSolution(): List<Node> {
        val startIndex = tour.indexOf(graph.first)
        return listOf(tour.takeLast(tour.size - startIndex), tour.take(startIndex)).flatten()
    }

    fun cost(): Int = getCost(tour)
}