package com.vm.dazntask.core.ui.usecase

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FormatDateUseCase {

    companion object {

        private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }

    operator fun invoke(time: LocalDateTime): String {
        return time.format(formatter)
    }
}
