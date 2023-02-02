import java.util.ArrayList;

public class Peasant extends Hero {
    int delivery;
    public Peasant(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int delivery, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, x, y);
        this.delivery = delivery;
    }

    public Peasant(ArrayList<Hero> gang, String name, int x, int y){
        super(gang, name, "Peasant",1,1, new int[] {1,1},1,3, x, y);
        this.delivery = 1;
    }

    public int getDelivery(){
        return delivery;
    }

    public void setDelivery(int value){
        delivery = value;
    }

    @Override
    public String toString(){
        return super.toString() + ".  Delivery: " + delivery;
    }
}

