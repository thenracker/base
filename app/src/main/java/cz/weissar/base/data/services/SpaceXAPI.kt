package cz.weissar.base.data.services

import cz.weissar.base.data.model.response.RocketLaunch
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

interface SpaceXAPI {

    val httpClient: HttpClient

    suspend fun getAllLaunches() =
        httpClient.get<List<RocketLaunch>>(GetLaunchesEndpoint)

    companion object {
        private const val GetLaunchesEndpoint = "https://api.spacexdata.com/v3/launches"

        operator fun invoke() = object : SpaceXAPI {
            override val httpClient = HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(
                        kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
                    )
                }
            }
        }
    }
}