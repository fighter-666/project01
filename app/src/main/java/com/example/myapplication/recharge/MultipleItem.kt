package com.example.recharge

import com.chad.library.adapter.base.entity.MultiItemEntity

class MultipleItem(override val itemType: Int) : MultiItemEntity {

    companion object {
        const val TEXT = 1
        const val IMG = 2
        const val IMG2 = 3
    }

}