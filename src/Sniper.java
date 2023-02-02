import java.util.ArrayList;

public class Sniper extends Archers {

    public Sniper(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int shots, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, shots, x, y);
    }

    public Sniper(ArrayList<Hero> gang, String name, int x, int y)
    {
        super(gang, name, "Sniper", 12,10, new int[] {8,10},15,9, 32, x, y);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
