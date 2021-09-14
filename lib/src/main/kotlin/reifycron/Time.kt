package reifycron

import java.time.LocalDateTime

class HourTime(val hour: Int, val minute: Int)

enum class Day {
    MON, TUE, WED, THU, FRI, SAT, SUN;
    companion object {
        fun format(days: Set<Day>) = days.joinToString(",") { (it.ordinal + 1).toString() }
    }
}

fun CronJob.previousExecution(date: LocalDateTime): LocalDateTime {
    return cron.prev(date)
}

fun CronJob.nextExecution(date: LocalDateTime): LocalDateTime {
    return cron.next(date)
}