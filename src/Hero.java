import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Hero {
    protected String name, role;
    protected int attack, protection, maxhealth, health, speed;
    protected int[] damage;
    protected ArrayList<Hero> gang;
    protected Vector2 position;

    public Hero(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[]damage, int health, int speed, int x, int y){
        this.gang = gang;
        this.name = name;
        this.role = role;
        this.attack = attack;
        this.protection = protection;
        this.damage =damage;
        this.maxhealth = health;
        this.health = maxhealth - new Random().nextInt(maxhealth);
        this.speed = speed;
        this.position = new Vector2(x, y);
    }

    public String getInfo(){
       return role + " " + name + " [hp] " + health + "/" + maxhealth;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void setPosition(int x, int y){
        position.x = x;
        position.y = y;
    }

    public String getName(){
        return name;
    }

    public String getRole(){
        return role;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int value){
        health = value;
    }

    public int getMaxHealth(){
        return maxhealth;
    }

    protected void getDamage(float attackPower) {
        this.health -= attackPower;
        if (this.health < 0) {
            this.health = 0;
        } else if (this.health > maxhealth) {
            this.health = maxhealth;
        }
        //System.out.println(this.name + " " + this.health);
    }

    public void step(ArrayList<Hero> heroList) {

    }

    @Override
    public String toString(){
        return ("Name: " + name + ".  Role: " + role + ".  Attack: " + attack + ".  Protection: " + protection +
                ".  Damage: " + Arrays.toString (damage) + ".  Health: " + health +
                ".  Speed: " + speed);
    }

}

