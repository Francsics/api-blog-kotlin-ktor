package com.example.mysql.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object EntityUser:Table<Nothing>(tableName = "users") {
    val idusers = int(name = "idusers").primaryKey()
    val name = varchar(name = "name")
    val email = varchar(name = "email")
    val password_hash = varchar(name = "password_hash")
}