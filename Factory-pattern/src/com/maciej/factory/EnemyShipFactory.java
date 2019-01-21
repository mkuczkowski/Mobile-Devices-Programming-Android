package com.maciej.factory;

public class EnemyShipFactory {
    public static EnemyShip makeEnemyShip(ShipType type) {
        EnemyShip ship = null;
        switch (type) {
            case DRONE:
                ship = new DroneShip();
                break;
            case UFO:
                ship = new UFOEnemyShip();
                break;
            case ROCKET:
                ship = new RocketEnemyShip();
                break;
        }
        return ship;
    }
}