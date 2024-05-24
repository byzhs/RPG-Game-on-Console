public class Warrior extends Avatar {

    private int strength;
    private int attackPower;

    public Warrior(String characterName, int health, double capacity, int strength, int attackPower) {
        super(characterName, health, capacity);
        this.strength = strength;
        this.attackPower = attackPower;

    }


    public int getAttackDamage(Monster monster) {
        int damage = this.attackPower;
        Weapon weapon = (Weapon) getHand();
        if (weapon != null && monster.matchVulnerability(weapon)) {
            damage *= monster.getVulnerabilityMultiplier();
        }
        return damage + weapon.calculateAttack(monster);
    }



    @Override
    public int attack() {
        if (getHand() instanceof Weapon) {
            Weapon weapon = (Weapon) getHand();
            return weapon.getBaseAttack() + weapon.getBonus();
        } else if (getHand() instanceof Potion) {
            Potion potion = (Potion) getHand();
            setHealth(getHealth() + potion.getHealAmount());
            return 0;
        } else {
            return 0;
        }
    }
}

