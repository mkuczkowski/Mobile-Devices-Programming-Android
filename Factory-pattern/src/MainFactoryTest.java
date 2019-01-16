import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class MainFactoryTest {

    @Test
    public void main() {
        EnemyShipFactory shipFactory = new EnemyShipFactory();
        assertNotNull(shipFactory);
        EnemyShip ship = null;
        assertNull(ship);
        ship = shipFactory.makeEnemyShip("Drone");
        assertNotNull(ship);
        assertTrue(ship instanceof DroneShip);
        assertFalse(ship instanceof UFOEnemyShip);
        ship = shipFactory.makeEnemyShip("Ufo");
        assertTrue(ship instanceof UFOEnemyShip);
        ship = shipFactory.makeEnemyShip("Big ufo");
        assertTrue(ship instanceof BigUFOEnemyShip);
        ship = shipFactory.makeEnemyShip("Rocket");
        assertTrue(ship instanceof RocketEnemyShip);
        ship = shipFactory.makeEnemyShip("");
        assertNull("Empty type should be null", ship);
        ship = shipFactory.makeEnemyShip("Airplane");
        assertNull("Invalid type should be null", ship);
    }

    @Test
    public void makeActions() {
        EnemyShipFactory shipFactory = new EnemyShipFactory();
        EnemyShip ship = shipFactory.makeEnemyShip("Drone");
        assertNotNull(ship);
        assertEquals("Dangerous drone", ship.getName());
        assertEquals(3.4, ship.getAmountDamage(), 0);
        assertEquals(5, ship.getWeight());
        ship = shipFactory.makeEnemyShip("Ufo");
        assertNotNull(ship);
        assertEquals("UFO Enemy Ship", ship.getName());
        assertEquals(34.4, ship.getAmountDamage(), 0);
        assertEquals(3453, ship.getWeight());
        ship = shipFactory.makeEnemyShip("Big ufo");
        assertNotNull(ship);
        assertEquals("Big UFO", ship.getName());
        assertEquals(54.5, ship.getAmountDamage(), 0);
        assertEquals(14432, ship.getWeight());
        ship = shipFactory.makeEnemyShip("Rocket");
        assertNotNull(ship);
        assertEquals("Rocket Ship", ship.getName());
        assertEquals(15.8, ship.getAmountDamage(), 0);
        assertEquals(2765, ship.getWeight());
    }
}