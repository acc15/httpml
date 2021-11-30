package httpml.expression.ast

import java.io.Writer

interface HttpmlChunk {

    fun writeTo(writer: Writer) {
    }

}

