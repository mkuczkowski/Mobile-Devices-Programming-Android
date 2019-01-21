package com.maciej.factory;

public class MainFactory {
    public static void main(String[] args) {
        EnemyShip ship = EnemyShipFactory.makeEnemyShip(ShipType.DRONE);
        System.out.println(ship.toString());
        ship = EnemyShipFactory.makeEnemyShip(ShipType.ROCKET);
        System.out.println(ship.toString());
        ship = EnemyShipFactory.makeEnemyShip(ShipType.UFO);
        System.out.println(ship.toString());
    }
}