package io.kshired.json.dsl.builder

import io.kotest.matchers.equals.shouldBeEqual
import io.kshired.json.dsl.JsonValue
import org.junit.jupiter.api.Test

class JsonObjectBuilderTest {
    @Test
    fun `should create json object with string values`() {
        val jsonObject = jsonObject {
            "key1" to "value1"
            "key2" to "value2"
        }

        jsonObject.toJsonString() shouldBeEqual "{\"key1\": \"value1\",\"key2\": \"value2\"}"
        jsonObject.toPrettyJsonString() shouldBeEqual """
            {
                "key1": "value1",
                "key2": "value2"
            }
        """.trimIndent()
    }

    @Test
    fun `should create json object with number values`() {
        val jsonObject = jsonObject {
            "key1" to 1
            "key2" to 2
        }

        jsonObject.toJsonString() shouldBeEqual "{\"key1\": 1,\"key2\": 2}"
        jsonObject.toPrettyJsonString() shouldBeEqual """
            {
                "key1": 1,
                "key2": 2
            }
        """.trimIndent()
    }

    @Test
    fun `should create json object with boolean values`() {
        val jsonObject = jsonObject {
            "key1" to true
            "key2" to false
        }

        jsonObject.toJsonString() shouldBeEqual "{\"key1\": true,\"key2\": false}"
        jsonObject.toPrettyJsonString() shouldBeEqual """
            {
                "key1": true,
                "key2": false
            }
        """.trimIndent()
    }

    @Test
    fun `should create json object with object values`() {
        val jsonObject = jsonObject {
            "key1" to jsonObject {
                "nestedKey1" to "nestedValue1"
            }
            "key2" to jsonObject {
                "nestedKey2" to "nestedValue2"
            }
        }

        jsonObject.toJsonString() shouldBeEqual "{\"key1\": {\"nestedKey1\": \"nestedValue1\"},\"key2\": {\"nestedKey2\": \"nestedValue2\"}}"
        jsonObject.toPrettyJsonString() shouldBeEqual """
            {
                "key1": {
                    "nestedKey1": "nestedValue1"
                },
                "key2": {
                    "nestedKey2": "nestedValue2"
                }
            }
        """.trimIndent()
    }

    @Test
    fun `should create json object with mixed values`() {
        val jsonObject = jsonObject {
            "key1" to "value1"
            "key2" to 2
            "key3" to false
            "key4" to jsonObject {
                "nestedKey1" to "nestedValue1"
            }
            "key5" to jsonArray {
                +"string1"
                add(1)
                jsonObject {
                    "nestedKey2" to "nestedValue2"
                }
                +null
                +JsonValue.JsonNull
            }
            "key6" to null
        }

        jsonObject.toJsonString() shouldBeEqual "{\"key1\": \"value1\",\"key2\": 2,\"key3\": false,\"key4\": {\"nestedKey1\": \"nestedValue1\"},\"key5\": [\"string1\",1,{\"nestedKey2\": \"nestedValue2\"},null,null],\"key6\": null}"
        jsonObject.toPrettyJsonString() shouldBeEqual """
            {
                "key1": "value1",
                "key2": 2,
                "key3": false,
                "key4": {
                    "nestedKey1": "nestedValue1"
                },
                "key5": [
                    "string1",
                    1,
                    {
                        "nestedKey2": "nestedValue2"
                    },
                    null,
                    null
                ],
                "key6": null
            }
        """.trimIndent()
    }
}
