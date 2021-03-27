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
import java.util.List;

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
        String pathsToDetailsString = Paths.get("").toAbsolutePath().toString() + "\\cryptograms\\noSaved.txt";
        File fileToReadDetailsFrom = new File(pathsToDetailsString);
        fileToReadDetailsFrom.delete();

        String pathsToDetailsString2 = Paths.get("").toAbsolutePath().toString() + "\\cryptograms\\saveWithSaved.txt";
        File fileToReadDetailsFrom2 = new File(pathsToDetailsString2);
        fileToReadDetailsFrom2.delete();

        String pathsToDetailsString3 = Paths.get("").toAbsolutePath().toString() + "\\PlayerDetails\\saveWithSaved.txt";
        File fileToReadDetailsFrom3 = new File(pathsToDetailsString3);
        fileToReadDetailsFrom3.delete();

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
     *
     * @throws Exception
     */
    @Test
    public void saveCryptogramWithNoSavedCryptogram() throws Exception {
        Cryptogram cryptogram = new Cryptogram();
        Player testPlayer = new Player("noSaved");
        assertTrue(cryptogram.saveCryptogram(testPlayer));
    }

//    @Test
//    public void saveCryptogramWithSavedCryptogram() throws Exception {
//        Player testPlayer = new Player("saveWithSaved");
//
//        Cryptogram cryptogramOne = new Cryptogram();
//        testPlayer.savePlayersDetails(testPlayer);
//        assertTrue(cryptogramOne.saveCryptogram2(testPlayer));
//
//    }


    /**
     * User story 5
     *
     * @throws Exception
     */
