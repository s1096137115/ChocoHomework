package tw.com.maxting.chocohomework.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.MenuItem
import android.view.ViewAnimationUtils
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import org.threeten.bp.ZonedDateTime
import tw.com.maxting.chocohomework.R

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(crossinline initializer: () -> T): T {
    val viewModelClass = T::class.java
    val factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(viewModelClass) -> initializer.invoke() as T
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
    return ViewModelProvider(this, factory).get(viewModelClass)
}

fun FragmentManager.openFragment(fragment: Fragment, containerViewId: Int = R.id.container) {
    this.beginTransaction()
        .replace(containerViewId, fragment)
        .commitAllowingStateLoss()
}

@ColorInt
fun Context.getAppThemeColor(elements: Int = R.attr.colorPrimary): Int {
    val typedArray = theme.obtainStyledAttributes(intArrayOf(elements))
    val result = typedArray.getColor(0, 0)
    typedArray.recycle()
    return result
}

fun Toolbar.setupItemExpandAnimation(
    itemId: Int,
    numberOfMenuIcon: Int = 1, @ColorRes id: Int = android.R.color.white
) {

    if (itemId == R.id.search) {
        val searchManager = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo((context as Activity).componentName))
        }
    }

    val toolbar = this

    val defaultBackgroundColor =
        if (background is ColorDrawable) {
            (background as ColorDrawable).color
        } else {
            context.getAppThemeColor()
        }

    var animationFinished = false

    fun expandToolbarAnimation(numberOfMenuIcon: Int) {
        toolbar.setBackgroundColor(ContextCompat.getColor(context!!, id)) //animation start toolbar color

        val width =
            this.width - resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) * numberOfMenuIcon / 2
        val createCircularReveal = ViewAnimationUtils.createCircularReveal(
            this,
            width, this.height / 2, 0.0f, width.toFloat()
        )
        createCircularReveal.duration = 350
        createCircularReveal.start()
    }

    fun collapseToolbarAnimation(numberOfMenuIcon: Int) {
        toolbar.setBackgroundColor(ContextCompat.getColor(context!!, id)) //animation start toolbar color

        val width =
            this.width - resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) * numberOfMenuIcon / 2
        val createCircularReveal = ViewAnimationUtils.createCircularReveal(
            this,
            width, this.height / 2, width.toFloat(), 0.0f
        )
        createCircularReveal.duration = 350
        createCircularReveal.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                animationFinished = true
                toolbar.setBackgroundColor(defaultBackgroundColor) //animation end toolbar color
                toolbar.collapseActionView()
                //todo set collapse status bar color
            }
        })
        createCircularReveal.start()
        //todo set expand status bar color
    }

    menu.findItem(itemId).setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem): Boolean {
            expandToolbarAnimation(numberOfMenuIcon)
            animationFinished = false
            return true
        }

        override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
            return if (item.isActionViewExpanded && animationFinished.not()) {
                collapseToolbarAnimation(numberOfMenuIcon)
                false
            } else {
                true
            }
        }
    })
}

fun String.convertLocalDate() = ZonedDateTime.parse(this).toLocalDate().toString()
