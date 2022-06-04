package com.oucare.bbt_oucare.feature_node.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
