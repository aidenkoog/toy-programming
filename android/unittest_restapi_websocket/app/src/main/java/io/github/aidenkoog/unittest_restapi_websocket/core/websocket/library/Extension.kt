package io.github.aidenkoog.unittest_restapi_websocket.core.websocket.library

import android.util.Log
import java.lang.reflect.Array
import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable
import java.lang.reflect.WildcardType

fun Type.getAboutRawType(): Class<*> = getRawType(this)

fun getRawType(type: Type): Class<*> {

    if (type is Class<*>) {
        // Type is a normal class.
        return type
    }
    if (type is ParameterizedType) {

        // I'm not exactly sure why getRawType() returns Type instead of Class. Neal isn't either but
        // suspects some pathological case related to nested classes exists.
        val rawType = type.rawType
        require(rawType is Class<*>)
        return rawType
    }
    if (type is GenericArrayType) {
        val componentType = type.genericComponentType
        return Array.newInstance(getRawType(componentType), 0).javaClass
    }
    if (type is TypeVariable<*>) {
        // We could use the variable's bounds, but that won't work if there are multiple. Having a raw
        // type that's more general than necessary is okay.
        return Any::class.java
    }
    if (type is WildcardType) {
        return getRawType(type.upperBounds[0])
    }
    throw IllegalArgumentException(
        "Expected a Class, ParameterizedType, or " + "GenericArrayType, but <" + type + "> is of type " + type.javaClass.name
    )
}

fun ParameterizedType.getParameterUpperBound(index: Int): Type = getParameterUpperBound(index, this)

fun getParameterUpperBound(index: Int, type: ParameterizedType): Type {
    val types = type.actualTypeArguments
    require(!(index < 0 || index >= types.size)) { "Index " + index + " not in range [0," + types.size + ") for " + type }
    val paramType = types[index]
    return if (paramType is WildcardType) {
        paramType.upperBounds[0]
    } else paramType
}

inline fun getSocketService(init: WebSocketController.Builder.() -> Unit): WebSocketController {
    val controller = WebSocketController.Builder()
    controller.init()
    return controller.build()
}

internal fun webSocketLog(value: String) {
    Log.d("WebSocketLog :: ", value)
}