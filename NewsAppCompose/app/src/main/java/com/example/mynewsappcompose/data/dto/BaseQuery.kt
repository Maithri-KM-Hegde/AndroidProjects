package com.example.mynewsappcompose.data.dto

data class BaseQuery(
    val country: String = "us",
    val category: String? = "business", //The category you want to get headlines for.
    val q: String? = null, //Keywords or a phrase to search for.
    val pageSize: Int = 20, // Default page size
    val page: Int = 1, // Default first page
) {
    fun toQueryMap(): Map<String, String> {
        return mutableMapOf<String, String>().apply {
            put("country", country)
            category?.let { put("category", it) }
            q?.let { put("q", it) }
            put("pageSize", pageSize.toString())
            put("page", page.toString())
        }
    }
}
