class Graph {
    val nodes: MutableList<Node> = mutableListOf()
    val size
        get() = nodes.size

    val first: Node
        get() = nodes.first()

    operator fun get(nodeId: Int): Node = nodes.getOrElse(nodeId) {
        nodes.add(it, Node(it))
        nodes[it]
    }

    fun distance(a: Node, b: Int): Int {
        return a.neighbors[nodes[b]]!!
    }

    fun distance(a: Node, b: Node): Int {
        return a.neighbors[b]!!
    }
}