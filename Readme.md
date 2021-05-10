# Graphql client Demo project with Spring boot and WebClient

Graphql API source :

https://countries.trevorblades.com

curl request :

```
curl --location --request POST 'https://countries.trevorblades.com/' \
--header 'conte: application/json' \
--header 'Content-Type: application/json' \
--data-raw ''{"operationName":null,"variables":{},"query":"{\n  country(code: \"BE\") {\n    name\n    capital\n    currency\n  }\n}\n"}'
```

response :

```
{
    "data": {
        "country": {
            "name": "Belgium",
            "capital": "Brussels",
            "currency": "EUR"
        }
    }
}
```

Countries project :

https://annexare.github.io/Countries/
