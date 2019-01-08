import java.util.Scanner;

public class EnemyShipTesting {
    public static void main(String[] args) {
        EnemyShipFactory shipFactory = new EnemyShipFactory();
        EnemyShip ship = null;
        Scanner input = new Scanner(System.in);
        System.out.println("What type of ship do you want? (Ufo / Drone / Rocket / Big ufo)");
        if(input.hasNextLine()) {
            String shipType = input.nextLine();
            ship = shipFactory.makeEnemyShip(shipType);
        }
        if(ship != null)
            makeActions(ship);
        else
            System.out.println("Invalid type of ship! Types are: Ufo / Drone / Rocket / Big ufo");
    }

    public static void makeActions(EnemyShip ship) {
        ship.displayEnemyShip();
        ship.enemyShipShoots();
        ship.displayShipWeight();
    }
}