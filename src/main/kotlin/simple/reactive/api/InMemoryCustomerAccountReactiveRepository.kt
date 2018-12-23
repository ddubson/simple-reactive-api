package simple.reactive.api

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.*

class InMemoryCustomerAccountReactiveRepository: ReactiveRepository<CustomerAccount, UUID> {
    private val inMemoryStore: MutableMap<UUID, CustomerAccount> = mutableMapOf()

    override fun findById(id: UUID): Mono<CustomerAccount> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteById(id: UUID): Mono<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll(): Mono<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(entity: CustomerAccount): Mono<CustomerAccount> {
        inMemoryStore[entity.id] = entity
        return Mono.just(entity)
    }

    override fun findAll(): Flux<CustomerAccount> {
        return Flux.fromIterable(inMemoryStore.values)
                .delayElements(Duration.ofMillis(500))
    }
}