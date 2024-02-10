package io.github.aidenkoog.websocket.websocket_coroutine.core.event

import com.google.gson.annotations.SerializedName

/**
 * GENERAL --> Normal WebSocket
 */
enum class RequestType {
    GENERAL, STOMP_SUBSCRIBE, STOMP_SEND,
}

sealed interface EventRequest {
    val typeOfRequest: RequestType
}

data class WebSocketRequest(
    val msg: String
) : EventRequest {
    override val typeOfRequest: RequestType = RequestType.GENERAL
}

data class StompSendRequest(
    @SerializedName("command") val command: String,

    @SerializedName("destination") val destination: String,

    @SerializedName("payload") val payload: String? = "",
) : EventRequest {
    override val typeOfRequest: RequestType = RequestType.STOMP_SEND
}

data class StompSubscribeRequest(
    @SerializedName("subscribe") val subscribe: Boolean,

    @SerializedName("destination") val destination: String,

    @SerializedName("payload") val payload: String? = "",
) : EventRequest {
    override val typeOfRequest: RequestType = RequestType.STOMP_SUBSCRIBE
}