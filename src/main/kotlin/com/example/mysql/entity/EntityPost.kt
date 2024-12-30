package com.example.mysql.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object EntityPost:Table<Nothing>(tableName = "posts") {
    val idposts = int(name = "idposts").primaryKey()
    val title = varchar(name = "title")
    val content = varchar(name = "content")
    val author_id = int(name = "author_id")
    val likes = int(name = "likes")
    val comments = varchar(name = "comments")
}