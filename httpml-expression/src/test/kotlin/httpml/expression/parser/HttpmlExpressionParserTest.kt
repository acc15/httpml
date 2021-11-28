package httpml.expression.parser

import io.kotest.core.spec.style.StringSpec
import java.io.StringReader

class HttpmlExpressionParserTest: StringSpec({

    fun p(str: String): HttpmlExpressionParser {
        println("Input: $str")
        return HttpmlExpressionParser(StringReader(str))
    }

    "must parse plain text" {
        val t = p("Some text").parseTemplate()
        println("Plain: $t")
    }

    "must parse escaped text" {
        val t = p("Some \\n\\r \\\${\\} text").parseTemplate()
        println("Escaped: $t")
    }

})
