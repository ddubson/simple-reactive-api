package simple.reactive.api

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.*

class InMemoryCustomerAccountReactiveRepository: ReactiveRepository<CustomerAccount, UUID> {
    private val inMemoryStore: MutableMap<UUID, CustomerAccount> = mutableMapOf()

    override fun findById(id: UUID): Mono<CustomerAccount> = Mono.justOrEmpty(inMemoryStore[id])

    override fun deleteById(id: UUID): Mono<Void> {
        inMemoryStore.remove(id)
        return Mono.empty()
    }

    override fun deleteAll(): Mono<Void> {
        inMemoryStore.clear()
        return Mono.empty()
    }

    override fun save(entity: CustomerAccount): Mono<CustomerAccount> {
        inMemoryStore[entity.id] = entity
        return Mono.just(entity)
    }

    override fun findAll(): Flux<CustomerAccount> =
            Flux.fromIterable(inMemoryStore.values).delayElements(Duration.ofMillis(200))
}