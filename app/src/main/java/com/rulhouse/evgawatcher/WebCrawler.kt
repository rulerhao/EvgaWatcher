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
//                val downloadUrl = "http://www.google.com/search?q=lakshman"
//                val downloadUrl = "https://www.oucare.com/app/flag20171106.html"
//                val downloadUrl = "http://imdb.com/search/title?count=100&genres=action&languages=en&release_date=2010,2016&title_type=feature"

                val mp3LinkText = mutableListOf<String>()

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
//                Log.d("TestText", "gridItemElements = $gridItemElements")
                for (gridItemElement in gridItemElements) {
                    val plGridImageElements = gridItemElement.getElementsByClass("pl-grid-image")
                    for (plGridImageElement in plGridImageElements) {
                        val item = plGridImageElement.child(1)
                        Log.d("TestText", "item.title = ${item.attr("title")}")
                        Log.d("TestText", "item.img = ${item.getElementsByTag("img").attr("src")}")

//                        plGridImageElement.getEle
//                        val links = plGridImageElement.getElementsByTag("a")
//                        for (linksEl in links) {
//                            val title: String = linksEl.attr("title")
//                            Log.d("TestText", "Title = ${title}")
//                        }
                    }
//                    for (in gridItemElement)
//                    val links: Elements = gridItemElement.getElementsByTag("a")
//                    for (linksEl in links) {
//                        val linkText: String = linksEl.attr("title")
//                        Log.d("TestText", "linkText = ${linkText}")
//                    }
//                    Log.d("TestText", "gridItemElement = $el")
//                    mp3LinkText.add(linkText)
                }
//                val links: Elements = doc.getElementsByTag("a")
//                for (el in links) {
////                    val linkText: String = el.attr("href")
//                    val linkText: String = el.attr("title")
//                    mp3LinkText.add(linkText)
//                    Log.d("TestText", "linkText = ${linkText}")
//                }
            } catch (e: Exception) {
                Log.d("TestText", "e = $e")
                e.printStackTrace()
            }
        }
    }
}