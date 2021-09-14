package reifycron

import it.justwrote.kjob.Mongo
import it.justwrote.kjob.kjob
import it.justwrote.kjob.kron.Kron
import it.justwrote.kjob.kron.KronModule

class CronService(private val mongoConnectionURL: String ) {
    private val service by lazy {
        kjob(Mongo) {
            extension(KronModule)
            connectionString = mongoConnectionURL
            databaseName = "reify-cron"
            jobCollection = "reify-jobs"
            lockCollection = "reify-locks"
        }
    }

    fun start() = service.start()
    
    fun shutdown() = service.shutdown()

    fun schedule(job: CronJob, block: CronJobExecution) {
        service(Kron).kron(job) {
            maxRetries = 3
            execute(block)
        }
    }
}
