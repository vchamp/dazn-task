package com.vm.dazntask.core.domain

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId

class ConvertStringToLocalTimeUseCase {

    operator fun invoke(
        dateString: String,
        zoneId: ZoneId = ZoneId.systemDefault()
    ): LocalDateTime {
        val offsetDateTime = OffsetDateTime.parse(dateString)
        val zonedDateTime = offsetDateTime.atZoneSameInstant(zoneId)
        return zonedDateTime.toLocalDateTime()
    }
}
