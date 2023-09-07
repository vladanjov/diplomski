package com.vladan.diplomski.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vladan.diplomski.R

sealed class BottomNavItem(val route: String, @StringRes val titleId: Int, @DrawableRes val iconId: Int) {
    object Artikli : BottomNavItem("artikli", R.string.artikli, R.drawable.ic_artikli)
    object Dobavljaci : BottomNavItem("dobavljaci", R.string.dobavljaci, R.drawable.ic_dobavljaci)
    object Istorija : BottomNavItem("istorija", R.string.istorija, R.drawable.ic_istorija)
    object Korpa : BottomNavItem("korpa", R.string.korpa, R.drawable.ic_cart)
}