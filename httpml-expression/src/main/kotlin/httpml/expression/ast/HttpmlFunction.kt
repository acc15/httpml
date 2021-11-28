package httpml.expression.ast

data class HttpmlParameter(
    val name: String,
    val value: HttpmlChunk
)

data class HttpmlFunction(
    val name: String,
    val parameters: List<HttpmlParameter>
) : HttpmlChunk
