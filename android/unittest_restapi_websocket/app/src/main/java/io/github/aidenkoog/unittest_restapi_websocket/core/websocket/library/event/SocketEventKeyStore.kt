package io.github.aidenkoog.unittest_restapi_websocket.core.websocket.library.event

import io.github.aidenkoog.unittest_restapi_websocket.core.websocket.core.Converter
import io.github.aidenkoog.unittest_restapi_websocket.core.websocket.core.IMapper
import kotlinx.coroutines.CoroutineScope
import java.lang.reflect.Type

class SocketEventKeyStore {

    private val cache = mutableMapOf<EventKey, IMapper<*>>()

    fun findEventMapper(
        returnType: Type,
        annotations: Array<Annotation>,
        converter: Converter<*>,
        coroutineScope: CoroutineScope,
        eventFactory: IMapper.Factory
    ): IMapper<*> {
        val key = EventKey(returnType, annotations)

        if (cache.containsKey(key)) {
            return cache[key]!!
        }

        val eventMapper = eventFactory.create(converter, coroutineScope)

        cache[key] = eventMapper

        return eventMapper
    }

}

data class EventKey(
    val returnType: Type, val annotations: Array<Annotation>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EventKey

        if (returnType != other.returnType) return false
        return annotations.contentEquals(other.annotations)
    }

    override fun hashCode(): Int {
        var result = returnType.hashCode()
        result = 31 * result + annotations.contentHashCode()
        return result
    }
}