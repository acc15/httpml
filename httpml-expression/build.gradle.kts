plugins {
    id("httpml.kotlin-conventions")
    `java-library`
    id("com.intershop.gradle.javacc") version "4.0.1"
}

javacc {
    // configuration container for all javacc configurations
    configs {
        register("template") {
            inputFile = file("src/main/javacc/HttpmlExpressionParser.jjt")
            packageName = "httpml.expression.parser"
            lookahead = 1
            staticParam = "false"
            jjtree {
                isConfigured = true
                multi = "true"
            }
        }
    }
}