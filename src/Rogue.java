import java.util.ArrayList;

public class Rogue extends Warriors {

    public Rogue(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, x, y);
    }

    public Rogue(ArrayList<Hero> gang, String name, int x, int y){
        super(gang, name, "Rogue",8,3, new int[] {2,4},10,6, x, y);
    }
}
