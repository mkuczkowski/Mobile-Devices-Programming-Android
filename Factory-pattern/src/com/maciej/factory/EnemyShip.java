package com.maciej.factory;

public abstract class EnemyShip {
    private String name;
    private double amountDamage;
    private int weight;
    private ShipType type;

    public String getName() { return name; }
    public void setName(String newName) { name = newName; }

    public double getAmountDamage() { return amountDamage; }
    public void setAmountDamage(double newAmount) { amountDamage = newAmount; }

    public int getWeight() { return weight; }
    public void setWeight(int newWeight) { weight = newWeight; }

    public EnemyShip(ShipType type) { this.type = type; }

    protected abstract void buildShip();

    @Override
    public String toString() {
        return this.getName() + " weighs " + this.getWeight() + " kg and does " +
                this.getAmountDamage() + " damage";
    }
}