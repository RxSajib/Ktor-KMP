package org.example.project.data.network.clint

import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.data.network.repository.Repository

object NetworkClint {

    val httpClint by lazy {
        HttpClient{
            install(Logging){
                level = LogLevel.ALL
            }

            install(ContentNegotiation){
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        coerceInputValues = true
                    }
                )
            }
        }
    }

     val repository by lazy {
        Repository(httpClint)
    }


}