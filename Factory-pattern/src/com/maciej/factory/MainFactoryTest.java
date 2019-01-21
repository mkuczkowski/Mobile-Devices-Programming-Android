package com.maciej.factory;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainFactoryTest {

    @Test
    public void main() {
        EnemyShip newShip = EnemyShipFactory.makeEnemyShip(ShipType.DRONE);
        assertNotNull(newShip);
        assertTrue(newShip instanceof DroneShip);
        assertEquals("Dangerous drone", newShip.getName());
        assertEquals(3.4, newShip.getAmountDamage(), 0);
        assertEquals(5, newShip.getWeight(), 0);
        newShip = EnemyShipFactory.makeEnemyShip(ShipType.ROCKET);
        assertNotNull(newShip);
        assertTrue(newShip instanceof RocketEnemyShip);
        assertEquals("Rocket Ship", newShip.getName());
        assertEquals(15.8, newShip.getAmountDamage(), 0);
        assertEquals(2765, newShip.getWeight(), 0);
        newShip = EnemyShipFactory.makeEnemyShip(ShipType.UFO);
        assertNotNull(newShip);
        assertTrue(newShip instanceof UFOEnemyShip);
        assertEquals("UFO Enemy Ship", newShip.getName());
        assertEquals(34.4, newShip.getAmountDamage(), 0);
        assertEquals(3453, newShip.getWeight(), 0);
    }
}