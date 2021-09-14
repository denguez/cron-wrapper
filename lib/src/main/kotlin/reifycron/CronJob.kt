package reifycron

import it.justwrote.kjob.KronJob
import it.justwrote.kjob.dsl.JobContext
import java.time.ZonedDateTime

typealias CronJobExecution = suspend JobContext<CronJob>.() -> Unit

open class CronJob(name: String, expression: String) : KronJob(name, expression) {
    val cron = CronUtils.parse(expression)
    val executionTime = CronUtils.executionTime(cron)

    fun lastExecution(date: ZonedDateTime): ZonedDateTime? {
        return executionTime.lastExecution(date).orElseGet(null)
    }
    
    fun nextExecution(date: ZonedDateTime): ZonedDateTime? {
        return executionTime.nextExecution(date).orElseGet(null)
    }
}