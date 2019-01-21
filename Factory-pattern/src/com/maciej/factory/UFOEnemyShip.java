package com.maciej.factory;

public class UFOEnemyShip extends EnemyShip {
    public UFOEnemyShip() {
        super(ShipType.UFO);
        buildShip();
        setName("UFO Enemy Ship");
        setAmountDamage(34.4);
        setWeight(3453);
    }

    @Override
    protected void buildShip() { System.out.println("Building ufo..."); }
}