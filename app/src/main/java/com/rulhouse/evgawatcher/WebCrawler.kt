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
                Log.d("TestText", "start")
                val downloadUrl = "http://tw.evga.com/products/productlist.aspx?type=0"

                val itemsName = mutableListOf<String>()
                val itemsImgUrl = mutableListOf<String>()

                val doc: Document = Jsoup.connect(downloadUrl)
//                    .userAgent("Mozilla")
//                    .userAgent("Chrome")
//                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
//                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.19582")
//                    .header("key", "value")
//                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//                    .data("key", "value")
//                    .referrer("http://www.google.com")
//                    .header("Accept", "text/html")
                    .header("Connection", "keep-alive")
//                    .ignoreContentType(true)
                    .get()

                val gridItemElements = doc.getElementsByClass("grid-item")
                for (gridItemElement in gridItemElements) {
                    val plGridImageElements = gridItemElement.getElementsByClass("pl-grid-image")
                    for (plGridImageElement in plGridImageElements) {
                        val item = plGridImageElement.child(1)
                        itemsName.add(item.attr("title"))
                        itemsImgUrl.add(item.getElementsByTag("img").attr("src"))
                    }
                    val btnAddCartElements = gridItemElement.getElementsByClass("btnAddCart")
                    Log.d("TestText", "btnAddCartElements = $btnAddCartElements")
                }
                Log.d("TestText", "item.title = $itemsName")
                Log.d("TestText", "item.img = $itemsImgUrl")
            } catch (e: Exception) {
                Log.d("TestText", "e = $e")
                e.printStackTrace()
            }
        }
    }
}