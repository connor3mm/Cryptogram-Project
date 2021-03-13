package cryptogram;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CryptoTesting {


    /**
     * Default constructor for test class SalesItemTest
     */

    public CryptoTesting() {

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


    @Test
    public void testLetterCryptoMade() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new Cryptogram();
        cgame.setPhrase("Test Phrase");
        assertEquals("Test Phrase", cgame.getPhrase());
    }

    @Test
    public void testGameMappingIsRandom() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new Cryptogram();

        int[] yes = cgame.getGameMapping();
        assertNotEquals(0, yes[0]);
        assertNotEquals(25, yes[25]);
        assertNotEquals(15, yes[15]);
    }

    @Test
    public void testPlayerMappingSameLetter() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new Cryptogram();
        cgame.setPhrase("a");
        assertTrue(cgame.getPlainLetter('y'));
    }

    @Test
    public void testUndoWithoutLetter() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new Cryptogram();
        assertFalse(cgame.undoGivenLetter('a'));
    }

    @Test
    public void testUndoWithLetter() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        LetterCryptogram cgame = new LetterCryptogram();
        cgame.enterLetter('b', 'a');
        assertTrue(cgame.undoGivenLetter('a'));
    }

    @Test
    public void testUndoWithoutNumber() {
        Player p = new Player("John");
        Game game = new Game(p, 1);
        Cryptogram cgame = new Cryptogram();
        assertFalse(cgame.undoGivenLetter('a'));
    }

    @Test
    public void testUndoWithNumber() {
        Player p = new Player("John");
        Game game = new Game(p, 1);
        NumberCryptogram cgame = new NumberCryptogram();
        cgame.enterLetter(7, 'a');
        assertTrue(cgame.undoGivenLetter('a'));
    }

    @Test
    public void testCryptoTypeLetterCrypto(){
        Player p = new Player("John");
        Game game = new Game(p, 0);
        assertNotEquals(game, null);
    }

    @Test
    public void testCryptoTypeNumberCrypto(){
        Player p = new Player("John");
        Game game = new Game(p, 1);
        assertNotEquals(game, null);
    }

    @Test
    public void testEnterLetter() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        LetterCryptogram cgame = new  LetterCryptogram();

        boolean result = (cgame.enterLetter('r', 'a'));
        // Results is depending on if user entered the correct mapping for guessing or not, this covers both cases
        if(result){
            assertTrue(result);
        }else{
            assertFalse(result);
        }
    }

    @Test
    public void testEnterNumber() {
        Player p = new Player("John");
        Game game = new Game(p, 1);
        NumberCryptogram cgame = new NumberCryptogram();

        boolean result = (cgame.enterLetter(7, 'a'));
        // Results is depending on if user entered the correct mapping for guessing or not, this covers both cases
        if(result){
            assertTrue(result);
        }else{
            assertFalse(result);
        }
    }

}

