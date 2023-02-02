import java.util.ArrayList;

public class Spearman extends Warriors{

    public Spearman(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, x, y);
    }

    public Spearman(ArrayList<Hero> gang, String name, int x, int y){
        super(gang, name, "Spearman",4,5,new int[] {1,3},10,4, x, y);
    }
}
