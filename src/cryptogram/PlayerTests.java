package cryptogram;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTests {



    /**
     * Default constructor for test class SalesItemTest
     */

    public void PlayerTests() {

    }


    /**
     * Sets up the test fixture.
     * <p>
     * Called before every test case method.
     */

    @Before
    public void setUp() {

    }


    /**
     * Tears down the test fixture.
     * <p>
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }


    /**
     * Test that a player is made with basic stats
     */
    @Test
    public void testPlayerMade() {
        Player p = new Player("John");
        assertEquals("John", p.getUsername());
        assertEquals(0, p.getAccuracy());
        assertEquals(0, p.getTotalGuesses());
        assertEquals(0, p.getCryptogramsPlayed());
        assertEquals(0, p.getCryptogramsCompleted());


    }

    /**
     * Test that when a crypto is generated, games played gets incremented
     */
    @Test
    public void testGamesPlayedIncrement() {
        //Test for letter crypto
        Player p = new Player("John");
        Game game = new Game(p, 0);
        game.generateCryptogram();
        assertEquals(1, p.getCryptogramsPlayed());

        //test for number crypto
        Game game2 = new Game(p, 1);
        game.generateCryptogram();
        assertEquals(2, p.getCryptogramsPlayed());
    }
}

