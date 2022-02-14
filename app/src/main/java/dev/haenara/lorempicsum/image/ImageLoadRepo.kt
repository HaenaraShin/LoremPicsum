package dev.haenara.lorempicsum.image

import dev.haenara.lorempicsum.domain.LoadImageUseCase
import dev.haenara.lorempicsum.domain.data.Images
import dev.haenara.lorempicsum.io.LoremPicsumApi
import io.reactivex.rxjava3.core.Single

class ImageLoadRepo(private val api: LoremPicsumApi) {
    fun loadImages(
        page: Int = 1,
        limit: Int = LoadImageUseCase.LIMIT
    ): Single<Images> {
        return api.getRandomImages(page, limit)
            .map { Images(it) }
    }
}
