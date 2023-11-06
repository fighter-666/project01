package com.example.myapplication.data

import javax.inject.Inject

class MobilePhone @Inject constructor(val simCard: SimCard){
    fun dialNumber(){
        //val simCard = SimCard()
        simCard.dialNumber()
    }
}