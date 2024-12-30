package com.example.route

import com.example.model.Posts
import com.example.model.User
import com.example.mysql.DbConnection
import com.example.mysql.entity.EntityPost
import com.example.mysql.entity.EntityUser
import com.example.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.dsl.insert
import org.ktorm.dsl.*

fun Application.routeUser() {
    val db: Database = DbConnection.getDatabaseInstance()
    routing {
        get("/")
        {
            call.respondText("Welcome to Ktor Mysql")
        }

        post("/register")
        {
            val user: User = call.receive()
            val noOfRowsAffected = db.insert(EntityUser)
            {
                set(it.name, user.name)
                set(it.email, user.email)
                set(it.password_hash, user.password_hash)

            }

            if (noOfRowsAffected > 0) {
                //success
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "$noOfRowsAffected rows are affected")
                )
            } else {
                //fail
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(isSuccess = true, data = "Error to register the user")
                )
            }
        }
        post("/login") {
            val loginRequest: LoginRequest = call.receive()


            val userFromDb = db.from(EntityUser)
                .select()
                .where { EntityUser.email eq loginRequest.email }
                .map { row ->
                    User(
                        name = row[EntityUser.name] ?: "",
                        email = row[EntityUser.email] ?: "",
                        password_hash = row[EntityUser.password_hash] ?: "",
                    )
                }
                .singleOrNull()

            if (userFromDb != null && userFromDb.password_hash == loginRequest.password_hash) {
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "Login successful")
                )
            } else {
                call.respond(
                    HttpStatusCode.Unauthorized,
                    GenericResponse(isSuccess = false, data = "Invalid username or password")
                )
            }
        }
        get("/listausers") {
            val users = db.from(EntityUser).select().map { row ->
                User(
                    name = row[EntityUser.name],
                    email = row[EntityUser.email],
                    password_hash = row[EntityUser.password_hash],
                )
            }

            if (users.isNotEmpty()) {
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = users)
                )
            } else {
                call.respond(
                    HttpStatusCode.NotFound,
                    GenericResponse(isSuccess = false, data = "No users found")
                )
            }
        }
        post("/criarposts") {
            val post: Posts = call.receive()
            val noOfRowsAffected = db.insert(EntityPost) {
                set(it.title, post.title)
                set(it.content, post.content)
                set(it.author_id, post.author_id)
                set(it.likes, post.likes)
                set(it.comments, post.comments)
            }

            if (noOfRowsAffected > 0) {
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "$noOfRowsAffected rows are affected")
                )
            } else {
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(isSuccess = false, data = "Error creating the post")
                )
            }
        }
        post("/deleteposts") {
            val postId = call.receive<Map<String, Int>>()["id"] ?: throw BadRequestException("Missing id")
            val noOfRowsAffected = db.delete(EntityPost) {
                it.idposts eq postId
            }

            if (noOfRowsAffected > 0) {
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "$noOfRowsAffected rows deleted")
                )
            } else {
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(isSuccess = false, data = "Error deleting the post")
                )
            }
        }
        post("/editarposts") {
            val post: Posts = call.receive()

            post.id?.let { id ->
                val noOfRowsAffected = db.update(EntityPost) {
                    set(it.title, post.title)
                    set(it.content, post.content)
                    set(it.author_id, post.author_id)
                    set(it.likes, post.likes)
                    set(it.comments, post.comments)
                    where {
                        it.idposts eq id
                    }
                }

                if (noOfRowsAffected > 0) {
                    call.respond(
                        HttpStatusCode.OK,
                        GenericResponse(isSuccess = true, data = "$noOfRowsAffected rows updated")
                    )
                } else {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        GenericResponse(isSuccess = false, data = "Error updating the post")
                    )
                }
            } ?: call.respond(
                HttpStatusCode.BadRequest,
                GenericResponse(isSuccess = false, data = "Post ID is missing")
            )
        }
    }
}