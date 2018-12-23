package simple.reactive.api

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/mvc/customer-accounts")
class CustomerAccountController(val customerAccountRepository: ReactiveRepository<CustomerAccount, UUID>) {
    @GetMapping
    fun getAllCustomerAccounts(): Flux<CustomerAccount> = customerAccountRepository.findAll()

    @GetMapping("/{id}")
    fun getCustomerAccountById(@PathVariable("id") uuidString: String): Mono<CustomerAccount> =
            customerAccountRepository.findById(UUID.fromString(uuidString))

    @PostMapping
    fun addCustomerAccount(@RequestBody customerAccount: CustomerAccount): Mono<CustomerAccount> =
            customerAccountRepository.save(customerAccount)

    @DeleteMapping
    fun deleteAllCustomerAccounts(): Mono<Void> = customerAccountRepository.deleteAll()

    @DeleteMapping("/{id}")
    fun deleteCustomerAccountById(@PathVariable("id") uuidString: String): Mono<Void> =
            customerAccountRepository.deleteById(UUID.fromString(uuidString))
}
/*

@Configuration
class FunctionalRoutes(val customerAccountRepository: ReactiveRepository<CustomerAccount, UUID>) {
    @Bean
    fun routes() = router {
        GET("/func/customer-accounts") {
            ServerResponse.ok().body(customerAccountRepository.findAll(), CustomerAccount::class.java)
        }
    }
}*/
