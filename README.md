# mailu-api
An easy to use Java client for https://github.com/maxa-ondrej/mailu-api-server

[v1.0.0](https://jitpack.io/#maxa-ondrej/mailu-api/v1.0.0)

## Installation
```gradle
repositories {
  maven { url 'https://jitpack.io' }
}

dependencies {
  implementation 'com.github.maxa-ondrej:mailu-api:v1.0.0'
}
```

## Usage
```java
public class Main {
    public static void main(String[] args) {
        final String username = "";
        final String password = "";
        final Mailu mailu = ClientKt.createMailu(username, password, "http://mailu_api_1").join();
        // use the mailu client here
    }
}
```
