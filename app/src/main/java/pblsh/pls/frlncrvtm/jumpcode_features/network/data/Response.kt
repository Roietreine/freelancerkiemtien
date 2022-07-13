package pblsh.pls.frlncrvtm.jumpcode_features.network.data

import androidx.annotation.Keep

@Keep
data class Response(

    val code: String? = null,
    val msg: String? = null,
    val data: DataModel? = null
) {
    @Keep
    data class DataModel(
        val appName: String? = null,
        val packageName: String? = null,
        val jump: Boolean = false,
        val jumpAddress: String? = null
    )
}
