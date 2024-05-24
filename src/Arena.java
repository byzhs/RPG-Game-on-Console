public class Arena {
    private Avatar avatar;
    private Monster monster;

    public Arena(Avatar avatar, Monster monster) {
        this.avatar = avatar;
        this.monster = monster;
    }

    public void fight() {
        while (avatar.isAlive() || !monster.isAlive()) {
            int avatarAttack = avatar.attack();
            monster.hurt(avatarAttack);
            int monsterAttack = monster.attack();
            avatar.hurt(monsterAttack);
        }

        if (avatar.isAlive()) {
            System.out.println("Avatar wins!");
        } else {
            System.out.println("Monster wins!");
        }
    }


}
