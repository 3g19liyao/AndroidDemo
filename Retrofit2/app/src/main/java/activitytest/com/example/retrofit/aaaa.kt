package activitytest.com.example.retrofit

data class aaaa(
    val desc: String,
    val result: Result,
    val statusCode: String
)

data class Result(
    val areacode: String,
    val card: String,
    val city: String,
    val company: String,
    val province: String,
    val zip: String
)