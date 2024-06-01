package io.kshired.json.dsl.builder

import io.kotest.matchers.equals.shouldBeEqual
import io.kshired.json.dsl.JsonValue
import org.junit.jupiter.api.Test

class JsonArrayBuilderTest {
    @Test
    fun `should create json array with string values`() {
        val jsonArray = jsonArray {
            +"string1"
            +"string2"
        }

        jsonArray.toJsonString() shouldBeEqual "[\"string1\",\"string2\"]"
        jsonArray.toPrettyJsonString() shouldBeEqual """
            [
                "string1",
                "string2"
            ]
        """.trimIndent()
    }

    @Test
    fun `should create json array with number values`() {
        val jsonArray = jsonArray {
            add(1)
            add(2)
            +JsonValue.JsonNumber(3)
        }

        jsonArray.toJsonString() shouldBeEqual "[1,2,3]"
        jsonArray.toPrettyJsonString() shouldBeEqual """
            [
                1,
                2,
                3
            ]
        """.trimIndent()
    }

    @Test
    fun `should create json array with object values`() {
        val jsonArray = jsonArray {
            jsonObject {
                "key1" to "value1"
            }
            jsonObject {
                "key2" to "value2"
            }
        }

        jsonArray.toJsonString() shouldBeEqual "[{\"key1\": \"value1\"},{\"key2\": \"value2\"}]"
        jsonArray.toPrettyJsonString() shouldBeEqual """
            [
                {
                    "key1": "value1"
                },
                {
                    "key2": "value2"
                }
            ]
        """.trimIndent()
    }

    @Test
    fun `should create json array with mixed values`() {
        val jsonArray = jsonArray {
            +"string1"
            add(1)
            +null
            +JsonValue.JsonNull
            jsonObject {
                "key1" to "value1"
            }
        }

        jsonArray.toJsonString() shouldBeEqual "[\"string1\",1,null,null,{\"key1\": \"value1\"}]"
        jsonArray.toPrettyJsonString() shouldBeEqual """
            [
                "string1",
                1,
                null,
                null,
                {
                    "key1": "value1"
                }
            ]
        """.trimIndent()
    }

    @Test
    fun `should create json array with nested array`() {
        val jsonArray = jsonArray {
            jsonArray {
                +"string1"
                add(1)
                jsonArray {
                    +"string2"
                    add(2)
                }
            }
            jsonArray {
                +"string3"
                add(3)
            }
        }

        jsonArray.toJsonString() shouldBeEqual "[[\"string1\",1,[\"string2\",2]],[\"string3\",3]]"
        jsonArray.toPrettyJsonString() shouldBeEqual """
            [
                [
                    "string1",
                    1,
                    [
                        "string2",
                        2
                    ]
                ],
                [
                    "string3",
                    3
                ]
            ]
        """.trimIndent()
    }
}
