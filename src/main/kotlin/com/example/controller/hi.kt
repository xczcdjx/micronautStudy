package com.example.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Todo")
@Controller("/todo")
open class HelloController {
    @Get(uri = "/hello", produces = [MediaType.TEXT_PLAIN])
    @Operation(summary = "test", description = "hello")
    open fun index(): String {
        return "hello world"
    }
}
