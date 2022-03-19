package cz.weissar.base.data.services

import cz.weissar.base.data.model.response.DummyResponse
import retrofit2.Retrofit
import retrofit2.http.GET

interface DummyWebService {

    companion object {
        operator fun invoke(
            retrofit: Retrofit,
        ): DummyWebService = retrofit.create(DummyWebService::class.java)
    }

    @GET("posts")
    suspend fun getDummyResponseList(): List<DummyResponse>

}