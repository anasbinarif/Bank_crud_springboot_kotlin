package com.example.banks_spring_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.client.RestTemplate
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@EnableJpaAuditing
@SpringBootApplication
class BanksSprinKotlinApplication {

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

}

fun main(args: Array<String>) {
    runApplication<BanksSprinKotlinApplication>(*args)
}
