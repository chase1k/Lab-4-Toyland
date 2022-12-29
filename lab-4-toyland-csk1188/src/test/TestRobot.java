package test;

import org.junit.jupiter.api.*;
import toys.BatteryPowered;
import toys.Robot;
import toys.ToyFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test for Robot
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRobot {
    /** standard output */
    private final PrintStream standardOut = System.out;
    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /** Test all aspects of the first Robot */
    @Test
    @Order(1)
    public void testFirstRobot() {
        Robot robot1 = (Robot) ToyFactory.makeToy("ROBOT Voltron 8 true");
        assertEquals(500, robot1.getProductCode());
        assertEquals("Voltron", robot1.getName());
        assertEquals(0, robot1.getHappiness());
        assertFalse(robot1.isRetired());
        assertEquals(0, robot1.getWear());
        assertEquals(100, robot1.getBatteryLevel());
        assertEquals(8, robot1.getNumBatteries());
        assertTrue(robot1.isFlying());
        assertEquals(0, robot1.getDistance());
        assertEquals("Toy{PC:500, N:Voltron, H:0, R:false, W:0.0}, BatteryPowered{BL:100, NB:8}, Robot{F:true, D:0}", robot1.toString());

        robot1.play(50);
        String expected = "PLAYING(50): Toy{PC:500, N:Voltron, H:0, R:false, W:0.0}, BatteryPowered{BL:100, NB:8}, Robot{F:true, D:0}" + System.lineSeparator();
        expected += "\tVoltron is flying around with total distance: 1250";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(50, robot1.getHappiness());
        assertFalse(robot1.isRetired());
        assertEquals(25, robot1.getWear());
        assertEquals(42, robot1.getBatteryLevel());
        assertEquals(8, robot1.getNumBatteries());
        assertTrue(robot1.isFlying());
        assertEquals(1250, robot1.getDistance());

        outputStreamCaptor.reset();
        robot1.play(40);
        expected = "PLAYING(40): Toy{PC:500, N:Voltron, H:50, R:false, W:25.0}, BatteryPowered{BL:42, NB:8}, Robot{F:true, D:1250}" + System.lineSeparator();
        expected += "\tVoltron is flying around with total distance: 2250"  + System.lineSeparator();
        expected += "\tDEPLETED:Toy{PC:500, N:Voltron, H:50, R:false, W:50.0}, BatteryPowered{BL:0, NB:8}, Robot{F:true, D:2250}" + System.lineSeparator();
        expected += "\tRECHARGED:Toy{PC:500, N:Voltron, H:50, R:false, W:50.0}, BatteryPowered{BL:100, NB:8}, Robot{F:true, D:2250}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(90, robot1.getHappiness());
        assertFalse(robot1.isRetired());
        assertEquals(50, robot1.getWear());
        assertEquals(100, robot1.getBatteryLevel());
        assertEquals(8, robot1.getNumBatteries());
        assertTrue(robot1.isFlying());
        assertEquals(2250, robot1.getDistance());

        outputStreamCaptor.reset();
        robot1.play(14);
        expected = "PLAYING(14): Toy{PC:500, N:Voltron, H:90, R:false, W:50.0}, BatteryPowered{BL:100, NB:8}, Robot{F:true, D:2250}" + System.lineSeparator();
        expected += "\tVoltron is flying around with total distance: 2600" +  System.lineSeparator();
        expected += "RETIRED: Toy{PC:500, N:Voltron, H:104, R:true, W:75.0}, BatteryPowered{BL:78, NB:8}, Robot{F:true, D:2600}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(104, robot1.getHappiness());
        assertTrue(robot1.isRetired());
        assertEquals(75, robot1.getWear());
        assertEquals(78, robot1.getBatteryLevel());
        assertEquals(8, robot1.getNumBatteries());
        assertTrue(robot1.isFlying());
        assertEquals(2600, robot1.getDistance());
    }

    /** More testing with second Robot */
    @Test
    @Order(2)
    public void testSecondRobot() {
        Robot robot2 = (Robot) ToyFactory.makeToy("ROBOT Wall-E 4 false");
        assertEquals(501, robot2.getProductCode());
        assertEquals("Wall-E", robot2.getName());
        assertEquals(0, robot2.getHappiness());
        assertFalse(robot2.isRetired());
        assertEquals(0, robot2.getWear());
        assertEquals(100, robot2.getBatteryLevel());
        assertEquals(4, robot2.getNumBatteries());
        assertFalse(robot2.isFlying());
        assertEquals(0, robot2.getDistance());
        assertEquals("Toy{PC:501, N:Wall-E, H:0, R:false, W:0.0}, BatteryPowered{BL:100, NB:4}, Robot{F:false, D:0}", robot2.toString());

        robot2.play(100);
        String expected = "PLAYING(100): Toy{PC:501, N:Wall-E, H:0, R:false, W:0.0}, BatteryPowered{BL:100, NB:4}, Robot{F:false, D:0}" + System.lineSeparator();
        expected += "\tWall-E is running around with total distance: 1000" + System.lineSeparator();
        expected += "\tDEPLETED:Toy{PC:501, N:Wall-E, H:0, R:false, W:10.0}, BatteryPowered{BL:0, NB:4}, Robot{F:false, D:1000}" + System.lineSeparator();
        expected += "\tRECHARGED:Toy{PC:501, N:Wall-E, H:0, R:false, W:10.0}, BatteryPowered{BL:100, NB:4}, Robot{F:false, D:1000}" + System.lineSeparator();
        expected += "RETIRED: Toy{PC:501, N:Wall-E, H:100, R:true, W:10.0}, BatteryPowered{BL:100, NB:4}, Robot{F:false, D:1000}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(100, robot2.getHappiness());
        assertTrue(robot2.isRetired());
        assertEquals(10, robot2.getWear());
        assertEquals(100, robot2.getBatteryLevel());
        assertEquals(4, robot2.getNumBatteries());
        assertFalse(robot2.isFlying());
        assertEquals(1000, robot2.getDistance());

        // make sure Robot extends BatteryPowered
        BatteryPowered bp = robot2;
        assertEquals(100, bp.getBatteryLevel());
        assertEquals(4, bp.getNumBatteries());
    }
}