package dev.haenara.lorempicsum.domain.data

import java.io.Serializable

data class Images(val list: List<Image>) : List<Image> by list, Serializable
