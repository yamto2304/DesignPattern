package com.example.annotations

import android.view.View
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.annotation.Size
import androidx.annotation.StringRes

//Chú giải tài nguyên
fun setTitle(@StringRes resId: Int) {}

//Chú giải quy tắc ràng buộc giá trị
@IntRange(from = 0, to = 255)
fun setAlpha(@IntRange(from = 0, to = 255) alpha: Int): Int {
    return -0
}

fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) {}

fun getLocation(button: View, @Size(min = 1) location: IntArray) {
    button.getLocationOnScreen(location)
}

@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class Positive



class Item(
    @Positive val amount: Float,
    @Annotations.AllowedNames(["Alice", "Bob"]) val name: String
)

class Annotations {
    @Target(AnnotationTarget.FIELD)
    annotation class AllowedNames(val names: Array<String>)
//    private val item = Item(amount = 1.0f, name = "Bob")
//    private val validator = Validator()
//    fun test() {
//        setAlpha(-0)
//        println("Is instance valid? ${validator.isValid(item)}")
//    }
}










//@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY)
//@Retention(AnnotationRetention.RUNTIME)
//annotation class Validate(val pattern: String)
//
//fun validateData(@Validate(pattern = "[A-Za-z0-9]+") data: String) {
//    val regex = Regex(pattern)
//    if (!regex.matches(data)) {
//        throw IllegalArgumentException("Invalid data format")
//    }
//    // continue with processing valid data
//}
//
//@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY)
//@Retention(AnnotationRetention.RUNTIME)
//annotation class NumberRange(val min: Int, val max: Int)
//
//fun validateNumber(@NumberRange(min = 0, max = 100) num: Int) {
//    if (num < min || num > max) {
//        throw IllegalArgumentException("Number must be in the range of $min to $max")
//    }
//    // continue with processing valid number
//}