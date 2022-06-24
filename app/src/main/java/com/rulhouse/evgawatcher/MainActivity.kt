package com.rulhouse.evgawatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import com.rulhouse.evgawatcher.data_store.use_cases.NotificationIdDataStoreUseCases
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.notification.impl.DifferentProductsNotification
import com.rulhouse.evgawatcher.notification.use_case.NotificationUseCase
import com.rulhouse.evgawatcher.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var notification: NotificationUseCase

    @Inject
    lateinit var notificationUseCase:NotificationUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Button(
                    onClick = {
                        runBlocking {
                            notificationUseCase.notificationDifferentProducts(listOf(
                                ProductsDifference(
                                    gpuProduct = GpuProduct(
                                        name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
                                        imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
                                        serial = "24G-P5-4988-KR",
                                        canBeBought = true,
                                        statement = listOf(),
                                        url = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
                                        limitedNumber = "",
                                        price = 10000,
                                        warranty = "全球保固： 3 年",
                                        favorite = false
                                    ),
                                    reason = DifferenceReason.PriceGetLower
                                )
                            ))
                        }
//                        DifferentProductsNotification().doNotify(this)
                    }
                ) {

                }
//                MainScreen()
            }
        }
    }
}