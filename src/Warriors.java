import java.util.ArrayList;

public abstract class Warriors extends Hero {

    int stepsToTarget;

    public Warriors(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, x, y);
    }

    @Override
    public void step(ArrayList<Hero> heroList) {
        if (health == 0) {
            return;
        }
        Hero targetHero = getTarget(heroList);
        Vector2 target = targetHero.getPosition();
        Vector2 me = this.getPosition();

        if (stepsToTarget==0) { // бьем если рядом со врагом
            // наносим урон
            int setDamage = targetHero.getHealth() - damage[1];
            // если просадили супостата ниже нуля - обнуляем здоровье
            if (setDamage<0) setDamage = 0;
            // выведем для теста
            if (Main.debugMessages) System.out.println("\n" + getRole() + " " + getName() + " set [hp] of " + targetHero.getRole() + " " + targetHero.getName() + " to " + setDamage + "\n");
            // уменьшим здоровье врага
            targetHero.setHealth(setDamage);
        } else { // идем к врагу
            int xSteps = target.x - me.x;
            // в какую сторону идти
            int xDirection;
            if (xSteps<0) { xDirection = -1; } else { xDirection = 1; }
            // если не выходим за границы
            if (Math.abs(xSteps) > 0 && (me.x + xDirection) > 0 && (me.x + xDirection) <= Main.GANG_SIZE && IsCellFree(heroList,me.x + xDirection, me.y)) { // если можем идти по x - идем
                this.setPosition(me.x + xDirection, me.y);
            } else { // ходим по y
                int ySteps = target.y - me.y;
                // в какую сторону идти
                int yDirection;
                if (xSteps<0) { yDirection = -1; } else { yDirection = 1; }
                if (Math.abs(ySteps) > 0 && (me.y + yDirection) > 0 && (me.y + yDirection) <= Main.GANG_SIZE && IsCellFree(heroList,me.x, me.y + yDirection)) { // если можем идти по x - идем
                    this.setPosition(me.x, me.y + yDirection);
                }
            }
            // выведем для теста
            if (Main.debugMessages) System.out.println("\n" + getRole() + " " + getName() + " new position is: x = " + getPosition().x + ", y = " + getPosition().y + "\n");
        }
    }

    public boolean IsCellFree(ArrayList<Hero> heroList, int x, int y) {
        boolean free1 = true;
        boolean free2 = true;
        for (Hero hero : heroList) {
            if (hero.position.x == x) {
                if (hero.position.y == y) {
                    free1 = false;
                }
            }
        }
        for (Hero hero : gang) {
            if (hero.position.x == x) {
                if (hero.position.y == y) {
                    free2 = false;
                }
            }
        }
        return (free1 && free2);
    }

    private Hero getTarget(ArrayList<Hero> heroList) {
        System.out.println();
        Hero heroObj = heroList.get(0); // текущий враг
        Hero heroTarget = heroObj; // ближайший враг
        int minDistance = getPosition().getDistanceInSteps(heroObj.getPosition().x, heroObj.getPosition().y);
        for (int i = 1; i < heroList.size(); i++) {
            heroObj = heroList.get(i); // текущий враг
            int heroDistance = getPosition().getDistanceInSteps(heroObj.getPosition().x, heroObj.getPosition().y);
            if (heroDistance < minDistance && heroObj.getHealth() > 0) {
                minDistance = heroDistance;
                heroTarget = heroObj; // ближайший враг
            }
            // выведем для теста текущего врага
            if (Main.debugMessages) System.out.println("Steps from "+getRole()+" "+getName()+" to "+heroObj.getRole()+" "+heroObj.getName()+" is "+heroDistance);
        }
        if (Main.debugMessages) System.out.println("[TARGET] Steps from "+getRole()+" "+getName()+" to "+heroTarget.getRole()+" "+heroTarget.getName()+" is "+minDistance);
        stepsToTarget = minDistance;
        return heroTarget;
    }

    @Override
    public String getInfo(){
        return super.getInfo();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
