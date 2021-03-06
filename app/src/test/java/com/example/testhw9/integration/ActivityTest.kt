package com.example.testhw9.integration

import android.app.Activity
import android.widget.SearchView
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.test.filters.MediumTest
import com.example.testhw9.ContactListAdapter
import com.example.testhw9.MainActivity
import com.example.testhw9.R
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@MediumTest
@RunWith(RobolectricTestRunner::class)
class ActivityTest {
    private lateinit var activity: Activity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(MainActivity::class.java).create().start().visible().get()
    }

    @Test
    fun `Activity not null`() {
        Assert.assertNotNull(activity)
    }

    @Test
    fun `Recycler not null`() {
        Assert.assertNotNull(activity.findViewById(R.id.contact_recycler_view))
    }

    @Test
    fun `Recycler adapter not null`() {
        val adapter: ContactListAdapter =
            (activity.findViewById(R.id.contact_recycler_view) as RecyclerView).adapter as ContactListAdapter
        Assert.assertNotNull(adapter)
    }

    @Test
    fun `Recycler adapter list not null`() {
        val adapter: ContactListAdapter =
            (activity.findViewById(R.id.contact_recycler_view) as RecyclerView).adapter as ContactListAdapter

        Assert.assertNotNull(adapter.contacts)
    }

    @Test
    fun `Menu shown correctly`(){
        val menu = Shadows.shadowOf(activity).optionsMenu

        Assert.assertNotNull(menu)
        Assert.assertTrue(menu.isNotEmpty())
        Assert.assertTrue(menu.hasVisibleItems())
    }

    @Test
    fun `SearchView shown correctly`(){
        val searchView = Shadows.shadowOf(activity).optionsMenu.findItem(R.id.search).actionView

        Assert.assertNotNull(searchView)
        Assert.assertTrue(searchView is SearchView)
        Assert.assertTrue(searchView.isVisible)
    }
}
