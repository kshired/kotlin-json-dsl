package io.github.kshired.json.dsl.builder

import io.github.kshired.json.dsl.JsonValue

class JsonObjectBuilder(
    private val map: MutableMap<String, JsonValue> = mutableMapOf()
) {
    infix fun String.to(value: Any?) {
        if (value == null) {
            map[this] = JsonValue.JsonNull
            return
        }

        when (value) {
            is String -> map[this] = JsonValue.JsonString(value)
            is Number -> map[this] = JsonValue.JsonNumber(value)
            is Boolean -> map[this] = JsonValue.JsonBoolean(value)
            is JsonValue -> map[this] = value
            else -> throw IllegalArgumentException("Unsupported type: ${value::class.simpleName}")
        }
    }

    fun build(): JsonValue.JsonObject = JsonValue.JsonObject(map)
}

fun jsonObject(init: JsonObjectBuilder.() -> Unit): JsonValue.JsonObject {
    val builder = JsonObjectBuilder().apply(init)
    return builder.build()
}
