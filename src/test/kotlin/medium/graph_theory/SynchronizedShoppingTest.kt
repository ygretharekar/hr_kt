package medium.graph_theory

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import medium.graph_theory.SynchronizedShopping

internal class SynchronizedShoppingTest {

    @Test
    fun shop() {
        val synchronizedShopping: SynchronizedShopping = SynchronizedShopping()
        assertEquals(0, synchronizedShopping.shop(0, 0, arrayOf("abc", "abc"), Array(1)  { arrayOf(1, 2, 3)}))
    }
}
