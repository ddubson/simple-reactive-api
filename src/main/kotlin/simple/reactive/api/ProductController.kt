package simple.reactive.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ProductController(val productRepository: ProductRepository) {
    @GetMapping("/products")
    fun allProducts(): Flux<Product> {
        return productRepository.fetchAll()
    }
}