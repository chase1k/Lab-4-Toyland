package test;

import org.junit.jupiter.api.*;
import toys.ActionFigure;
import toys.Color;
import toys.ToyFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test for ActionFigure
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestActionFigure {
    /** standard output */
    private final PrintStream standardOut = System.out;
    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /** Test all aspects of the first ActionFigure */
    @Test
    @Order(1)
    public void testFirstActionFigure() {
        ActionFigure af1 = (ActionFigure) ToyFactory.makeToy("ACTION_FIGURE He-man 30 By_the_power_of_Grayskull!");
        assertEquals(300, af1.getProductCode());
        assertEquals("He-man", af1.getName());
        assertEquals(0, af1.getHappiness());
        assertFalse(af1.isRetired());
        assertEquals(0, af1.getWear());
        assertEquals(Color.ORANGE, af1.getHairColor());
        assertEquals(30, af1.getAge());
        assertEquals(1, af1.getEnergyLevel());
        assertEquals("By_the_power_of_Grayskull!", af1.getSpeak());
        assertEquals("Toy{PC:300, N:He-man, H:0, R:false, W:0.0}, Doll{HC:ORANGE, A:30, S:By_the_power_of_Grayskull!}, ActionFigure{E:1}", af1.toString());

        af1.play(40);
        String expected = "PLAYING(40): Toy{PC:300, N:He-man, H:0, R:false, W:0.0}, Doll{HC:ORANGE, A:30, S:By_the_power_of_Grayskull!}, ActionFigure{E:1}" + System.lineSeparator();
        expected += "\tHe-man kung foo chops with 40 energy!" + System.lineSeparator();
        expected += "\tHe-man brushes their ORANGE hair and says, \"By_the_power_of_Grayskull!\"";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(40, af1.getHappiness());
        assertFalse(af1.isRetired());
        assertEquals(30, af1.getWear());
        assertEquals(2, af1.getEnergyLevel());


        outputStreamCaptor.reset();
        af1.play(12);
        expected = "PLAYING(12): Toy{PC:300, N:He-man, H:40, R:false, W:30.0}, Doll{HC:ORANGE, A:30, S:By_the_power_of_Grayskull!}, ActionFigure{E:2}" + System.lineSeparator();
        expected += "\tHe-man kung foo chops with 24 energy!" + System.lineSeparator();
        expected += "\tHe-man brushes their ORANGE hair and says, \"By_the_power_of_Grayskull!\"";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(52, af1.getHappiness());
        assertFalse(af1.isRetired());
        assertEquals(60, af1.getWear());
        assertEquals(3, af1.getEnergyLevel());


        outputStreamCaptor.reset();
        af1.play(49);
        expected = "PLAYING(49): Toy{PC:300, N:He-man, H:52, R:false, W:60.0}, Doll{HC:ORANGE, A:30, S:By_the_power_of_Grayskull!}, ActionFigure{E:3}" + System.lineSeparator();
        expected += "\tHe-man kung foo chops with 147 energy!" + System.lineSeparator();
        expected += "\tHe-man brushes their ORANGE hair and says, \"By_the_power_of_Grayskull!\"" + System.lineSeparator();
        expected += "RETIRED: Toy{PC:300, N:He-man, H:101, R:true, W:90.0}, Doll{HC:ORANGE, A:30, S:By_the_power_of_Grayskull!}, ActionFigure{E:4}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(101, af1.getHappiness());
        assertTrue(af1.isRetired());
        assertEquals(90, af1.getWear());
        assertEquals(4, af1.getEnergyLevel());

    }

    /** More testing with second ActionFigure */
    @Test
    @Order(2)
    public void testSecondActionFigure() {
        ActionFigure af2 = (ActionFigure) ToyFactory.makeToy("ACTION_FIGURE Animal 27 BEAT_DRUMS!");
        assertEquals(301, af2.getProductCode());
        assertEquals("Animal", af2.getName());
        assertEquals(0, af2.getHappiness());
        assertFalse(af2.isRetired());
        assertEquals(0, af2.getWear());
        assertEquals(Color.ORANGE, af2.getHairColor());
        assertEquals(27, af2.getAge());
        assertEquals(1, af2.getEnergyLevel());
        assertEquals("BEAT_DRUMS!", af2.getSpeak());
        assertEquals("Toy{PC:301, N:Animal, H:0, R:false, W:0.0}, Doll{HC:ORANGE, A:27, S:BEAT_DRUMS!}, ActionFigure{E:1}", af2.toString());

        af2.play(40);
        String expected = "PLAYING(40): Toy{PC:301, N:Animal, H:0, R:false, W:0.0}, Doll{HC:ORANGE, A:27, S:BEAT_DRUMS!}, ActionFigure{E:1}" + System.lineSeparator();
        expected += "\tAnimal kung foo chops with 40 energy!" + System.lineSeparator();
        expected += "\tAnimal brushes their ORANGE hair and says, \"BEAT_DRUMS!\"";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(40, af2.getHappiness());
        assertFalse(af2.isRetired());
        assertEquals(27, af2.getWear());
    }
}