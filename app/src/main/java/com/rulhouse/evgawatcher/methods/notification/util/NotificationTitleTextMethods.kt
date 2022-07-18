package com.rulhouse.evgawatcher.methods.notification.util

import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.DifferenceReason

class NotificationTitleTextMethods {
    fun getTitleTextByReason(reason: DifferenceReason): Int {
        return when (reason) {
            DifferenceReason.GetBuyable -> {
                R.string.get_in_stock
            }
            DifferenceReason.GetNotBuyable -> {
                R.string.get_out_of_stock
            }
            DifferenceReason.PriceGetLower -> {
                R.string.price_goes_low
            }
            DifferenceReason.PriceGetHigher -> {
                R.string.price_goes_up
            }
        }
    }
}