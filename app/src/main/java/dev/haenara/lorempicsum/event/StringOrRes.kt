package dev.haenara.lorempicsum.event

import android.content.Context
import androidx.annotation.StringRes

data class StringOrRes private constructor(
    val string: String? = null,
    @StringRes val resource: Int? = null
) {
    constructor(string: String) : this(string, null)
    constructor(@StringRes res: Int) : this(null, res)
}

fun Context.toString(stringOrResource: StringOrRes): String {
    return stringOrResource.string
        ?: getString(stringOrResource.resource!!)
}
