# Kotlin-Json-DSL

This is a simple Kotlin DSL for creating JSON strings.

## Usage

```kotlin
val json = jsonObject {
    "name" to "John"
    "age" to 30
    "isStudent" to true
    "address" to jsonObject {
        "street" to "Main St"
        "city" to "New York"
    }
    "friends" to jsonArray {
        jsonObject {
            "name" to "Alice"
            "age" to 25
        }
        jsonObject {
            "name" to "Bob"
            "age" to 35
        }
    }
}

val jsonString = json.toJsonString()
val prettyJsonString = json.toPrettyJsonString()
```

Output of the above code:

- `jsonString`:

```json
{"name":"John","age":30,"isStudent":true,"address":{"street":"Main St","city":"New York"},"friends":[{"name":"Alice","age":25},{"name":"Bob","age":35}]}
```

- `prettyJsonString`:

```json
{
  "name": "John",
  "age": 30,
  "isStudent": true,
  "address": {
    "street": "Main St",
    "city": "New York"
  },
  "friends": [
    {
      "name": "Alice",
      "age": 25
    },
    {
      "name": "Bob",
      "age": 35
    }
  ]
}
```
