package io.kshired.json.dsl


sealed class JsonValue {
    data class JsonString(val value: String) : JsonValue()
    data class JsonNumber(val value: Number) : JsonValue()
    data class JsonBoolean(val value: Boolean) : JsonValue()
    data class JsonObject(val value: MutableMap<String, JsonValue>) : JsonValue()
    data class JsonArray(val value: MutableList<JsonValue>) : JsonValue()
    data object JsonNull : JsonValue()

    fun toJsonString(indent: String = "", currentIndent: String = "", newLine: String = ""): String {
        val nextIndent = currentIndent + indent

        return when (this) {
            is JsonString -> "\"${this.value}\""
            is JsonNumber -> this.value.toString()
            is JsonBoolean -> this.value.toString()
            is JsonObject -> this.value.entries.joinToString(
                prefix = "{$newLine", postfix = "$newLine$currentIndent}", separator = ",$newLine"
            ) { "${nextIndent}\"${it.key}\": ${it.value.toJsonString(indent, nextIndent, newLine)}" }

            is JsonArray -> this.value.joinToString(
                prefix = "[$newLine", postfix = "$newLine$currentIndent]", separator = ",$newLine"
            ) { "${nextIndent}${it.toJsonString(indent, nextIndent, newLine)}" }

            JsonNull -> "null"
        }
    }

    fun toPrettyJsonString(): String {
        return toJsonString("    ", newLine = "\n")
    }
}
