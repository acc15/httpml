# httpml template examples

## Simple variable reference

    Test template ${variable}

## Function call

    Test template ${fun(
        parameter1,
        parameter2  ,
        named_parameter1 = text_value1,
        named_parameter2 = text_value2
    )}

## What is value?

Value term is used for following things:

* Variable name
* Function name
* Parameter name
* Parameter value

Value is just chunk of text until its used as variable, function or parameter name

## Expression syntax

Expression are always starts with `${` and ends with `}`.

Expression may contain either `reference_to_variable` or `function_call()`.

## Nested expressions

In every part of expression you may use nested expression.

For example here is reference to variable which name is stored in another variable:

    ${${variable_name}}


Partial variable names are also supported:

    ${my_${var_name}}

And of course you may specify function names (and even partial function names) with nested expressions.


For example imagine there is two functions: `plus` and `minus` and variable named `op_name`.

We may use this variable to resolve function name:

    ${${op_name}(1, 2)}

When `op_name = plus` we get `3`, and when `op_name = minus` we get `-2`

## Java Escapes

In any part of value you can use java escapes. Escaped characters won't be trimmed:

* `\b` (`\u0008`) Backspace
* `\t` (`\u0009`) Horizontal tab
* `\n` (`\u000a`) Linefeed
* `\f` (`\u000c`) Form feed
* `\r` (`\u000d`) Carriage return
* `\"` (`\u0022`) Double quotation mark
* `\'` (`\u0027`) Single quotation mark
* `\\` (`\u005c`) Backslash
* `\uXXXX` - Unicode character code
* `\010` - Octal character code

## Quoted values

Variable names, function names, parameter names and parameter values may be specified in quotes:

    ${'var_name'}
    ${fun(' parameter in quotes. All spaces are preserved as is', 'named_parameter1' = 'some text value')}

In quoted values you can use espaced characters:

    ${fun(named_parameter = 'some\n\r\t\a strange \u0031 parameter value')}

Nested expressions also supported:

    ${fun(named_parameter = 'You name is ${name}')}

And even quoted value within nested expression within quoted value:

    ${fun(p = 'You name is ${some_func('nested quoted value which may also contain ${nested_expression}')}')}

## Unquoted values (variables, function names, parameter names and parameter values)

You CAN'T use following characters directly without quotes:

* `=`
* `->`
* `)`
* `,`
* `\n`, `\r` are completely ignored
* spaces (` `) and tabs (`\t`) are trimmed

You can use java escapes if you want to use these characters.
But better and simpler to use quoted values in such cases.


# Examples

## Embedding file

    <html>
        <title>${ file | title.txt, encoding = utf-8 }</title>
        <body>Hello ${ file | user.txt}</body>
    </html>

## Base64 encoding

    Authorization: Basic ${base64 | ${user}:${password}, encoding = utf-8}

