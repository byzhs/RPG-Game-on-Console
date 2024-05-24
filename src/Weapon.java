public class Weapon extends Stuff {

    private final WeaponType type;
    private int baseAttack;
    private int bonus;

    private int attackBonus;


    public enum WeaponType {
        SWORD(25), AXE(18), BOW(10), LANCE(20), DAGGER(12);
        private final int attackPower;
        WeaponType(int attackPower) {
            this.attackPower = attackPower;
        }
        public int getAttackPower() {
            return attackPower;
        }
    }


    public Weapon(String name, int weight, int baseAttack, int bonus, Weapon.WeaponType type) {
        super(name, weight);

        this.baseAttack = baseAttack;
        this.bonus = bonus;
        this.type = type;
        this.attackBonus = attackBonus;


    }

    public int calculateAttack(Monster monster) {
        if (monster.matchVulnerability(this)) {
            return (int) (getBaseAttack() + getAttackBonus() * 1.5 + monster.calculateBonusDamage(this));
        } else {
            return getBaseAttack() + getAttackBonus() + monster.calculateBonusDamage(this);
        }
    }


    public WeaponType getType() {
        return type;
    }

    public int getAttackBonus() {
        return this.type.getAttackPower() + this.bonus;
    }
    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + getName() + '\'' +
                ", weight=" + getWeight() +
                ", baseAttack=" + baseAttack +
                ", bonus=" + bonus +
                '}';
    }
}
