package com.example

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
	info = Info(
		title = "Demokt",
		description = "Todolist",
	)
)
object Application {

	@JvmStatic
	fun main(args: Array<String>) {
		Micronaut.run(Application.javaClass)
	}
}

