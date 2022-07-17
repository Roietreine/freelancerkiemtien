package refractnginamo.pls.potapaupload.jumpcode_features.network.repository

import refractnginamo.pls.potapaupload.jumpcode_features.network.data.Request
import refractnginamo.pls.potapaupload.jumpcode_features.network.data.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface JumpService {

    @POST("app_conf")
    suspend fun getJumpCode(@Body param : Request): Response
}