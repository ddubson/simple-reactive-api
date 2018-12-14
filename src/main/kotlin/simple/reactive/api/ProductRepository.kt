package simple.reactive.api

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductRepository {
    fun addOne(product: Product): Mono<Product>

    fun fetchAll(): Flux<Product>
}

class InMemoryReactiveProductRepository: ProductRepository {
    private val inMemoryStore: MutableMap<Long, Product> = mutableMapOf()

    override fun addOne(product: Product): Mono<Product> {
        inMemoryStore[product.id] = product
        return Mono.just(product)
    }

    override fun fetchAll(): Flux<Product> {
        return Flux.fromIterable(inMemoryStore.values)
    }
}