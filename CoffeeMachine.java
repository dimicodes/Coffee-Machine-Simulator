package machine;

import java.util.Scanner;

public class CoffeeMachine {
    // Instance variables representing the current state of the machine
    private int usdSupply = 550;
    private int waterSupply = 400;
    private int milkSupply = 540;
    private int groundCoffeeSupply = 120;
    private int disposableCupSupply = 9;
    private boolean machineRunning = true;

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (coffeeMachine.machineRunning) {
            coffeeMachine.userAction();
        }
    }

    void userAction() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        switch (userChoice.toLowerCase()) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                printMachineSupply();
                break;
            case "exit":
                machineRunning = false;
                break;
            default:
                System.out.println("Invalid option, please try again.");
        }
    }

    void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine().toLowerCase();

        switch (userChoice) {
            case "1": // Espresso
                if (canMakeCoffee(250, 0, 16)) {
                    makeCoffee(250, 0, 16, 4);
                }
                break;
            case "2": // Latte
                if (canMakeCoffee(350, 75, 20)) {
                    makeCoffee(350, 75, 20, 7);
                }
                break;
            case "3": // Cappuccino
                if (canMakeCoffee(200, 100, 12)) {
                    makeCoffee(200, 100, 12, 6);
                }
                break;
            case "back": // Go back
                userAction();
            default:
                System.out.println("Invalid coffee type.");
        }
    }

    void fill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        waterSupply += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milkSupply += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        groundCoffeeSupply += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        disposableCupSupply += scanner.nextInt();
    }

    void take() {
        System.out.println("I gave you $" + usdSupply);
        usdSupply = 0;
    }

    void printMachineSupply() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(waterSupply + " ml of water");
        System.out.println(milkSupply + " ml of milk");
        System.out.println(groundCoffeeSupply + " g of coffee beans");
        System.out.println(disposableCupSupply + " disposable cups");
        System.out.println("$" + usdSupply + " of money");
    }

    boolean canMakeCoffee(int waterNeeded, int milkNeeded, int coffeeNeeded) {
        if (waterSupply < waterNeeded) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (milkSupply < milkNeeded) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (groundCoffeeSupply < coffeeNeeded) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        } else if (disposableCupSupply < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        } else {
            return true;
        }
    }

    void makeCoffee(int waterNeeded, int milkNeeded, int coffeeNeeded, int price) {
        waterSupply -= waterNeeded;
        milkSupply -= milkNeeded;
        groundCoffeeSupply -= coffeeNeeded;
        disposableCupSupply -= 1;
        usdSupply += price;
        System.out.println("I have enough resources, making you a coffee!");
    }
}
