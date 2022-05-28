package com.rulhouse.evgawatcher

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class WebCrawler {
    suspend fun test() {
        withContext(Dispatchers.IO) {
            try {
                val downloadUrl = "http://tw.evga.com/products/productlist.aspx?type=0"

                val itemsName = mutableListOf<String>()
                val itemUrl = mutableListOf<String>()
                val itemsImgUrl = mutableListOf<String>()
                val itemCanBuy = mutableListOf<Boolean>()
                val itemSerial = mutableListOf<String>()
                val itemDetails = mutableListOf<List<String>>()
                val itemLimit = mutableListOf<String>()
                val itemPrice = mutableListOf<String>()
                val itemWarranty = mutableListOf<String>()

                val doc: Document = Jsoup.connect(downloadUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.19582")
                    .header("Connection", "keep-alive")
                    .get()

                val gridItemElements = doc.getElementsByClass("grid-item")
                gridItemElements.forEach { element ->
                    val plGridImageElements = element.getElementsByClass("pl-grid-image")
                    for (plGridImageElement in plGridImageElements) {
                        val item = plGridImageElement.child(1)
                        // Url
                        itemUrl.add(item.attr("href"))
                        // Title
                        itemsName.add(item.attr("title"))
                        // Image Url
                        itemsImgUrl.add(item.getElementsByTag("img").attr("src"))
                        // Serial
                        itemSerial.add(item.getElementsByTag("img").attr("alt"))
                    }
                    // Item can buy
                    val btnAddCartElements = element.getElementsByClass("btnAddCart")
                    itemCanBuy.add(!btnAddCartElements.isEmpty())

                    // Item details
                    val plGridInfo = element.getElementsByClass("pl-grid-info")
                    plGridInfo.forEach { element ->
                        val ulList = element.getElementsByTag("ul")
                        val details = ulList.first()
                        val detailsList: MutableList<String> = mutableListOf()
                        details?.children()?.forEach { element ->
                            detailsList.add(element.text())
                        }
                        itemDetails.add(detailsList)
                    }
                    // Limit
                    val productLimit = element.getElementsByClass("limit").text()
                    itemLimit.add(productLimit)

                    // Price
                    val priceClass = element.getElementsByClass("pl-grid-price")
                    priceClass.forEach { element ->
                        val price = element.getElementsByTag("strong").text()
                        itemPrice.add(price)
                    }

                    // Warranty
                    var warranty: String = ""
                    val warrantyClass = element.getElementsByTag("p")
                    warrantyClass.forEach { element ->
                        if (element.text().contains("全球保固：")) {
                            warranty = element.text()
                        }
                    }
                    itemWarranty.add(warranty)
                }
                Log.d("TestText", "item.name = $itemsName")
                Log.d("TestText", "item.img = $itemsImgUrl")
                Log.d("TestText", "item.serial = $itemSerial")
                Log.d("TestText", "item.itemCanBuy = $itemCanBuy")
                Log.d("TestText", "item.itemDetails = $itemDetails")
                Log.d("TestText", "item.itemUrl = $itemUrl")
                Log.d("TestText", "item.itemLimit = $itemLimit")
                Log.d("TestText", "item.itemPrice = $itemPrice")
                Log.d("TestText", "item.itemWarranty = $itemWarranty")
                convertStringToObject(
                    nameList = itemsName,
                    imgUtlList = itemsImgUrl,
                    serialList = itemSerial,
                    canBuyList = itemCanBuy,
                    detailsList = itemDetails,
                    urlList = itemUrl,
                    limitList = itemLimit,
                    priceList = itemPrice,
                    warrantyList = itemWarranty
                )
            } catch (e: Exception) {
                Log.d("TestText", "e = $e")
                e.printStackTrace()
            }
        }
    }

    private fun convertStringToObject(
        nameList: List<String>,
        imgUtlList: List<String>,
        serialList: List<String>,
        canBuyList: List<Boolean>,
        detailsList: List<List<String>>,
        urlList: List<String>,
        limitList: List<String>,
        priceList: List<String>,
        warrantyList: List<String>
    ): List<GpuProduct> {
        val list = mutableListOf<GpuProduct>()
        for (i in nameList.indices) {
            val name = nameList[i]
            val imgUrl = imgUtlList[i]
            val serial = serialList[i]
            val canBuy = canBuyList[i]
            val details = detailsList[i]
            val url = "https://tw.evga.com" + urlList[i]
            val limit = limitList[i]
            val price = StringMethods.removeStringCharacter(priceList[i], listOf(',')).toInt()
            val warranty = warrantyList[i]

            list.add(
                name = name,
                imgUrl = imgUrl,
                serial = serial,
                canBuy = canBuy,
                details = details,
                url = url,
                limit = limit,
                price = price,
                warranty = warranty
            )
        }

        return list
    }
}