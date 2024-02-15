package io.github.aidenkoog.websocket.error_code.feature

import com.google.gson.annotations.SerializedName
import io.github.aidenkoog.websocket.error_code.base.Data

interface TestRepository {
    suspend fun testRepository(request: RequestModel): Data<ResponseModel>
}

data class RequestModel(
    @SerializedName("test") val test: String = "",
)

data class ResponseModel(
    @SerializedName("test") val test: String = "",
)