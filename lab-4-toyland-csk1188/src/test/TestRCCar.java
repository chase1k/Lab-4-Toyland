package test;

import org.junit.jupiter.api.*;
import toys.BatteryPowered;
import toys.RCCar;
import toys.ToyFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test for RCCar
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRCCar {
    /** standard output */
    private final PrintStream standardOut = System.out;
    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /** Test all aspects of the first RCCar */
    @Test
    @Order(1)
    public void testFirstRCCar() {
        RCCar car1 = (RCCar) ToyFactory.makeToy("RC_CAR Herbie 4");
        assertEquals(400, car1.getProductCode());
        assertEquals("Herbie", car1.getName());
        assertEquals(0, car1.getHappiness());
        assertFalse(car1.isRetired());
        assertEquals(0, car1.getWear());
        assertEquals(100, car1.getBatteryLevel());
        assertEquals(4, car1.getNumBatteries());
        assertEquals(10, car1.getSpeed());
        assertEquals("Toy{PC:400, N:Herbie, H:0, R:false, W:0.0}, BatteryPowered{BL:100, NB:4}, RCCar{S:10}", car1.toString());

        car1.play(60);
        String expected = "PLAYING(60): Toy{PC:400, N:Herbie, H:0, R:false, W:0.0}, BatteryPowered{BL:100, NB:4}, RCCar{S:10}" + System.lineSeparator();
        expected += "\tHerbie races around at 10mph!";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(60, car1.getHappiness());
        assertFalse(car1.isRetired());
        assertEquals(10, car1.getWear());
        assertEquals(36, car1.getBatteryLevel());
        assertEquals(4, car1.getNumBatteries());
        assertEquals(15, car1.getSpeed());

        outputStreamCaptor.reset();
        car1.play(12);
        expected = "PLAYING(12): Toy{PC:400, N:Herbie, H:60, R:false, W:10.0}, BatteryPowered{BL:36, NB:4}, RCCar{S:15}" + System.lineSeparator();
        expected += "\tHerbie races around at 15mph!";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(72, car1.getHappiness());
        assertFalse(car1.isRetired());
        assertEquals(25, car1.getWear());
        assertEquals(20, car1.getBatteryLevel());
        assertEquals(4, car1.getNumBatteries());
        assertEquals(20, car1.getSpeed());

        outputStreamCaptor.reset();
        car1.play(37);
        expected = "PLAYING(37): Toy{PC:400, N:Herbie, H:72, R:false, W:25.0}, BatteryPowered{BL:20, NB:4}, RCCar{S:20}" + System.lineSeparator();
        expected += "\tHerbie races around at 20mph!" +  System.lineSeparator();
        expected += "\tDEPLETED:Toy{PC:400, N:Herbie, H:72, R:false, W:25.0}, BatteryPowered{BL:0, NB:4}, RCCar{S:20}"  + System.lineSeparator();
        expected += "\tRECHARGED:Toy{PC:400, N:Herbie, H:72, R:false, W:25.0}, BatteryPowered{BL:100, NB:4}, RCCar{S:20}"  + System.lineSeparator();
        expected += "RETIRED: Toy{PC:400, N:Herbie, H:109, R:true, W:45.0}, BatteryPowered{BL:100, NB:4}, RCCar{S:25}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(109, car1.getHappiness());
        assertTrue(car1.isRetired());
        assertEquals(45, car1.getWear());
        assertEquals(100, car1.getBatteryLevel());
        assertEquals(4, car1.getNumBatteries());
        assertEquals(25, car1.getSpeed());
    }

    /** More testing with second RCCar */
    @Test
    @Order(2)
    public void testSecondRCCar() {
        RCCar car2 = (RCCar) ToyFactory.makeToy("RC_CAR BatMobile 10");
        assertEquals(401, car2.getProductCode());
        assertEquals("BatMobile", car2.getName());
        assertEquals(0, car2.getHappiness());
        assertFalse(car2.isRetired());
        assertEquals(0, car2.getWear());
        assertEquals(100, car2.getBatteryLevel());
        assertEquals(10, car2.getNumBatteries());
        assertEquals(10, car2.getSpeed());
        assertEquals("Toy{PC:401, N:BatMobile, H:0, R:false, W:0.0}, BatteryPowered{BL:100, NB:10}, RCCar{S:10}", car2.toString());

        car2.play(98);
        String expected = "PLAYING(98): Toy{PC:401, N:BatMobile, H:0, R:false, W:0.0}, BatteryPowered{BL:100, NB:10}, RCCar{S:10}" + System.lineSeparator();
        expected += "\tBatMobile races around at 10mph!" + System.lineSeparator();
        expected += "\tDEPLETED:Toy{PC:401, N:BatMobile, H:0, R:false, W:0.0}, BatteryPowered{BL:0, NB:10}, RCCar{S:10}" + System.lineSeparator();
        expected += "\tRECHARGED:Toy{PC:401, N:BatMobile, H:0, R:false, W:0.0}, BatteryPowered{BL:100, NB:10}, RCCar{S:10}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(98, car2.getHappiness());
        assertFalse(car2.isRetired());
        assertEquals(10, car2.getWear());
        assertEquals(100, car2.getBatteryLevel());
        assertEquals(10, car2.getNumBatteries());
        assertEquals(15, car2.getSpeed());

        outputStreamCaptor.reset();
        car2.play(1);
        expected = "PLAYING(1): Toy{PC:401, N:BatMobile, H:98, R:false, W:10.0}, BatteryPowered{BL:100, NB:10}, RCCar{S:15}" + System.lineSeparator();
        expected += "\tBatMobile races around at 15mph!";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(99, car2.getHappiness());
        assertFalse(car2.isRetired());
        assertEquals(25, car2.getWear());
        assertEquals(89, car2.getBatteryLevel());
        assertEquals(10, car2.getNumBatteries());
        assertEquals(20, car2.getSpeed());

        // make sure RCCar extends BatteryPowered
        BatteryPowered bp = car2;
        assertEquals(89, bp.getBatteryLevel());
        assertEquals(10, bp.getNumBatteries());
    }
}