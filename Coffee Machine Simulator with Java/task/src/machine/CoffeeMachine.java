package machine;

import java.util.Scanner;

import static machine.CoffeeMachine.*;

class Coffee {

    public static void makeEspresso() {
        if (w >= 250 && g >= 16) {
            System.out.println("I have enough resources, making you a coffee!");
            money += 4;
            disposableCups--;
            w -= 250;
            g -= 16;
            count++;
        } else {
            String query = w < 250 || g < 16 ? "Sorry, not enough water!" : "Sorry, not enough coffee beans!";
            System.out.println(query);
        }
    }

    public static void makeLatte() {

        if (w >= 350 && g >= 20 && l >= 75) {
            System.out.println("I have enough resources, making you a coffee!");
            w -= 350;
            l -= 75;
            g -= 20;
            disposableCups--;
            money += 7;
            count++;
        } else {
            if (w < 350) {
                System.out.println("Sorry, not enough water!");
            } else if (g < 20) {
                System.out.println("Sorry, not enough coffee beans!");
            } else if (l < 75) {
                System.out.println("Sorry, not enough milk!");
            }
        }
    }

    public static void makeCappuccino() {

        if (w >= 200 && g >= 12 && l >= 100) {
            System.out.println("I have enough resources, making you a coffee!");
            w -= 200;
            l -= 100;
            g -= 12;
            disposableCups--;
            money += 6;
            count++;
        } else {
            if (w < 200) {
                System.out.println("Sorry, not enough water!");
            } else if (g < 12) {
                System.out.println("Sorry, not enough coffee beans!");
            } else if (l < 100) {
                System.out.println("Sorry, not enough milk!");
            }
        }

    }

}

class CoffeeMachine {

    public static int w = 400;
    public static int l = 540;
    public static int g = 120;
    public static int disposableCups = 9;
    public static int money = 550;
    public static int count;


    public static void printCoffee() {


        while (true) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");

            String input = scanner.nextLine();

            states s = states.valueOf(input);

            switch (s) {
                case buy -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    String coffee = scanner.nextLine();
                    if (count == 10) {
                        System.out.println("I need cleaning!");
                    }

                    switch (coffee) {
                        case "1" -> {

                            Coffee.makeEspresso();
                        }
                        case "2" -> {

                            Coffee.makeLatte();
                        }
                        case "3" -> {

                            Coffee.makeCappuccino();
                        }
                        case "back" -> {

                        }
                    }
                }
                case fill -> {
                    String amount;
                    System.out.println("Write how many ml of water you want to add:");
                    amount = scanner.nextLine();
                    int water = Integer.parseInt(amount);
                    w += water;
                    System.out.println("Write how many ml of milk you want to add:");
                    amount = scanner.nextLine();
                    int milk = Integer.parseInt(amount);
                    l += milk;
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    amount = scanner.nextLine();
                    int grams = Integer.parseInt(amount);
                    g += grams;
                    System.out.println("Write how many disposable cups you want to add:");
                    amount = scanner.nextLine();
                    int disposable = Integer.parseInt(amount);
                    disposableCups += disposable;
                }
                case take -> {
                    System.out.println("I gave you $" + money);
                    money = 0;
                }
                case remaining -> {
                    System.out.println("The coffee machine has:");
                    System.out.println(w + " ml of water");
                    System.out.println(l + " ml of milk");
                    System.out.println(g + " g of coffee beans");
                    System.out.println(disposableCups + " disposable cups");
                    System.out.println("$" + money + " of money");
                }

                case clean -> {

                    count = 0;

                    System.out.println("I have been cleaned!");
                }

                case exit -> System.exit(0);
            }
        }
    }

    public static void main(String[] args) {

        printCoffee();

    }


    public enum states {
        buy, fill, take, clean, remaining, exit
    }
}
