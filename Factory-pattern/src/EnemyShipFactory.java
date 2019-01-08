public class EnemyShipFactory {
    public EnemyShip makeEnemyShip(String type) {
        if(type.equals("Ufo")) {
            return new UFOEnemyShip();
        } else if (type.equals("Rocket")) {
            return new RocketEnemyShip();
        } else if (type.equals("Big ufo")) {
            return new BigUFOEnemyShip();
        } else if (type.equals("Drone")) {
            return new DroneShip();
        } else return null;
    }
}