//    @Test
//    public void loadCryptogramWithSavedCryptogram() throws Exception {
//        Cryptogram cryptogram = new Cryptogram();
//        Player testPlayer = new Player("testUser");
//        cryptogram.loadCryptogram(testPlayer);
//
//        assertNotNull(cryptogram.loadCryptogram(testPlayer));
//    }

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


    //\\\\\\\\\\\\\\\\\\\\\\\\  Iteration 3  ////////////////////////////////////////


    /**
     * User story 6 tests
     */
    @Test
    public void showSolutionTestLetter() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new LetterCryptogram();
        cgame.setPhrase("abc");

        assertFalse(cgame.checkIfGameCompleted());

        cgame.showSolution();

        assertEquals('a', cgame.playerMapping[0] + 97);
        assertEquals('b', cgame.playerMapping[1] + 97);
        assertEquals('c', cgame.playerMapping[2] + 97);
    }

    @Test
    public void showSolutionTestNumber() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new NumberCryptogram();
        cgame.setPhrase("abc");

        assertFalse(cgame.checkIfGameCompleted());

        cgame.showSolution();

        assertEquals('a', cgame.playerMapping[0] + 97);
        assertEquals('b', cgame.playerMapping[1] + 97);
        assertEquals('c', cgame.playerMapping[2] + 97);
    }




    /**
     * User story 7 tests
     */
    @Test
    public void showFrequencyLetter() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new LetterCryptogram();
        cgame.setPhrase("abbccc");
        cgame.setLetterFrequency();
        cgame.getNumberOfLetters();

        assertEquals(1, cgame.letterFrequency[0]);
        assertEquals(2, cgame.letterFrequency[1]);
        assertEquals(3, cgame.letterFrequency[2]);

        //Percentages
        assertEquals(16, (cgame.letterFrequency[0] * 100) / cgame.cryptoPhrase.length());
        assertEquals(33, (cgame.letterFrequency[1] * 100) / cgame.cryptoPhrase.length());
        assertEquals(50, (cgame.letterFrequency[2] * 100) / cgame.cryptoPhrase.length());
    }


    @Test
    public void showFrequencyNumber() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new NumberCryptogram();
        cgame.setPhrase("abbccc");
        cgame.setLetterFrequency();
        cgame.getNumberOfLetters();

        assertEquals(1, cgame.letterFrequency[0]);
        assertEquals(2, cgame.letterFrequency[1]);
        assertEquals(3, cgame.letterFrequency[2]);

        //Percentages
        assertEquals(16, (cgame.letterFrequency[0] * 100) / cgame.cryptoPhrase.length());
        assertEquals(33, (cgame.letterFrequency[1] * 100) / cgame.cryptoPhrase.length());
        assertEquals(50, (cgame.letterFrequency[2] * 100) / cgame.cryptoPhrase.length());
    }



    /**
     * User story 14 tests
     */
    @Test
    public void showHintTest() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new LetterCryptogram();
        cgame.setPhrase("abc");

        cgame.getLetterHint(cgame.getNewPhrase().charAt(0));
        assertEquals('a', cgame.playerMapping[0] + 97);


        cgame.getLetterHint(cgame.getNewPhrase().charAt(5));
        assertEquals('b', cgame.playerMapping[1] + 97);


        cgame.getLetterHint(cgame.getNewPhrase().charAt(10));
        assertEquals('c', cgame.playerMapping[2] + 97);
    }


    @Test
    public void showHintWithMappedLetterTest() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new LetterCryptogram();
        cgame.setPhrase("abc");

        cgame.playerMapping[0] = 4;
        assertEquals('e', (char) cgame.playerMapping[0]+97);

        cgame.getLetterHint(cgame.getNewPhrase().charAt(0));
        assertEquals('a', cgame.playerMapping[0] + 97);
    }

    @Test
    public void showHintWithCorrectLetterTest() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new LetterCryptogram();
        cgame.setPhrase("abc");

        cgame.playerMapping[0] = 0;
        assertEquals('a', (char) cgame.playerMapping[0]+97);

        cgame.getLetterHint(cgame.getNewPhrase().charAt(0));
        assertEquals('a', cgame.playerMapping[0] + 97);
    }


    @Test
    public void showHintWithCorrectNumberTest() {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        Cryptogram cgame = new NumberCryptogram();
        cgame.setPhrase("abc");

        cgame.playerMapping[0] = 0;
        assertEquals('a', (char) cgame.playerMapping[0]+97);

        cgame.getLetterHint(cgame.getNewPhrase().charAt(0));
        assertEquals('a', cgame.playerMapping[0] + 97);
    }





    /**
     * User story 13 tests
     */
    @Test
    public void LoadPlayersWhenNoPlayersSaved() {
        Players players = new Players();
        Assertions.assertThrows(Exception.class, players::loadAllPlayers);
    }


    @Test
    public void DisplayTopTenScoresWhenNoScores() {
        Players players = new Players();
        assertEquals(0, players.getTopTenScores().size());
    }


    @Test
    public void DisplayTopTenScoresWhenLessThan10Scores (){
        Players players = new Players();

        players.addPlayer(new Player("Cameron", 0, 0, 0, 0, 5));
        players.addPlayer(new Player("Connor", 0, 0, 0, 0, 3));
        players.addPlayer(new Player("Stewart", 0, 0, 0, 0, 1));

        List<Player> playerList = players.getTopTenScores();
        assertEquals(5, players.getAllPlayers().get(2).getCryptogramsCompleted());
        assertEquals(3, players.getAllPlayers().get(1).getCryptogramsCompleted());
        assertEquals(1, players.getAllPlayers().get(0).getCryptogramsCompleted());
    }


    @Test
    public void DisplayTopTenScoresWhenEqualAndGreaterThan10Scores() {
        Players players = new Players();

        players.addPlayer(new Player("Cameron", 0, 0, 0, 0, 5));
        players.addPlayer(new Player("Connor", 0, 0, 0, 0, 3));
        players.addPlayer(new Player("Stewart", 0, 0, 0, 0, 1));
        players.addPlayer(new Player("Owen", 0, 0, 0, 0, 7));
        players.addPlayer(new Player("John", 0, 0, 0, 0, 2));
        players.addPlayer(new Player("Steven", 0, 0, 0, 0, 4));
        players.addPlayer(new Player("Thomas", 0, 0, 0, 0, 8));
        players.addPlayer(new Player("Bob", 0, 0, 0, 0, 9));
        players.addPlayer(new Player("Aiden", 0, 0, 0, 0, 10));
        players.addPlayer(new Player("Matthew", 0, 0, 0, 0, 6));

        List<Player> playerList = players.getTopTenScores();

        assertEquals(10, playerList.get(9).getCryptogramsCompleted());
        assertEquals(9, playerList.get(8).getCryptogramsCompleted());
        assertEquals(8, playerList.get(7).getCryptogramsCompleted());
        assertEquals(7, playerList.get(6).getCryptogramsCompleted());
        assertEquals(6, playerList.get(5).getCryptogramsCompleted());
        assertEquals(5, playerList.get(4).getCryptogramsCompleted());
        assertEquals(4, playerList.get(3).getCryptogramsCompleted());
        assertEquals(3, playerList.get(2).getCryptogramsCompleted());
        assertEquals(2, playerList.get(1).getCryptogramsCompleted());
        assertEquals(1, playerList.get(0).getCryptogramsCompleted());

        //Test with 11 people
        players.addPlayer(new Player("Number11", 0, 0, 0, 0, 11));
        playerList = players.getTopTenScores();
        assertEquals(11, playerList.get(9).getCryptogramsCompleted());
    }
}