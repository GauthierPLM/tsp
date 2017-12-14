class LinKernigan(private val graph: Graph, private val solution: List<Node>) {
    companion object {
        var timedOut = false
    }

    init {
        LkTour.initGraph(graph)
        LkPath.initGraph(graph)
    }

    private fun minimize(path: LkPath, tour: LkTour): LkTour? {
        var maxGain = -1
        var bestPath: LkPath? = null

        path.forEach { node: Node ->
            val y = path.next(node)
            val gain = graph.distance(node, y) - graph.distance(node, path.last)
            if (gain > maxGain) {
                maxGain = gain
                bestPath = path.rearrange(node)
            }
        }

        if (maxGain > 0) {
            val newTour = bestPath?.reunite()
            if (newTour != null && newTour.cost() < tour.cost())
                return newTour
        }

        return null
    }

    private fun optimise(tour: LkTour): LkTour? {
        solution.forEach { node: Node ->
            val newTour = minimize(tour.cut(node), tour)
            if (newTour != null)
                return newTour
        }
        return null
    }

    fun run(): List<Node> {
        var tour = LkTour(solution)

        while (!timedOut) {
            optimise(tour)?.let { tour = it }
        }

        return tour.toSolution()
    }
}