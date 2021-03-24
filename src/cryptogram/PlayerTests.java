package cryptogram;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.nio.file.Paths;

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
        String pathsToDetailsString = Paths.get("").toAbsolutePath().toString() + "\\PlayerDetails\\John.txt";
        File fileToReadDetailsFrom = new File(pathsToDetailsString);
        fileToReadDetailsFrom .delete();


    }


    /**
     * Test that a player is made with basic stats
     */
    @Test
    public void testPlayerMade() {
        Player p = new Player("John");
        assertEquals("John", p.getUsername());
        assertTrue(0.0 == p.getAccuracy());
        assertEquals(0, p.getTotalGuesses());
        assertEquals(0, p.getCryptogramsPlayed());
        assertEquals(0, p.getCryptogramsCompleted());
    }


    //\\\\\\\\\\\\\\\\\\\\\\\\  Iteration 2  ////////////////////////////////////////
    /**
     * User story 9
     * Test that when a crypto is complete, increment happens
     */
    @Test
    public void testGamesCompletedIncrement() {
        //Test for letter crypto
        Player p = new Player("John");
        p.incrementCryptogramCompleted();
        assertEquals(1, p.getCryptogramsCompleted());
    }



    /**
     * User story 10
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
    public void testGamesPlayedByload() {
        //Test for letter crypto
        Player p = new Player("John");
        Game game = new Game(p, 0);
        game.generateCryptogram();
        assertEquals(1, p.getCryptogramsPlayed());
        game.savePlayer();

        //test for number crypto
        game.loadGame();
        assertEquals(1, p.getCryptogramsPlayed());
    }



    /**
     * User story 11
     */
    @Test
    public void testUpdateAccuracyTrue() {
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
    public void testUpdateAccuracyFalse() {
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



    /**
     * User story 12 and 8
     */
    @Test
    public void testLoadPlayer() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        game.generateCryptogram();
        p.setCorrectGuesses(49);
        p.setTotalGuesses(99);
        p.setAccuracy(50.0);
        p.setCryptogramsCompleted(3);
        p.setCryptogramsPlayed(2);

        //User story 8
        assertTrue(p.savePlayersDetails(p));

        //User story 12
        game.loadGame();
        assertEquals(49, p.getCorrectGuesses());
        assertEquals(99, p.getTotalGuesses());
        assertTrue(50 == p.getAccuracy());
        assertEquals(3, p.getCryptogramsCompleted());
        assertEquals(2, p.getNumCryptogramsPlayed());
    }

    @Test
    public void corruptPlayerDetails() {
        Assertions.assertThrows(Exception.class, () -> new Player().loadPlayersDetails("testCor"));
    }

    @Test
    public void testPlayaDontExist() throws Exception {
        Player p = new Player().loadPlayersDetails("Unknown");
        Assertions.assertNull(p);
    }

    @Test
    public void testPlaya() throws Exception {
        Assertions.assertThrows(Exception.class, () -> new Player().loadPlayersDetails("testCor"));
    }

}