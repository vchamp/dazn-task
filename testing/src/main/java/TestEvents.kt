import com.vm.dazntask.core.data.LocalEvent
import java.time.LocalDateTime
import java.time.Month

val TEST_EVENTS = arrayOf(
    LocalEvent(
        "1",
        "Title 1",
        "Subtitle 1",
        LocalDateTime.of(2023, Month.MARCH, 29, 11, 0),
        "https://image.url/1",
        "https://video.url/1"
    ),
    LocalEvent(
        "2",
        "Title 2",
        "Subtitle 2",
        LocalDateTime.of(2023, Month.MARCH, 29, 10, 0),
        "https://image.url/2",
        "https://video.url/2"
    )
)