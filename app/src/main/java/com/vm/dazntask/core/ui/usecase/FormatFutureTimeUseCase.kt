package com.vm.dazntask.core.ui.usecase

import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class FormatFutureTimeUseCase {

    private val formatTime = FormatTimeUseCase()

    operator fun invoke(
        time: LocalDateTime,
        now: LocalDateTime = LocalDateTime.now()
    ): String {
        val diff = Duration.between(
            now.truncatedTo(ChronoUnit.DAYS),
            time.truncatedTo(ChronoUnit.DAYS)
        ).toDays()
        return when (diff) {
            0L -> "Today, ${formatTime(time)}"
            1L -> "Tomorrow, ${formatTime(time)}"
            else -> "In $diff days"
        }
    }
}
