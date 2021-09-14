package reifycron

import it.justwrote.kjob.KronJob
import it.justwrote.kjob.dsl.JobContext

typealias CronJobExecution = suspend JobContext<CronJob>.() -> Unit

class CronJob(name: String, expression: String) : KronJob(name, expression) {
    companion object {
        suspend fun everyMinute(minute: Int, name: String): CronJob {
            requireNatural(minute)
            val expression = "0 ${now().minute + 1}/$minute * * * ?"
            return CronJob(name, expression)
        }

        suspend fun everyHour(hour: Int, minute: Int = 0, name: String): CronJob {
            requireNatural(hour)
            requireMinuteRange(minute)
            val expression = "0 $minute 0/$hour * * ?"
            return CronJob(name, expression)
        }

        suspend fun daily(time: HourTime, name: String): CronJob {
            requireTimeRange(time)
            val expression = "0 ${time.minute} ${time.hour} * * ?"
            return CronJob(name, expression)
        }

        suspend fun weekly(time: HourTime, days: Set<Day>, name: String): CronJob {
            requireTimeRange(time)
            require(days.isNotEmpty()) { "Empty day set" }
            val expression = "0 ${time.minute} ${time.hour} ? * ${format(days)}}"
            return CronJob(name, expression)
        }
    }
}