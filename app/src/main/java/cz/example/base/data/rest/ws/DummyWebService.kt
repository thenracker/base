package cz.example.base.data.rest.ws

import cz.example.base.data.rest.dto.DummyResponse
import retrofit2.Retrofit
import retrofit2.http.GET

interface DummyWebService {

    companion object {
        operator fun invoke(
            retrofit: Retrofit
        ): DummyWebService {
            return retrofit
                .create(DummyWebService::class.java)
        }
    }

    @GET("posts")
    suspend fun getDummyResponseList(): List<DummyResponse>

}