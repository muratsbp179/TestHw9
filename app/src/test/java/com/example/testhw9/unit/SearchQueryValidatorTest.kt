package com.example.testhw9.unit

import androidx.test.filters.SmallTest
import com.example.testhw9.search.validateQuery
import org.junit.Assert
import org.junit.Test

@SmallTest
class SearchQueryValidatorTest {
    @Test
    fun `Empty query returns true`(){
        Assert.assertTrue(validateQuery(""))
    }

    @Test
    fun `Null query returns false`(){
        Assert.assertFalse(validateQuery(null))
    }

    @Test
    fun `Correct lowercase name returns true`(){
        Assert.assertTrue(validateQuery("pasha"))
    }

    @Test
    fun `Correct uppercase name returns true`(){
        Assert.assertTrue(validateQuery("PASHA"))
    }

    @Test
    fun `Correct simple name returns true`(){
        Assert.assertTrue(validateQuery("Pasha"))
    }

    @Test
    fun `Name with invalid characters returns false`(){
        Assert.assertFalse(validateQuery("/Pasha*"))
    }

    @Test
    fun `Correct name with dot returns true`(){
        Assert.assertTrue(validateQuery("Robert Jr."))
    }

    @Test
    fun `Correct long name returns false`(){
        Assert.assertTrue(validateQuery("Hubert Blaine Wolfeschlegelsteinhausenbergerdorff Sr. "))
    }

    @Test
    fun `Correct Russian name returns true`(){
        Assert.assertTrue(validateQuery("Паша"))
    }

    @Test
    fun `Correct US name returns true`(){
        Assert.assertTrue(validateQuery("Pashka"))
    }

    @Test
    fun `Correct simple number returns true`(){
        Assert.assertTrue(validateQuery("8911-904-73-41"))
    }

    @Test
    fun `Number with letters returns false`(){
        Assert.assertFalse(validateQuery("020-368a-6884"))
    }

    @Test
    fun `Number with invalid characters returns false`(){
        Assert.assertFalse(validateQuery("020-368****-6884"))
    }

    @Test
    fun `Correct Russian number returns true`(){
        Assert.assertTrue(validateQuery("+7(911)-904-73-41"))
    }

    @Test
    fun `Correct US number returns true`(){
        Assert.assertTrue(validateQuery("020-368-6884"))
    }
}
