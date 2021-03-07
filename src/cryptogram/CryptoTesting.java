package cryptogram;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        public void testGameMappingIsRandom(){
            Player p = new Player("John");
            Game game = new Game(p, 0);
            Cryptogram cgame = new Cryptogram();

            int[] yes = cgame.getGameMapping();
            assertNotEquals(0,yes[0]);
            assertNotEquals(25,yes[25]);
            assertNotEquals(15,yes[15]);
        }

        @Test
        public void testPlayerMappingSameLetter() {
            Player p = new Player("John");
            Game game = new Game(p, 0);
            Cryptogram cgame = new Cryptogram();
            cgame.setPhrase("a");
            cgame.getPlainLetter('y');

        }
    }
