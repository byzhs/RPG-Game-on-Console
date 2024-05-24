import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter your name:");

        Scanner scanner = new Scanner(System.in);
        String characterName = scanner.nextLine();


        Avatar player = new Avatar(characterName, 100, 10);

        System.out.println("Do you want to play as an Avatar or a Warrior?");
        System.out.println("Enter '1' for Avatar or '2' for Warrior");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            player = new Avatar(characterName, 100, 10);
        } else if (choice == 2) {
            player = new Warrior(characterName, 100, 50, 10, 25);
        }
        startGame(scanner, player);


        System.out.println("Hello " + characterName + "! Welcome to our adventure. Don't forget to wear weapon before you fight. ");

        startGame(scanner, player);

    }

    public static void startGame(Scanner scanner, Avatar player) {


        Warrior warrior = new Warrior(player.getCharacterName(), 100, 50, 10, 25);


        List<Monster> monsters = new ArrayList<>();
        Random random = new Random();
        monsters.add(new Monster("Alperen", 70, random.nextInt(15), 10, Monster.WeaponType.SWORD));
        random = new Random();
        monsters.add(new Monster("ArzuKitchen", 60, random.nextInt(15), 10, Monster.WeaponType.BOW));
        random = new Random();
        monsters.add(new Monster("!Az", 100, random.nextInt(15) , 10, Monster.WeaponType.DAGGER));
        random = new Random();
        monsters.add(new Monster("Yoma", 80, random.nextInt(15) , 10, Monster.WeaponType.LANCE));
        random = new Random();
        monsters.add(new Monster("CeLL", 80, random.nextInt(15) , 10, Monster.WeaponType.AXE));
        random = new Random();
        monsters.add(new Monster("Titan", 120, random.nextInt(15) , 10, Monster.WeaponType.AXE));
        random = new Random();
        monsters.add(new Monster("Ghouls", 80, random.nextInt(15) , 10, Monster.WeaponType.AXE));

        for (Monster monster : monsters) {
            int damage = random.nextInt(15);
            monster.setDamage(damage);
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(monsters.size());
        Monster selectedMonster = monsters.get(randomIndex);


        boolean gameOver = false;
        while (!gameOver) {


            System.out.println("Please choose your action:");
            System.out.println("1. Go left");
            System.out.println("2. Go right");
            System.out.println("3. Go straight");
            System.out.println("4. Open backpack");
            System.out.println("5. Help");
            System.out.println("6. See profile");

            Random rand456 = new Random();
            int randomIndex34 = rand456.nextInt(monsters.size());
            Monster selectedMonster345 = monsters.get(randomIndex34);


            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character


            if (choice == 1) {
                // Go left
                System.out.println("********************");
                System.out.println("You encountered a monster: " + selectedMonster345.getName());
                System.out.println("********************");
                fight(player, selectedMonster345, scanner);
                Arena arena = new Arena(player, selectedMonster345);
                arena.fight();
            } else if (choice == 2) {
                // Go right
                System.out.println("********************");
                System.out.println("You encountered a monster: " + selectedMonster345.getName());
                System.out.println("********************");
                fight(player, selectedMonster345, scanner);
                Arena arena = new Arena(player, selectedMonster);
                arena.fight();

            } else if (choice == 3) {
                // Go straight
                System.out.println("********************");
                System.out.println("You encountered a monster: " + selectedMonster345.getName());
                System.out.println("********************");
                fight(player, selectedMonster345, scanner);
                Arena arena = new Arena(player, selectedMonster);
                arena.fight();

            } else if (choice == 4) {
                // Open backpack
                openBackpack(player, scanner);

            } else if (choice == 5) {
                // Help
                System.out.println("********************");
                System.out.println("This is a survival game to fight with monsters. If you are having trouble " +
                        "winning don't forget to open your backpack and wear your weapon to attack.");
                System.out.println("********************");

            } else if (choice == 6) {
                System.out.println("********************");
                System.out.println("Name: " + player.getCharacterName());
                System.out.println("Health: " + player.getHealth());

                if (player.getHand() != null) {
                    System.out.println("Held Weapon: " + player.getHand().getName());
                } else {
                    System.out.println("You are not holding any weapon.");
                }

                System.out.println("Backpack used capacity: " + player.getUsedCapacity() + "/" + player.getCapacity());
                System.out.println("********************");


            }

            if (player.getHealth() <= 0) {
                gameOver = true;
                System.out.println("Game over! The player has died.");
            }


        }

    }

    private static void fight(Avatar player, Monster monster, Scanner scanner) {

        while (player.isAlive()) {
            System.out.println("What do you want to do?");
            System.out.println("1. Attack");
            System.out.println("2. Use potion");
            System.out.println("3. Run away");
            System.out.println("4. Open backpack");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character



            if (choice == 1) {
                // Attack

                if ( player.getHealth() > 0 && player.getHand() != null) {
                    double damage = player.attack();
                    monster.hurt(damage);
                    System.out.println("********************");
                    System.out.println(player.getCharacterName() + " attacked " + monster.getName() + " for " + damage + " damage");
                } else {
                    if (player.getHealth() <= 0) {
                        System.out.println("You need to have enough health to attack!");
                    }
                    if (player.getHand() == null) {
                        System.out.println("You need to equip a weapon before attacking!");
                    }
                }
            } else if (choice == 2) {
                // Use potion
                player.usePotion();
            } else if (choice == 3) {
                // Run away
                if(player.getHealth() > 30) {
                    break;
                } else {
                    System.out.println("You cannot run away as your health is too low.");
                }
            } else if (choice == 4) {
                // Open backpack
                openBackpack(player, scanner);
            }

            if (monster.isAlive()) {
                double damage = monster.attack();
                player.hurt(damage);
                System.out.println(monster.getName() + " attacked " + player.getCharacterName() + " for " + damage + " damage");
            }
            if (!player.isAlive() || !monster.isAlive()) {
                break;
            }
        }

        if (player.isAlive()) {
            System.out.println("********************");
            System.out.println("You won the fight!");
            System.out.println("********************");
            // Give player a potion as a reward
            player.addToBackpack(new Potion("Heal", 1,15));
            startGame(scanner, player);
        } else {
            System.out.println("********************");
            System.out.println("You died in the fight. Monsters win!");
            System.out.println("********************");
            System.out.println("Do you want to restart the game? (yes/no)");
            String restart = scanner.nextLine();
            if (restart.equalsIgnoreCase("yes")) {
                // Restart the game by calling the main method again
                main(null);
            } else {
                // Exit the game
                System.exit(0);
            }
        }
    }




    private static void openBackpack(Avatar player, Scanner scanner) {
        System.out.println("Backpack:");
        for (int i = 0; i < player.getBackpack().size(); i++) {
            System.out.println((i + 1) + ". " + player.getBackpack().get(i));
        }

        System.out.println("What do you want to do?");
        System.out.println("1. Hold weapon");
        System.out.println("2. Drop weapon");
        System.out.println("3. Use potion");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (choice == 1) {
            System.out.println("Which weapon would you like to hold?");
            System.out.println("1. Axe");
            System.out.println("2. Sword");
            System.out.println("3. Bow");
            System.out.println("4. Lance");
            System.out.println("5. Dagger");
            int weaponChoice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            Weapon weapon = null;
            if (weaponChoice == 1) {
                weapon = new Weapon("Axe",1,25,2, Weapon.WeaponType.AXE);
                player.setHand(weapon);
            } else if (weaponChoice == 2) {
                weapon = new Weapon("Sword",1,18,1, Weapon.WeaponType.SWORD);
                player.setHand(weapon);
            } else if (weaponChoice == 3) {
                weapon = new Weapon("Bow",1,10,3, Weapon.WeaponType.BOW);
                player.setHand(weapon);
            } else if (weaponChoice == 4) {
                weapon = new Weapon("Lance",1,20,3, Weapon.WeaponType.LANCE);
                player.setHand(weapon);
            } else if (weaponChoice == 5) {
                weapon = new Weapon("Dagger",1,12,3, Weapon.WeaponType.DAGGER);
                player.setHand(weapon);
            }


            System.out.println("********************");
            System.out.println("You are now holding a " + weapon.getType() + " with attack power of " + weapon.getBaseAttack());
            System.out.println("********************");
        } else if (choice == 2) {
            // code to let go of weapon
            player.dropHandItem();
        } else if (choice == 3) {
            if (player.getBackpack().size() == 0) {
                System.out.println("You have no potions to use.");
            } else {
                System.out.println("Do you want to use a potion? (Enter 1 for yes, 0 for no)");
                int usePotionChoice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                if (usePotionChoice == 1){
                Potion potion = (Potion) player.getBackpack().get(usePotionChoice - 1);
                player.usePotion();}
                //player.getBackpack().remove(potionChoice - 1);

                System.out.println("Potions in your backpack:");
                for (int i = 0; i < player.getBackpack().size(); i++) {
                    System.out.println((i + 1) + ". " + player.getBackpack().get(i).getName());
                }
            }
        }

    }

}





