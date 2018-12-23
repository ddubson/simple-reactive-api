package simple.reactive.api

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ReactiveRepository<T, ID> {
    fun save(entity: T): Mono<T>

    fun findAll(): Flux<T>

    fun findById(id: ID): Mono<T>

    fun deleteById(id: ID): Mono<Void>

    fun deleteAll(): Mono<Void>
}

