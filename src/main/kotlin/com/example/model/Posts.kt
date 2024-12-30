package com.example.model

import com.google.gson.annotations.SerializedName

data class Posts(
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("title")
	val title: String? = null,
	@field:SerializedName("content")
	val content: String? = null,
	@field:SerializedName("author_id")
	val author_id: Int? = null,
	@field:SerializedName("likes")
	val likes: Int? = null,
	@field:SerializedName("comments")
	val comments: String? = null,

)
