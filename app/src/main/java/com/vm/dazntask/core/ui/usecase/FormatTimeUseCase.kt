package com.vm.dazntask.core.ui.usecase

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FormatTimeUseCase {

    companion object {

        private val formatter = DateTimeFormatter.ofPattern("HH:mm")
    }

    operator fun invoke(time: LocalDateTime): String {
        return time.format(formatter)
    }
}
