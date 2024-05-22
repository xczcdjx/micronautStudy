package com.example.db

import io.micronaut.context.annotation.ConfigurationProperties

import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton
import org.ktorm.database.Database

@ConfigurationProperties("datasources.default")
class DatasourceConfig {
    var url: String? = null
    var driverClassName: String? = null
    var username: String? = null
    var password: String? = null
}

@Factory
class DatabaseConfig {
    @Singleton
    fun ktormDatabase(config: DatasourceConfig): Database {
        println(config.username)
        return Database.connect(
            url = config.url!!,
            driver = config.driverClassName!!,
            user = config.username!!,
            password = config.password!!
        )
    }
}