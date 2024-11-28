package com.example.examapp

class Animal(val screenW: Int, val screenH: Int, scale: Float) {
    var w = (80 * scale).toInt()
    var h = (80 * scale).toInt()
    var x = screenW
    var y = (screenH / 2)

    fun fly() { // 確保此方法存在
        x -= 10
        y += (-20..20).random()
        if ((x + w) < 0) {
            Reset()
        }
    }

    fun Reset() {
        x = screenW
        y = (screenH / 2)
    }}