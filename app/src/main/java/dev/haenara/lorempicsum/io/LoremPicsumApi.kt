package dev.haenara.lorempicsum.io

import dev.haenara.lorempicsum.domain.data.Image
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LoremPicsumApi {
    /**
     * URL 예시
     * https://picsum.photos/v2/list?page=2&limit=100
     */
    @GET("v2/list")
    fun getRandomImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Single<List<Image>>
}
