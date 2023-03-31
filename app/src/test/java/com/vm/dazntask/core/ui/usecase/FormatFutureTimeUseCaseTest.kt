package com.vm.dazntask.core.ui.usecase

import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month

class FormatFutureTimeUseCaseTest {

    private val formatFutureTime = FormatFutureTimeUseCase()

    @Test
    fun `When time is today, includes Today string`() {
        val now = LocalDateTime.of(2023, Month.MARCH, 29, 10, 0)
        val today = LocalDateTime.of(2023, Month.MARCH, 29, 20, 0)
        assertTrue(formatFutureTime(today, now).contains("Today"))
    }

    @Test
    fun `When time is tomorrow, includes Tomorrow string`() {
        val now = LocalDateTime.of(2023, Month.MARCH, 29, 20, 0)
        val tomorrow = LocalDateTime.of(2023, Month.MARCH, 30, 10, 0)
        assertTrue(formatFutureTime(tomorrow, now).contains("Tomorrow"))
    }

    @Test
    fun `When time is not today or tomorrow, does not include Today and Tomorrow strings`() {
        val now = LocalDateTime.of(2023, Month.MARCH, 29, 20, 0)
        val day = LocalDateTime.of(2023, Month.MARCH, 31, 10, 0)
        val formatted = formatFutureTime(day, now)
        assertTrue(!formatted.contains("Today") && !formatted.contains("Tomorrow"))
    }
}
