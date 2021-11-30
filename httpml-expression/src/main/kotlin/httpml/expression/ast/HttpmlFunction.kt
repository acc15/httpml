package httpml.expression.ast

data class HttpmlFunction(
    val name: HttpmlChunk,
    val parameters: List<HttpmlParameter>
) : HttpmlChunk
