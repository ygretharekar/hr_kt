package medium.graph_theory

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import medium.graph_theory.SynchronizedShopping

internal class SynchronizedShoppingTest {
    @Test
    fun shop() {
        val synchronizedShopping: SynchronizedShopping = SynchronizedShopping()
        assertEquals(
            1,
            synchronizedShopping.shop(
                5,
                5,
                arrayOf("1 1", "1 2", "1 3", "1 4", "1 5"),
                arrayOf(
                    arrayOf(1, 2, 10),
                    arrayOf(1, 3, 10),
                    arrayOf(2, 4, 10),
                    arrayOf(3, 5, 10),
                    arrayOf(4, 5, 10)
                )
            )
        )
    }
}
