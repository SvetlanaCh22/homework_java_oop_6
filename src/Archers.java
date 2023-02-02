import java.util.ArrayList;

public abstract class Archers extends Hero {

    int shots;

    public Archers(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[] damage, int health, int speed, int shots, int x, int y) {
        super(gang, name, role, attack, protection, damage, health, speed, x, y);
        this.shots = shots;
    }

    @Override
    public void step(ArrayList<Hero> heroList) {
        if (health == 0) {
            return;
        }
        Hero targetHero = getTarget(heroList);
        Vector2 target = targetHero.getPosition();

        float attackPower;
        final int minDst = 4;
        final int maxDst = 12;
        if (target.x <= minDst) {
            attackPower = damage[1];
        } else if (target.x >= maxDst) {
            attackPower = damage[0];
        } else {
            attackPower = damage[0] + ((float)(target.x - 4) / 8) * (damage[1] - damage[0]);
        }

        if (shots>0) {
            shots = shots-1; // выстрел
            // наносим урон
            int setDamage = targetHero.getHealth() - (int)Math.round((double)attackPower);
            // если просадили супостата ниже нуля - обнуляем здоровье
            if (setDamage<0) setDamage = 0;
            // выведем для теста
            if (Main.debugMessages) System.out.println("\n" + getRole() + " " + getName() + " set [hp] of " + targetHero.getRole() + " " + targetHero.getName() + " to " + setDamage + "\n");
            // уменьшим здоровье врага
            targetHero.setHealth(setDamage);
        }

        // проверяем крестьян
        for (Hero hero : gang) {
            String role = hero.getRole();
            if (role.equals("Peasant")) {
                // если это крестьянин - попробуем взять стрелу
                Peasant peasant = (Peasant)hero;
                if (peasant.getDelivery() == 1) {
                    peasant.setDelivery(0); // установим у крестьянина флаг свободности 0.
                    shots++; // увеличим число стрел
                    // других крестьян не трогаем - выходим
                    break;
                }
            }
        }
    }

    private Hero getTarget(ArrayList<Hero> heroList) {
        System.out.println();
        Hero heroObj = heroList.get(0); // текущий враг
        Hero heroTarget = heroObj; // ближайший враг
        double minDistance = getPosition().getDistance(heroObj.getPosition().x, heroObj.getPosition().y);
        for (int i = 1; i < heroList.size(); i++) {
            heroObj = heroList.get(i); // текущий враг
            double heroDistance = getPosition().getDistance(heroObj.getPosition().x, heroObj.getPosition().y);
            if (heroDistance < minDistance && heroObj.getHealth() > 0) {
                minDistance = heroDistance;
                heroTarget = heroObj; // ближайший враг
            }
            // выведем для теста текущего врага
            if (Main.debugMessages) System.out.println("Distance from "+getRole()+" "+getName()+" to "+heroObj.getRole()+" "+heroObj.getName()+" is "+heroDistance);
        }
        if (Main.debugMessages) System.out.println("[TARGET] Distance from "+getRole()+" "+getName()+" to "+heroTarget.getRole()+" "+heroTarget.getName()+" is "+minDistance);
        return heroTarget;
    }

    @Override
    public String getInfo(){
        return super.getInfo() + " [shots] " + shots;
    }

    @Override
    public String toString() {
        return super.toString() + ", Shots: " + shots;
    }
}
