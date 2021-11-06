package com.example.calculator.domain

import com.fathzer.soft.javaluator.DoubleEvaluator
import kotlin.math.floor

/**
 * Рассчитывает значение выражения [expression]
 */

fun calculateExpression(expression: String): String {

    if (expression.isBlank()) return ""

    var formattedExpression = expression
    while (formattedExpression.isNotBlank() && !formattedExpression.last().isDigit() &&
        formattedExpression.last() != ')'
    ) {
        formattedExpression = formattedExpression.dropLast(1)
    }

    while (formattedExpression.isNotBlank() && !formattedExpression.first().isDigit() &&
        formattedExpression.first() != ')' && formattedExpression.first() != '-'
    ) {
        formattedExpression = formattedExpression.drop(1)
    }

    if (formattedExpression.isBlank()) return ""

    if (formattedExpression.isBlank()) return ""

    val result = DoubleEvaluator().evaluate(formattedExpression)

    return if (floor(result) == result) {
        result.toInt().toString()
    } else {
        result.toString()
    }
}