package simple.reactive.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/mvc/products")
class ProductController(val productRepository: ProductRepository) {
    @GetMapping
    fun allProducts(): Flux<Product> {
        return productRepository.fetchAll()
    }

    @PostMapping
    fun addProduct(@RequestBody product: Product): Mono<Product> {
        return productRepository.addOne(product)
    }
}

@Configuration
class FunctionalRoutes(val productRepository: ProductRepository) {
    @Bean
    fun routes() = router {
        GET("/func/products") {
            ServerResponse.ok().body(productRepository.fetchAll(), Product::class.java)
        }
    }
}