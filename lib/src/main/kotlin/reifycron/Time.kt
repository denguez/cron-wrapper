package reifycron

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class HourTime(val hour: Int, val minute: Int)

enum class Day {
    MON, TUE, WED, THU, FRI, SAT, SUN
}

fun format(days: Set<Day>) =  
    days.joinToString(",") { (it.ordinal + 1).toString() }