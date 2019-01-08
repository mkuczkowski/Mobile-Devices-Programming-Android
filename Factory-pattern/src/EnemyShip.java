public abstract class EnemyShip {
    private String name;
    private double amountDamage;
    private int weight;

    public String getName() { return name; }
    public void setName(String newName) { name = newName; }

    public double getAmountDamage() { return amountDamage; }
    public void setAmountDamage(double newAmount) { amountDamage = newAmount; }

    public int getWeight() { return weight; }
    public void setWeight(int newWeight) { weight = newWeight; }

    public void displayEnemyShip() {
        System.out.println(getName() + " is visible");
    }

    public void enemyShipShoots() {
        System.out.println(getName() + " shoots and does " + getAmountDamage() + " damage");
    }

    public void displayShipWeight() {
        System.out.println(getName() + " weighs " + getWeight() + " kilograms");
    }
}