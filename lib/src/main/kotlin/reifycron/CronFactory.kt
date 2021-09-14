package reifycron

object CronFactory {
    fun everyMinute(minute: Int, name: String): CronJob {
        requireNatural(minute)
        val expression = "0 0/$minute * * * ?"
        return CronJob(name, expression)
    }

    fun everyHour(hour: Int, minute: Int = 0, name: String): CronJob {
        requireNatural(hour)
        requireMinuteRange(minute)
        val expression = "0 $minute 0/$hour * * ?"
        return CronJob(name, expression)
    }

    fun daily(time: HourTime, name: String): CronJob {
        requireTimeRange(time)
        val expression = "0 ${time.minute} ${time.hour} * * ?"
        return CronJob(name, expression)
    }

    fun weekly(time: HourTime, days: Set<Day>, name: String): CronJob {
        requireTimeRange(time)
        require(days.isNotEmpty()) { "Empty day set" }
        val expression = "0 ${time.minute} ${time.hour} ? * ${Day.format(days)}}"
        return CronJob(name, expression)
    }
}

internal fun requireRange(number: Int, min: Int, max: Int = Int.MAX_VALUE) {
    require(min <= number && number < max) { "Number must be in range $min-$max" }
}

internal fun requirePositive(number: Int, max: Int = Int.MAX_VALUE) = requireRange(number, 0, max)

internal fun requireNatural(number: Int, max: Int = Int.MAX_VALUE) = requireRange(number, 1, max)

internal fun requireMinuteRange(minutes: Int) = requirePositive(minutes, max = 60)

internal fun requireHourRange(hours: Int) = requirePositive(hours, max = 24)

internal fun requireTimeRange(time: HourTime) {
    requireHourRange(time.hour)
    requireMinuteRange(time.minute)
}
