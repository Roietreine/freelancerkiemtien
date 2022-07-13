package pblsh.pls.frlncrvtm.jumpcode_features.network.repository

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pblsh.pls.frlncrvtm.jumpcode_features.network.data.Request
import pblsh.pls.frlncrvtm.jumpcode_features.network.data.Response
import pblsh.pls.frlncrvtm.jumpcode_features.network.di.RetrofitHelper

class JumpServiceImpl {

    private val service : JumpService = RetrofitHelper.service()

    suspend fun getJumpCodeUrl(param : Request): Flow<Response> = callbackFlow {
        trySend(service.getJumpCode(param))
        awaitClose()
    }
}