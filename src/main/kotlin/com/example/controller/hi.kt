package com.example.controller

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.serde.annotation.Serdeable
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject
import org.ktorm.database.Database
import org.ktorm.dsl.forEach
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object User : Table<Nothing>("user") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val sex = varchar("sex")
}

@Serdeable
data class ReUser(
    @Schema(description = "用户ID", example = "1")
    val id: Int,
    @Schema(description = "用户名", example = "John Doe")
    val name: String,
    @Schema(description = "用户性别", example = "男")
    val sex: String
)

@Serdeable
data class ResU(
    @Schema(description = "用户")
    val users: List<ReUser>)

data class Res(
    val msg:String="success",
    val data:Any,
    val code:Int=200
)

@Tag(name = "Todo")
@Controller("/todo")
open class HelloController(@Inject private val con: Database) {
    @Get(uri = "/hello")
    @Operation(summary = "test", description = "hello")
    @Produces(MediaType.APPLICATION_JSON)
    open fun index(): ResU {
        val res = con.from(User).select().map { row ->
            ReUser(
                id = row[User.id]!!,
                name = row[User.name]!!,
                sex = row[User.sex]!!
            )
        }
        return ResU(res)
    }
}
