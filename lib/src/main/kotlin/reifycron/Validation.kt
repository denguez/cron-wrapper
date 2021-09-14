package reifycron

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
