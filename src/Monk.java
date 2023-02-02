import java.util.ArrayList;

public class Monk extends Healers
{
    int magic;

    public Monk(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int mana, int magic, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, mana, x, y);
        this.magic = magic;
    }

    public Monk(ArrayList<Hero> gang, String name, int x, int y){
        super(gang, name, "Monk",12,7,new int[] {-4,-4},30,5, 5, x, y);
        this.magic = 1;
    }

    @Override
    public String toString() {
        return super.toString() + ".  Magic: " + magic;
    }
}
