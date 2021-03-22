package com.example.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

const val zero = 0
const val empty = ""

class MyCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = zero
) :
    ConstraintLayout(context, attrs, defStyle) {

    private var name: String = empty
    private var age: Int = zero
    private var number: String = empty

    @ColorRes
    private var color: Int = zero

    @DrawableRes
    private var icon: Int = zero

    private lateinit var iconView: AppCompatImageView
    private lateinit var nameView: AppCompatTextView
    private lateinit var ageView: AppCompatTextView
    private lateinit var numberView: AppCompatTextView

    init {
        inflate(context, R.layout.my_custom_view, this)
        val typeArray = context.theme.obtainStyledAttributes(attrs, R.styleable.MyCustomView, zero, zero)
        initViews()
        loadAttrs(typeArray)
        loadViews()
    }

    private fun initViews() {
        iconView = findViewById(R.id.my_custom_view_icon)
        nameView = findViewById(R.id.my_custom_view_name)
        ageView = findViewById(R.id.my_custom_view_age)
        numberView = findViewById(R.id.my_custom_view_number)
    }

    private fun loadAttrs(typeArray: TypedArray) {
        icon = typeArray.getResourceId(R.styleable.MyCustomView_mcv_icon, zero)
        name = typeArray.getString(R.styleable.MyCustomView_mcv_name) ?: empty
        color = typeArray.getResourceId(R.styleable.MyCustomView_mcv_name_color, zero)
        age = typeArray.getInt(R.styleable.MyCustomView_mcv_age, zero)
        number = typeArray.getString(R.styleable.MyCustomView_mcv_number) ?: empty
        typeArray.recycle()
    }

    private fun loadViews() {
        setIcon(icon)
        setName(name)
        setNameColor(color)
        setAge(age)
        setNumber(number)
    }

    fun setIcon(@DrawableRes value: Int) {
        if (value != 0) {
            iconView.setImageResource(value)
        }
    }

    fun setName(value: String) {
        nameView.text = value
    }

    fun setNameColor(@ColorRes value: Int) {
        if (value != 0) {
            val color = ContextCompat.getColor(context, value)
            nameView.setTextColor(color)
        }
    }

    fun setAge(value: Int) {
        ageView.text = "$value"
    }

    fun setNumber(value: String) {
        numberView.text = value
    }
}