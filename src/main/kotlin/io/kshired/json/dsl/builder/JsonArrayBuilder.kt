package io.kshired.json.dsl.builder

import io.kshired.json.dsl.JsonValue

class JsonArrayBuilder(
    private val list: MutableList<JsonValue> = mutableListOf()
) {
    operator fun Any?.unaryPlus() {
        if (this == null) {
            list.add(JsonValue.JsonNull)
            return
        }

        when (this) {
            is String -> list.add(JsonValue.JsonString(this))
            is JsonValue -> list.add(this)
            else -> throw IllegalArgumentException("Unsupported type: ${this::class.simpleName}")
        }
    }

    fun add(value: String) {
        list.add(JsonValue.JsonString(value))
    }

    fun add(value: Number) {
        list.add(JsonValue.JsonNumber(value))
    }

    fun add(value: JsonValue) {
        list.add(value)
    }

    fun jsonObject(init: JsonObjectBuilder.() -> Unit) {
        val builder = JsonObjectBuilder().apply(init)
        list.add(builder.build())
    }

    fun build(): JsonValue.JsonArray = JsonValue.JsonArray(list)
}

fun jsonArray(init: JsonArrayBuilder.() -> Unit): JsonValue.JsonArray {
    val builder = JsonArrayBuilder().apply(init)
    return builder.build()
}
