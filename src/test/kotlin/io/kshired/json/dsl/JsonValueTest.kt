package io.kshired.json.dsl

import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test

class JsonValueTest {
    @Test
    fun `should create json string`() {
        val jsonValue = JsonValue.JsonString("string")

        jsonValue.toJsonString() shouldBeEqual "\"string\""
        jsonValue.toPrettyJsonString() shouldBeEqual "\"string\""
    }

    @Test
    fun `should create json number`() {
        val jsonValue = JsonValue.JsonNumber(1)

        jsonValue.toJsonString() shouldBeEqual "1"
        jsonValue.toPrettyJsonString() shouldBeEqual "1"
    }

    @Test
    fun `should create json boolean`() {
        val jsonValue = JsonValue.JsonBoolean(true)

        jsonValue.toJsonString() shouldBeEqual "true"
        jsonValue.toPrettyJsonString() shouldBeEqual "true"
    }

    @Test
    fun `should create json object`() {
        val jsonValue = JsonValue.JsonObject(mutableMapOf("key" to JsonValue.JsonString("value")))

        jsonValue.toJsonString() shouldBeEqual "{\"key\": \"value\"}"
        jsonValue.toPrettyJsonString() shouldBeEqual """
            {
                "key": "value"
            }
        """.trimIndent()
    }

    @Test
    fun `should create json array`() {
        val jsonValue = JsonValue.JsonArray(mutableListOf(JsonValue.JsonString("value")))

        jsonValue.toJsonString() shouldBeEqual "[\"value\"]"
        jsonValue.toPrettyJsonString() shouldBeEqual """
            [
                "value"
            ]
        """.trimIndent()
    }

    @Test
    fun `should create json null`() {
        val jsonValue = JsonValue.JsonNull

        jsonValue.toJsonString() shouldBeEqual "null"
        jsonValue.toPrettyJsonString() shouldBeEqual "null"
    }
}
