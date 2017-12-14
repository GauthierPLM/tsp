class LkPath(private val path: List<Node>) {
    val last: Node
        get() = this.path.last()

    companion object {
        lateinit var graph: Graph

        fun initGraph(graph: Graph) {
            this.graph = graph
        }
    }

    fun forEach(function: (Node) -> Unit) {
        path.dropLast(2).forEach(function)
    }

    fun next(node: Node): Node {
        return path[path.indexOf(node) + 1]
    }

    fun rearrange(node: Node): LkPath {
        val index = path.indexOf(node) + 1
        return LkPath(path.take(index) + path.takeLast(path.size - index).reversed())
    }

    fun reunite(): LkTour = LkTour(path)
}