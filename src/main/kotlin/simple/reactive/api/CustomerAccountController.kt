package simple.reactive.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/mvc/customer-accounts")
class CustomerAccountController(val customerAccountRepository: ReactiveRepository<CustomerAccount>) {
    @GetMapping
    fun allCustomerAccounts(): Flux<CustomerAccount> {
        return customerAccountRepository.findAll()
    }

    @PostMapping
    fun addCustomerAccount(@RequestBody customerAccount: CustomerAccount): Mono<CustomerAccount> {
        return customerAccountRepository.save(customerAccount)
    }
}

@Configuration
class FunctionalRoutes(val customerAccountRepository: ReactiveRepository<CustomerAccount>) {
    @Bean
    fun routes() = router {
        GET("/func/customer-accounts") {
            ServerResponse.ok().body(customerAccountRepository.findAll(), CustomerAccount::class.java)
        }
    }
}