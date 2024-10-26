package com.example.pinnaclemove

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.pinnaclemove.models.BoardsFragment
import com.example.pinnaclemove.models.HighlightsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.adapter.FragmentStateAdapter
import android.animation.ObjectAnimator
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyWorkSpaceActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var shortcutLayout: LinearLayout
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_work_space)

        // Set up Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Amina Bibi's CRM"

        // Set up ViewPager and TabLayout
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        // Set up adapter for ViewPager2
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Link TabLayout and ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "BOARDS"
                1 -> tab.text = "HIGHLIGHTS"
            }
        }.attach()

        // Initialize shortcut layout and Floating Action Button
        shortcutLayout = findViewById(R.id.shortcut_layout)
        fab = findViewById(R.id.fab)

        // Set Floating Action Button click listener
        fab.setOnClickListener {
            toggleShortcuts()
        }
    }

    // ViewPager2 Adapter
    class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> BoardsFragment()
                1 -> HighlightsFragment()
                else -> Fragment()
            }
        }
    }

    // Navigate to CreateCardActivity
    fun navigateToScreen2(view: View) {
        val intent = Intent(this, CreateCardActivity::class.java)
        startActivity(intent)
    }

    // Navigate to CreateBoardActivity
    fun navigateToScreen3(view: View) {
        val intent = Intent(this, CreateBoardActivity::class.java)
        startActivity(intent)
    }

    // Toggle shortcuts visibility
    private fun toggleShortcuts() {
        if (shortcutLayout.visibility == View.GONE) {
            shortcutLayout.visibility = View.VISIBLE
            fab.hide() // Hide FAB
            animateShortcuts(true)
        } else {
            animateShortcuts(false) {
                shortcutLayout.visibility = View.GONE
                fab.show() // Show FAB
            }
        }
    }

    // Animate shortcuts visibility
    private fun animateShortcuts(show: Boolean, onEnd: (() -> Unit)? = null) {
        val translationX = if (show) 0f else shortcutLayout.width.toFloat()
        val animator = ObjectAnimator.ofFloat(shortcutLayout, "translationX", translationX)
        animator.duration = 300
        animator.addListener(object : android.animation.Animator.AnimatorListener {
            override fun onAnimationStart(animation: android.animation.Animator) {}

            override fun onAnimationEnd(animation: android.animation.Animator) {
                onEnd?.invoke()
            }

            override fun onAnimationCancel(animation: android.animation.Animator) {}

            override fun onAnimationRepeat(animation: android.animation.Animator) {}
        })
        animator.start()
    }

    // Close shortcuts when the close button is clicked
    fun closeShortcuts(view: View) {
        animateShortcuts(false) {
            shortcutLayout.visibility = View.GONE
            fab.show() // Show FAB again
        }
    }
}
