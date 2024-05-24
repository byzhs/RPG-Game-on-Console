import java.util.ArrayList;
import java.util.List;

public class Avatar {
    private String characterName;
    private int health;
    private Stuff hand;
    private double capacity;
    private List<Stuff> backpack;
    private boolean canDrinkPotion;
    private Weapon heldWeapon;
    private double usedCapacity;


    public void setHealth(int health) {
        this.health = health;
    }

    public Avatar(String characterName, int health, double capacity) {
        this.characterName = characterName;
        this.health = health;
        this.hand = null;
        this.capacity = capacity;
        this.backpack = new ArrayList<>();
        this.canDrinkPotion = true;
    }


    public String getCharacterName() {
        return characterName;
    }


    public void addToBackpack(Stuff item) {
        if (this.usedCapacity + item.getWeight() <= this.capacity) {
            this.backpack.add(item);
            this.usedCapacity += item.getWeight();
        } else {
            System.out.println("Not enough capacity in your backpack.");
        }
    }

    public double getUsedCapacity() {
        return usedCapacity;
    }

    public double getCapacity() {
        return capacity;
    }



    public void dropHandItem() {
        if (hand != null) {
            System.out.println("You dropped the " + hand.getName() + ".");
            hand = null;
        } else {
            System.out.println("You don't have anything in your hand.");
        }
    }

    public void usePotion() {
        if (backpack.size() > 0) {
            for (Stuff item : backpack) {
                if (item instanceof Potion) {
                    Potion potion = (Potion) item;
                    int healAmount = potion.getHealAmount();
                    int newHealth = health + healAmount;
                    setHealth(newHealth);
                    System.out.println(characterName + " used a potion and healed for " + healAmount + " health.");
                    System.out.println("New health: " + newHealth);
                    backpack.remove(item);
                    canDrinkPotion = false;
                    break;
                }
            }
        } else {
            System.out.println("You have no potion to use!");
        }
    }


    public int getHealth() {
        return health;
    }

    public Stuff getHand() {
        return hand;
    }

    public void setHand(Stuff hand) {
        this.hand = hand;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int attack() {
        if (this.hand != null && this.hand instanceof Weapon) {
            Weapon weapon = (Weapon) this.hand;
            int damage = weapon.getBaseAttack();
            canDrinkPotion = true;
            return damage;
        } else if (this.hand != null && this.hand instanceof Potion && canDrinkPotion) {
            Potion potion = (Potion) this.hand;
            int heal = potion.getHealAmount();
            this.hand = null;
            return heal;
        } else {
            System.out.println("You need to hold a weapon or a usable potion in order to attack!");
            return 0;
        }
    }

    public List<Stuff> getBackpack() {
        return backpack;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "characterName='" + characterName + '\'' +
                ", health=" + health +
                ", hand=" + hand +
                '}';
    }

    public String getName() {
        return characterName;
    }


    public void hurt(double damage) {
        health -= damage;
        if(health <= 0) health = 0;
    }


}

