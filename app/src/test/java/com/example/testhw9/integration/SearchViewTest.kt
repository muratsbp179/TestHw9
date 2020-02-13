package com.example.testhw9.integration

import android.app.Activity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
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
class SearchViewTest {
    private lateinit var activity: Activity
    private lateinit var menu: Menu
    private lateinit var searchItem: MenuItem
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactListAdapter

    @Before
    fun setup() {
        activity =
            Robolectric.buildActivity(MainActivity::class.java).create().start().resume().visible()
                .get()
        menu = Shadows.shadowOf(activity).optionsMenu
        searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        recyclerView = activity.findViewById(R.id.contact_recycler_view)
        adapter = recyclerView.adapter as ContactListAdapter
    }

    @Test
    fun `Perform search button click`() {
        activity.onOptionsItemSelected(searchItem)

        Assert.assertTrue(searchItem.expandActionView())
        Assert.assertTrue(searchItem.isActionViewExpanded)
    }

    @Test
    fun `Empty query text on start`() {
        Assert.assertEquals("", searchView.query.toString())
    }

    @Test
    fun `Background for empty list`() {
        if (adapter.contacts.isEmpty()) {
            Assert.assertEquals(
                R.drawable.no_contacts,
                Shadows.shadowOf(recyclerView.background).createdFromResId
            )
        } else {
            Assert.assertEquals(
                0,
                Shadows.shadowOf(recyclerView.background).createdFromResId
            )
        }
    }
}