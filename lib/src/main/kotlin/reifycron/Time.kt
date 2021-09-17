package reifycron

class HourTime(val hour: Int, val minute: Int)

enum class Day {
    Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
    companion object {
        fun format(days: Set<Day>) = days.joinToString(",") { 
            it.name.slice(IntRange(0, 3)).uppercase()
        }
    }
}