# Kotlin-Json-DSL

[<img src="https://img.shields.io/maven-central/v/io.github.kshired/kotlin-json-dsl.svg?label=latest%20release"/>](https://search.maven.org/search?q=g:io.github.kshired)
![GitHub](https://img.shields.io/github/license/kshired/kotlin-json-dsl)

Simple Kotlin DSL library for creating JSON strings.

## Installation

### Gradle

```groovy
dependencies {
    implementation 'io.github.kshired:kotlin-json-dsl:0.0.1'
}
```

### Gradle(kotlin)

```kotlin
dependencies {
    implementation("io.github.kshired:kotlin-json-dsl:0.0.1")
}
```

### Maven

```xml
<dependency>
    <groupId>io.github.kshired</groupId>
    <artifactId>kotlin-json-dsl</artifactId>
    <version>0.0.1</version>
</dependency>
```

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
