package simple.reactive.api

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Flux
import java.math.BigDecimal
import java.util.*

@SpringBootApplication
class App : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        Flux
                .generate<CustomerAccount> {
                    it.next(CustomerAccount(UUID.randomUUID(), "Checking", BigDecimal.valueOf(20.00)))
                }
                .take(10)
                .flatMap {
                    this.customerAccountRepository().save(it)
                }
                .thenMany(this.customerAccountRepository().findAll())
                .subscribe {
                    println("Customer Account [id: ${it.id}] added. ")
                }
    }

    @Bean
    fun customerAccountRepository(): ReactiveRepository<CustomerAccount> =
            InMemoryCustomerAccountReactiveRepository()
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
