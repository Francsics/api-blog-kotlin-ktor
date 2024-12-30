package com.example.model

import com.google.gson.annotations.SerializedName

data class User(
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("email")
	val email: String? = null,
	@field:SerializedName("password_hash")
	val password_hash: String? = null,

)
