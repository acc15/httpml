package httpml.expression.parser

import httpml.expression.ast.HttpmlText
import httpml.expression.ast.HttpmlValue
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.property.forAll
import java.io.StringReader

class HttpmlExpressionParserTest: StringSpec({

    fun inExpression(str: String): HttpmlExpressionParser {
        val parser = HttpmlExpressionParser(HttpmlExpressionParserTokenManager(
            SimpleCharStream(StringReader(str), 1, 1),
            HttpmlExpressionParserConstants.IN_EXPRESSION)
        )
        return parser
    }

    "must parse escapes in expression" {
        forAll(
            row("\\b", '\b'),
            row("\\r", '\r'),
            row("\\n", '\n'),
            row("\\t", '\t'),
            row("\\f", '\u000C'),
            row("\\0", 0.toChar()),
            row("\\012", '\n'),
            row("\\udead", '\udead'),
            row("\\\\", '\\'),
            row("\\$", '$'),
            row("\\'", '\''),
        ) { input, expectedChar ->
            inExpression(input).singleChar() shouldBe expectedChar
        }
    }

    "must parse text with escapes" {
        forAll(
            row("abc\\n", "abc\n"),
            row("x\\u000ayz", "x\nyz"),
            row("  x  two spaces \t  ", "x  two spaces"),
            row(" \t text is trimmed   \r\n", "text is trimmed")
        ) { input, expected ->
            val chunks = inExpression(input).value().chunks
            chunks shouldContainExactly listOf(HttpmlText(expected))
        }
    }

    "must parse quoted text" {
        forAll(
            row("  \t ' value in quotes '   \t", " value in quotes "),
            row("  a mixed 'quoted' string  in value  ", "a mixed quoted string  in value"),
        ) { input, expected ->
            val chunks = inExpression(input).value().chunks
            chunks shouldContainExactly listOf(HttpmlText(expected))
        }
    }

    "must parse nested expression" {
        forAll(
            row(" just an \${expression in \${expression}} ", HttpmlValue(listOf(
                HttpmlText("just an "),
                HttpmlValue(listOf(
                    HttpmlText("expression in "),
                    HttpmlValue(listOf(HttpmlText("expression")))
                )
            )))),
        ) { input, expected ->
            val value = inExpression(input).value()
            value shouldBe expected
        }
    }

})
