package medium.graph_theory

import kotlin.math.max
import kotlin.math.min

class SynchronizedShopping {

    private var shops = mutableListOf<Int>()
    private var shortestPath: MutableMap<Int, MutableList<Int>> = mutableMapOf()
    private var queue: MutableSet<Pair<Int, Pair<Int, Int>>> = mutableSetOf<Pair<Int, Pair<Int, Int>>>()

    private fun inQueue(node: Int, fishes: Int, cost: Int, fishNum: Int): Unit {
        if (shortestPath[node]?.getOrNull(fishes) ?: Int.MAX_VALUE <= cost) {
            return
        }

        queue.remove(shortestPath[node]?.getOrNull(fishes) ?: Int.MAX_VALUE to (node to fishes))
        val pathList: MutableList<Int> =
            shortestPath.getOrPut<Int, MutableList<Int>>(node) { MutableList(1 shl fishNum) { Int.MAX_VALUE } }
        pathList[fishes] = cost

        queue.add(cost to (node to fishes))
    }

    private fun dijkdtra(src: Int, fishNum: Int, biRoads: Array<MutableList<Pair<Int, Int>>>) {
        inQueue(src, shops[src], 0, fishNum)

        while (queue.isNotEmpty()) {
            val cost = queue.first().first
            val currentFishes = queue.first().second.second
            val node = queue.first().second.first

            queue.remove(queue.first())

            for (road in biRoads[node]) {
                inQueue(road.first, shops[road.first] or currentFishes, cost + road.second, fishNum)
            }
        }
    }

    fun shop(n: Int, k: Int, centers: Array<String>, roads: Array<Array<Int>>): Int {
        // Write your code here

        val fishShops = centers.map { s ->
            s
                .split(" ")
                .map {
                    it.toInt()
                }
        }

        val biRoads: Array<MutableList<Pair<Int, Int>>> = Array(n) { mutableListOf<Pair<Int, Int>>() }

        roads
            .forEach {
                biRoads[it[0] - 1].add(Pair(it[1] - 1, it[2]))
                biRoads[it[1] - 1].add(Pair(it[0] - 1, it[2]))
            }

        fishShops
            .forEach { a ->
                var fishes: Int = 0
                a
                    .drop(1)
                    .forEach {
                        fishes = fishes or (1 shl it - 1)
                    }

                shops.add(fishes)
            }

        dijkdtra(0, k, biRoads)

        var ans = Int.MAX_VALUE

        shortestPath[n - 1]?.forEachIndexed { i, i1 ->
            shortestPath[n - 1]?.forEachIndexed { j, j1 ->
                if (i1 != Int.MAX_VALUE && j1 != Int.MAX_VALUE && (i or j) == (1 shl k) - 1) {
                    ans = min(
                        ans,
                        max(i1, j1)
                    )
                }
            }
        }

        return ans
    }
}
