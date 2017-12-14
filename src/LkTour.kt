class LkTour(private val tour: List<Node>) {
    val cost: Int = getCost(tour)

    companion object {

        lateinit var graph: Graph
        fun initGraph(graph: Graph) {
            this.graph = graph
        }

    }

    fun cut(node: Node): LkPath {
        val index = tour.indexOf(node) + 1
        return LkPath(tour.takeLast(tour.size - index) + tour.take(index))
    }

    fun toSolution(): List<Node> {
        val startIndex = tour.indexOf(graph.first)
        return tour.takeLast(tour.size - startIndex) + tour.take(startIndex)
    }
}