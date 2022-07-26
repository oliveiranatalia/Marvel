package br.com.zup.marvel.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(var email:String = "", var password:String = "",var name:String = ""
): Parcelable