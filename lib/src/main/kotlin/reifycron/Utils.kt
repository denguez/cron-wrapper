package reifycron

import com.cronutils.model.definition.CronDefinitionBuilder
import com.cronutils.parser.CronParser
import com.cronutils.model.time.ExecutionTime
import com.cronutils.model.Cron
import com.cronutils.descriptor.CronDescriptor
import java.util.Locale

object CronUtils {
    val definition by lazy {
        CronDefinitionBuilder.defineCron().run {
            withSeconds().and()
            withMinutes().and()
            withHours().and()
            withDayOfMonth()
                .supportsL()
                .supportsW()
                .supportsHash()
                .supportsQuestionMark()
                .and()
            withMonth().and()
            withDayOfWeek()
                //.withIntMapping(7, 0) // we support non-standard non-zero-based numbers!
                .supportsL()
                .supportsW()
                .supportsHash()
                .supportsQuestionMark()
                .and()
            withYear()
                .optional()
                .and()
            instance()
        }
    }

    val parser by lazy {
        CronParser(definition);
    }

    val descriptor by lazy {
        CronDescriptor.instance(Locale.UK);
    }

    fun parse(expression: String) =  parser.parse(expression)

    fun executionTime(cron: Cron) = ExecutionTime.forCron(cron)

    fun executionTime(expression: String): ExecutionTime {
        return ExecutionTime.forCron(parser.parse(expression))
    }

    fun describe(cron: Cron) = descriptor.describe(cron)

    fun describe(expression: String): String {
        return descriptor.describe(parser.parse(expression))
    }
}
