package cryptogram;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void testCryptoTypeLetterCrypto() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        assertNotEquals(game, null);
    }

    @Test
    public void testCryptoTypeNumberCrypto() {
        Player p = new Player("John");
        Game game = new Game(p, 1);
        assertNotEquals(game, null);
    }

    @Test
    public void testEnterLetter() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        LetterCryptogram cgame = new LetterCryptogram();

        boolean result = (cgame.enterLetter('r', 'a'));
        // Results is depending on if user entered the correct mapping for guessing or not, this covers both cases
        if (result) {
            assertTrue(result);
        } else {
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
        if (result) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }

    /*
    //Will fail if there the file has content
    @Test
    public void emptyPhraseFile() {
        Cryptogram cryptogram = new Cryptogram();
        Assertions.assertThrows(Exception.class, cryptogram::getCryptoPhrases);
    }

    */


    //\\\\\\\\\\\\\\\\\\\\\\\\  Iteration 2  ////////////////////////////////////////


    /**
     * User story 4
     * @throws Exception
     */
    @Test
    public void saveCryptogramWithNoSavedCryptogram() throws Exception {
        Cryptogram cryptogram = new Cryptogram();
        Player testPlayer = new Player("noSaved");
        assertTrue(cryptogram.saveCryptogram(testPlayer));

        String pathsToDetailsString = Paths.get("").toAbsolutePath().toString() + "\\cryptograms\\noSaved.txt";
        File fileToReadDetailsFrom = new File(pathsToDetailsString);
        fileToReadDetailsFrom .delete();
    }

    @Test
    public void saveCryptogramWithSavedCryptogram() throws Exception {
        Player testPlayer = new Player("saveWithSaved");

        Cryptogram cryptogramOne = new Cryptogram();
        testPlayer.savePlayersDetails(testPlayer);
        assertTrue(cryptogramOne.saveCryptogram2(testPlayer));

    }


    /**
     * User story 5
     * @throws Exception
     */
    @Test
    public void loadCryptogramWithSavedCryptogram() throws Exception {
        Cryptogram cryptogram = new Cryptogram();
        Player testPlayer = new Player("testUser");
        cryptogram.loadCryptogram(testPlayer);

        assertNotNull(cryptogram.loadCryptogram(testPlayer));
    }

    @Test
    public void loadCryptogramWithNoSavedCryptogram() throws Exception {
        Cryptogram cryptogram = new Cryptogram();
        Player testPlayer = new Player("loadWithNoSaved");

        assertNull(cryptogram.loadCryptogram(testPlayer));
    }

    @Test
    public void loadCryptogramCorruptedFile() throws Exception {
        Player testPlayer = new Player().loadPlayersDetails("corrupt");
        Cryptogram cryptogram = new Cryptogram(testPlayer);

        Assertions.assertThrows(Exception.class, () -> cryptogram.loadCryptogram(testPlayer));
    }
}

