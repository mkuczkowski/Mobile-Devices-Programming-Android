package com.maciej.factory;

public class DroneShip extends EnemyShip {
    public DroneShip() {
        super(ShipType.DRONE);
        buildShip();
        setName("Dangerous drone");
        setAmountDamage(3.4);
        setWeight(5);
    }

    @Override
    protected void buildShip() { System.out.println("Building drone..."); }
}