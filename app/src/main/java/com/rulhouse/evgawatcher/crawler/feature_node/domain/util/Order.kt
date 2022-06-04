package com.oucare.bbt_oucare.feature_node.domain.util

sealed class Order (val orderType: OrderType) {
    class Date(orderType: OrderType): Order(orderType)
}
