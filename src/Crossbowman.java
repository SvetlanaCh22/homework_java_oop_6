import java.util.ArrayList;

public class Crossbowman extends Archers{

    public Crossbowman(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int shots, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, shots, x, y);
    }

    public Crossbowman(ArrayList<Hero> gang, String name, int x, int y){
        super(gang, name, "Crossbowman",6,3,new int[] {2,3},10,4, 16, x, y);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
