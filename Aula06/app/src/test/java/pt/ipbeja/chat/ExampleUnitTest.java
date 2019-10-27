package pt.ipbeja.chat;

import org.junit.Test;

import pt.ipbeja.chat.db.entity.Contact;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void initials_areCorrect() {

        Contact c = new Contact("Diogo");
        assertEquals("D", c.getInitials());

        c.setName("Diogo P");
        assertEquals("DP", c.getInitials());

        c.setName("Diogo Pina Manique");
        assertEquals("DM", c.getInitials());

    }

    @Test(expected = IllegalArgumentException.class)
    public void initials_empty_name_throws() {
        Contact c = new Contact("");
    }
}