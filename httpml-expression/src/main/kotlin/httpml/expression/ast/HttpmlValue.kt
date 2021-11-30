package httpml.expression.ast

data class HttpmlValue(
    val chunks: List<HttpmlChunk>
): HttpmlChunk
