package com.rulhouse.evgawatcher.crawler

import android.util.Log
import com.rulhouse.evgawatcher.GpuProduct
import com.rulhouse.evgawatcher.StringMethods
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class WebCrawler {
    companion object {
        suspend fun test(): List<GpuProduct>? {
            var ans: List<GpuProduct>? = null
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
                    gridItemElements.forEach { gridItemElement ->
                        val plGridImageElements =
                            gridItemElement.getElementsByClass("pl-grid-image")
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
                        val btnAddCartElements = gridItemElement.getElementsByClass("btnAddCart")
                        itemCanBuy.add(!btnAddCartElements.isEmpty())

                        // Item details
                        val plGridInfoElements = gridItemElement.getElementsByClass("pl-grid-info")
                        plGridInfoElements.forEach { plGridInfoElement ->
                            val ulList = plGridInfoElement.getElementsByTag("ul")
                            val details = ulList.first()
                            val detailsList: MutableList<String> = mutableListOf()
                            details?.children()?.forEach { detail ->
                                detailsList.add(detail.text())
                            }
                            itemDetails.add(detailsList)
                        }
                        // Limit
                        val productLimit = gridItemElement.getElementsByClass("limit").text()
                        itemLimit.add(productLimit)

                        // Price
                        val priceElements = gridItemElement.getElementsByClass("pl-grid-price")
                        priceElements.forEach { priceElement ->
                            val price = priceElement.getElementsByTag("strong").text()
                            itemPrice.add(price)
                        }

                        // Warranty
                        var warranty = ""
                        val warrantyElements = gridItemElement.getElementsByTag("p")
                        warrantyElements.forEach { warrantyElement ->
                            if (warrantyElement.text().contains("全球保固：")) {
                                warranty = warrantyElement.text()
                            }
                        }
                        itemWarranty.add(warranty)
                    }
                    ans = convertStringToObject(
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
                    e.printStackTrace()
                }
            }
            return ans
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
                val priceString = StringMethods.removeStringCharacter(priceList[i], listOf(','))
                val price = if (priceString.isEmpty()) 0 else priceString.toInt()
                val warranty = warrantyList[i]

                list.add(
                    GpuProduct(
                        name = name,
                        imgUrl = imgUrl,
                        serial = serial,
                        canBeBought = canBuy,
                        statement = details,
                        url = url,
                        limitedNumber = limit,
                        price = price,
                        warranty = warranty
                    )
                )
            }
            return list
        }
    }
}