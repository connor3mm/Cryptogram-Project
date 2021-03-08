package cryptogram;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTests {



    /**
     * Default constructor for test class SalesItemTest
     */

    public PlayerTests() {

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
        assertTrue( 0.0 == p.getAccuracy());
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

    @Test
    public void testUpdateAccuracy1() {
        //Test for letter crypto
        Player p = new Player("John");
        Game game = new Game(p, 0);
        game.generateCryptogram();
        p.setCorrectGuesses(49);
        p.setTotalGuesses(99);
        p.updateAccuracy(true);
        System.out.println(p.getAccuracy());
        assertTrue(50.0 == p.getAccuracy());
    }

    @Test
    public void testUpdateAccuracy2() {
        //Test for letter crypto
        Player p = new Player("John");
        Game game = new Game(p, 0);
        game.generateCryptogram();
        p.setCorrectGuesses(50);
        p.setTotalGuesses(49);
        p.updateAccuracy(false);
        System.out.println(p.getAccuracy());
        assertTrue(100.0 == p.getAccuracy());
    }




}