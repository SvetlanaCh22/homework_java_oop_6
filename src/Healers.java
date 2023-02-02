import java.util.ArrayList;

public abstract class Healers extends Hero {
    int mana;

    public Healers(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int mana, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, x, y);
        this.mana = mana;
    }

    @Override
    public void step(ArrayList<Hero> heroList) {
        System.out.println();
        double minHealth = 100;
        int minHealthHero = 0;
        for (int i = 0; i < gang.size(); i++) {
            int heroHealth = gang.get(i).getHealth();
            if (heroHealth == 0) {
                continue;
            }
            int heroMaxHealth = gang.get(i).getMaxHealth();
            if (heroHealth < heroMaxHealth){
                double temp = ((double)heroHealth / (double)heroMaxHealth) * 100;
                if (temp < minHealth) {
                    minHealth = temp;
                    minHealthHero = i;
                }
            }
        }
        // лечим героя с минимумом здоровья
        healing(gang.get(minHealthHero));
    }

    void healing(Hero weak) {
        int currentHealth = weak.getHealth();
        int healingPower = damage[0];
        if (Math.abs(healingPower - currentHealth) > weak.getMaxHealth()) {
            weak.setHealth(weak.getMaxHealth());
            if (Main.debugMessages) System.out.printf("Hero %s %s complete healing hero %s %s. Current [hp] is: %d/%d\n", name, role, weak.getName(), weak.getRole(), weak.getHealth(), weak.getMaxHealth());
        } else {
            weak.setHealth(Math.abs(healingPower - currentHealth));
            if (Main.debugMessages) System.out.printf("Hero %s %s healing hero %s %s on %d points. Current [hp] is: %d/%d\n", name, role, weak.getName(), weak.getRole(), Math.abs(healingPower), weak.getHealth(), weak.getMaxHealth());
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Mana: " + mana;
    }

}

