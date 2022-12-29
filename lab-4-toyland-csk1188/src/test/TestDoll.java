package test;

import org.junit.jupiter.api.*;
import toys.Color;
import toys.Doll;
import toys.ToyFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test for Doll
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDoll {
    /** standard output */
    private final PrintStream standardOut = System.out;
    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /** Test all aspects of the first Doll */
    @Test
    @Order(1)
    public void testFirstDoll() {
        Doll doll1 = (Doll) ToyFactory.makeToy("DOLL GabbyGabby RED 6 Will_you_be_my_friend?");
        assertEquals(200, doll1.getProductCode());
        assertEquals("GabbyGabby", doll1.getName());
        assertEquals(0, doll1.getHappiness());
        assertFalse(doll1.isRetired());
        assertEquals(0, doll1.getWear());
        assertEquals(Color.RED, doll1.getHairColor());
        assertEquals(6, doll1.getAge());
        assertEquals("Will_you_be_my_friend?", doll1.getSpeak());
        assertEquals("Toy{PC:200, N:GabbyGabby, H:0, R:false, W:0.0}, Doll{HC:RED, A:6, S:Will_you_be_my_friend?}", doll1.toString());

        doll1.play(15);
        String expected = "PLAYING(15): Toy{PC:200, N:GabbyGabby, H:0, R:false, W:0.0}, Doll{HC:RED, A:6, S:Will_you_be_my_friend?}" + System.lineSeparator();
        expected += "\tGabbyGabby brushes their RED hair and says, \"Will_you_be_my_friend?\"";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(15, doll1.getHappiness());
        assertFalse(doll1.isRetired());
        assertEquals(6, doll1.getWear());

        outputStreamCaptor.reset();
        doll1.play(8);
        expected = "PLAYING(8): Toy{PC:200, N:GabbyGabby, H:15, R:false, W:6.0}, Doll{HC:RED, A:6, S:Will_you_be_my_friend?}" + System.lineSeparator();
        expected += "\tGabbyGabby brushes their RED hair and says, \"Will_you_be_my_friend?\"";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(23, doll1.getHappiness());
        assertFalse(doll1.isRetired());
        assertEquals(12, doll1.getWear());

        outputStreamCaptor.reset();
        doll1.play(100);
        expected = "PLAYING(100): Toy{PC:200, N:GabbyGabby, H:23, R:false, W:12.0}, Doll{HC:RED, A:6, S:Will_you_be_my_friend?}" + System.lineSeparator();
        expected += "\tGabbyGabby brushes their RED hair and says, \"Will_you_be_my_friend?\"" + System.lineSeparator();
        expected += "RETIRED: Toy{PC:200, N:GabbyGabby, H:123, R:true, W:18.0}, Doll{HC:RED, A:6, S:Will_you_be_my_friend?}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(123, doll1.getHappiness());
        assertTrue(doll1.isRetired());
        assertEquals(18, doll1.getWear());
    }

    /** More testing with second Doll */
    @Test
    @Order(2)
    public void testSecondDoll() {
        Doll doll2 = (Doll) ToyFactory.makeToy("DOLL Barbie YELLOW 18 We_can_do_anything!\n");
        assertEquals(201, doll2.getProductCode());
        assertEquals("Barbie", doll2.getName());
        assertEquals(0, doll2.getHappiness());
        assertFalse(doll2.isRetired());
        assertEquals(0, doll2.getWear());
        assertEquals(Color.YELLOW, doll2.getHairColor());
        assertEquals(18, doll2.getAge());
        assertEquals("We_can_do_anything!", doll2.getSpeak());
        assertEquals("Toy{PC:201, N:Barbie, H:0, R:false, W:0.0}, Doll{HC:YELLOW, A:18, S:We_can_do_anything!}", doll2.toString());

        doll2.play(31);
        String expected = "PLAYING(31): Toy{PC:201, N:Barbie, H:0, R:false, W:0.0}, Doll{HC:YELLOW, A:18, S:We_can_do_anything!}" + System.lineSeparator();
        expected += "\tBarbie brushes their YELLOW hair and says, \"We_can_do_anything!\"";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(31, doll2.getHappiness());
        assertFalse(doll2.isRetired());
        assertEquals(18, doll2.getWear());
    }
}