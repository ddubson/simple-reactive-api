package simple.reactive.api

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Flux
import kotlin.random.Random.Default.nextLong

@SpringBootApplication
class App : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        Flux
                .generate<Product> {
                    val generatedId = nextLong(1, 5_000)
                    it.next(Product(generatedId, "Product $generatedId"))
                }
                .take(10)
                .flatMap {
                    this.productRepository().addOne(it)
                }
                .thenMany(this.productRepository().fetchAll())
                .subscribe {
                    println("Product [id: ${it.id}] added. ")
                }
    }

    @Bean
    fun productRepository(): ProductRepository = InMemoryReactiveProductRepository()
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
