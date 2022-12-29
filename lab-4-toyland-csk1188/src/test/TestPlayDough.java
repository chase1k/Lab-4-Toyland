package test;

import org.junit.jupiter.api.*;
import toys.Color;
import toys.PlayDough;
import toys.ToyFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test for PlayDough
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPlayDough {
    /** standard output */
    private final PrintStream standardOut = System.out;
    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /** Test all aspects of the first PlayDough */
    @Test
    @Order(1)
    public void testFirstPlayDough() {
        PlayDough pd1 = (PlayDough) ToyFactory.makeToy("PLAY_DOUGH Play-Doh GREEN");
        assertEquals(100, pd1.getProductCode());
        assertEquals("Play-Doh", pd1.getName());
        assertEquals(0, pd1.getHappiness());
        assertFalse(pd1.isRetired());
        assertEquals(0, pd1.getWear());
        assertEquals(Color.GREEN, pd1.getColor());
        assertEquals( "Toy{PC:100, N:Play-Doh, H:0, R:false, W:0.0}, PlayDough{C:GREEN}", pd1.toString());

        pd1.play(10);
        String expected = "PLAYING(10): Toy{PC:100, N:Play-Doh, H:0, R:false, W:0.0}, PlayDough{C:GREEN}" + System.lineSeparator();
        expected += "\tArts and crafting with GREEN Play-Doh";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(10, pd1.getHappiness());
        assertFalse(pd1.isRetired());
        assertEquals(0.5, pd1.getWear());

        outputStreamCaptor.reset();
        pd1.play(5);
        expected = "PLAYING(5): Toy{PC:100, N:Play-Doh, H:10, R:false, W:0.5}, PlayDough{C:GREEN}" + System.lineSeparator();
        expected += "\tArts and crafting with GREEN Play-Doh";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(15, pd1.getHappiness());
        assertFalse(pd1.isRetired());
        assertEquals(0.75, pd1.getWear());

        outputStreamCaptor.reset();
        pd1.play(100);
        expected = "PLAYING(100): Toy{PC:100, N:Play-Doh, H:15, R:false, W:0.75}, PlayDough{C:GREEN}" + System.lineSeparator();
        expected += "\tArts and crafting with GREEN Play-Doh" + System.lineSeparator();
        expected += "RETIRED: Toy{PC:100, N:Play-Doh, H:115, R:true, W:5.75}, PlayDough{C:GREEN}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(115, pd1.getHappiness());
        assertTrue(pd1.isRetired());
        assertEquals(5.75, pd1.getWear());
    }

    /** More testing with second PlayDough */
    @Test
    @Order(2)
    public void testSecondPlayDough() {
        PlayDough pd2 = (PlayDough) ToyFactory.makeToy("PLAY_DOUGH Clay-Dough RED");
        assertEquals(101, pd2.getProductCode());
        assertEquals("Clay-Dough", pd2.getName());
        assertEquals(0, pd2.getHappiness());
        assertFalse(pd2.isRetired());
        assertEquals(0, pd2.getWear());
        assertEquals(Color.RED, pd2.getColor());
        assertEquals("Toy{PC:101, N:Clay-Dough, H:0, R:false, W:0.0}, PlayDough{C:RED}", pd2.toString());

        pd2.play(42);
        String expected = "PLAYING(42): Toy{PC:101, N:Clay-Dough, H:0, R:false, W:0.0}, PlayDough{C:RED}" + System.lineSeparator();
        expected += "\tArts and crafting with RED Clay-Dough";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(42, pd2.getHappiness());
        assertFalse(pd2.isRetired());
        assertEquals(2.1, pd2.getWear());
    }
}