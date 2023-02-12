package com.example.annotations


data class User(
    val name: String,
    val birthDate: String
)

@Target(AnnotationTarget.FIELD)
@Repeatable
annotation class AllowedRegex(val regex: String) {

}