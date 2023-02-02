import java.util.ArrayList;

public class Warlock extends Healers {
    int magic;

    public Warlock(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int mana, int magic, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, mana, x, y);
        this.magic = magic;
    }

    public Warlock(ArrayList<Hero> gang, String name, int x, int y){
        super(gang, name, "Warlock", 17,12,new int[] {-5,-5},30,9, 9, x, y);
        this.magic = 1;
    }

    @Override
    public String toString() {
        return super.toString() + ".  Magic: " + magic;
    }
}
