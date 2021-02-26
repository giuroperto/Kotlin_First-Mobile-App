package com.ctt.first_project_mobile.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//CASTING para PARCEL

@Parcelize
data class Usuario(val id : Int,
                   val nome : String,
                   val idade : Int,
                   var foto: Bitmap? = null) : Parcelable

//transforma em Parcel que o android entende

//bitmap eh o mapeamento de bit -> pixels -> tratar como  imagem
//anexando a foto como propriedade do User