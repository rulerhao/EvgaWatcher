package com.rulhouse.evgawatcher

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rulhouse.evgawatcher.methods.notification.use_case.NotificationUseCase
import com.rulhouse.evgawatcher.product_difference.ProductDifferenceMockObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

//@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NotificationTest {

//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)

    private val myScope = object: CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Job()
    }

    private lateinit var context: Context

    @Inject
    lateinit var notificationUseCase: NotificationUseCase

    @Before
    fun setup() {

//        hiltRule.inject()

        context = InstrumentationRegistry.getInstrumentation().targetContext

    }

    @Test
    fun priceGetLowerTest() {
        val mockObject = ProductDifferenceMockObject.mockPriceGetLower
        myScope.launch {
            notificationUseCase.notificationDifferentProducts(mockObject)
        }
    }
}