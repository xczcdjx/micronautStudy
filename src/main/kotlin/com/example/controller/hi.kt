package com.example.controller

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.swagger.v3.oas.annotations.Operation
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

@Introspected
data class ReUser(val id:Int,val name:String,val sex:String)
@Introspected
data class ResU(val users:List<ReUser>)
@Tag(name = "Todo")
@Controller("/todo")
open class HelloController(@Inject private val con:Database) {
    @Get(uri = "/hello")
    @Operation(summary = "test", description = "hello")
    @Produces(MediaType.APPLICATION_JSON)
    open fun index(): ResU {
        val res=con.from(User).select().map { row ->
            ReUser(
                id = row[User.id]!!,
                name = row[User.name]!!,
                sex = row[User.sex]!!
            )
        }
        return ResU(res)
    }
}
