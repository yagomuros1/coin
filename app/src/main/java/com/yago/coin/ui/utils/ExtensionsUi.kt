package com.yago.coin.ui.utils

import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.addItem(drawable: Int, title: String): Int {

    this.apply {
        // ----------------- add a new item to menu ----------------
        // add new item to menu
        val newItem: MenuItem = this.menu.add(
            Menu.NONE, // group id
            this.menu.size(), // item id
            1, // order
            title // title
        )

        // set new item's icon
        newItem.setIcon(drawable)

        // set new item show as action flags
        newItem.setShowAsActionFlags(
            MenuItem.SHOW_AS_ACTION_ALWAYS or
                    MenuItem.SHOW_AS_ACTION_WITH_TEXT
        )

        return newItem.itemId

    }


}