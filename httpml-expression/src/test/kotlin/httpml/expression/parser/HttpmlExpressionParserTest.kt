package httpml.expression.parser

import io.kotest.core.spec.style.StringSpec
import java.io.StringReader

class HttpmlExpressionParserTest: StringSpec({

    fun p(str: String) = HttpmlExpressionParser(StringReader(str))

    "must parse identifier" {

        val node = p("(a+b)*(c+4);").Start()
        node.dump("")

    }

})