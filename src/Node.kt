class Node(val id: Int) {
    val neighbors: MutableMap<Node, Int> = mutableMapOf()
}
