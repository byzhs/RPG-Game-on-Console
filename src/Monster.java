import java.util.Random;

public class Monster {
    private int health;
    private int attackPower;
    private double attackChance;
    private int damage;
    private String name;
    private WeaponType vulnerability;
    private int attackPoints;

    public enum WeaponType {
        SWORD, AXE, BOW, LANCE, DAGGER
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Monster(String name, int health, int attackPower, double attackChance, WeaponType vulnerability) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.attackChance = attackChance;
        this.vulnerability = vulnerability;

        Random random = new Random();
        this.attackPoints = random.nextInt(10) + 1;
    }

    public WeaponType getVulnerability() {
        return vulnerability;
    }
    public void setVulnerability(WeaponType vulnerability) {
        this.vulnerability = vulnerability;
    }


    public boolean matchVulnerability(Weapon weapon) {
        return this.vulnerability.equals(weapon.getType());
    }

    public double getVulnerabilityMultiplier() {
        return 1.5;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public double getAttackChance() {
        return attackChance;
    }

    public int calculateBonusDamage(Weapon weapon) {
        if (matchVulnerability(weapon)) {
            return this.attackPoints * weapon.getAttackBonus();
        } else {
            return 0;
        }
    }

    public int attack() {
        int randomValue = new Random().nextInt(5);
        if (randomValue < attackChance) {
            return attackPower;
        } else {
            return 0;
        }
    }
    public boolean isAlive() {
        return health > 0;
    }

    public void hurt(double damage) {
        health -= damage;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "health=" + health +
                ", attackPower=" + attackPower +
                ", attackChance=" + attackChance +
                '}';
    }
}


