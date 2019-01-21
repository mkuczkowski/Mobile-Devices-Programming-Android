package com.maciej.factory;

public class RocketEnemyShip extends EnemyShip {
    public RocketEnemyShip() {
        super(ShipType.ROCKET);
        buildShip();
        setName("Rocket Ship");
        setAmountDamage(15.8);
        setWeight(2765);
    }

    @Override
    protected void buildShip() { System.out.println("Building rocket ship..."); }
}