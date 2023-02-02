/*
Реализовать полноценный выстрел у стрелков.

Поиск ближайшего противника, расчёт повреждения с учётом расстояния
и разницы атаки стрелка и защиты цели.

Ну и нанесение повреждения. Боец с нулём жизней считается мёртвым.
В дальнейшем не лечится и не наносит повреждений.

Мертвые бойцы обозначаются другим цветом.

Автор: Чубченко Светлана
Графика ConsoleView: Евгений Финогенов
*/

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int GANG_SIZE = 5;
    public static ArrayList<Hero> whiteSide;
    public static ArrayList<Hero> darkSide;

    public static boolean debugMessages;

    static void callResetPeasants(ArrayList<Hero> heroList) {
        for (Hero hero : heroList) {
            String role = hero.getRole();
            if (role.equals("Peasant")) {
                Peasant peasant = (Peasant)hero;
                peasant.setDelivery(1); // сбросим доступность
            }
        }
    }

    private static void init(){
        whiteSide = new ArrayList<>();
        darkSide = new ArrayList<>();

        final Random rand = new Random();

        String[] namesList = {"Grigorii", "Lev", "Andrei", "Roman", "Arsenii", "Stepan", "Vladislav", "Nikita", "Gleb",
                "Mark", "David", "Yaroslav", "Evgenii", "Matvei", "Fyodor", "Nicolai", "Aleksei", "Andrei", "Artemii",
                "Victor", "Nikita", "Daniil", "Denis", "Egor", "Igor", "Lev", "Leonid", "Pavel", "Petr", "Roman",
                "Ruslan", "Sergey", "Semion", "Timofei", "Stepan", "Vladimir", "Timofei", "Yaroslav", "Pavel", "Egor",
                "Sergey", "Vladislav", "Fedor", "Konstantin", "Maksim", "Artyom", "Nikita", "Yurii", "Platon", "Denis",
                "Yaroslav", "Miron", "Vasilii", "Lev", "Stepan", "Evgenii", "Savelii", "David", "Grigorii", "Timur",
                "Kirill", "Victor", "Fyodor", "Bogdan", "Konstantin", "Adam", "Leonid", "Roman", "Pavel", "Artemii",
                "Petr", "Aleksei", "Miron", "Vladimir"};
        int namesSize = namesList.length;

        int x = 1;
        int y = 1;
        for (int i = 0; i < GANG_SIZE; i++) {
            switch (new Random().nextInt(4)) {
                case 0 -> whiteSide.add(new Peasant(whiteSide, namesList[rand.nextInt(namesSize)], x, y++));
                case 1 -> whiteSide.add(new Rogue(whiteSide, namesList[rand.nextInt(namesSize)], x, y++));
                case 2 -> whiteSide.add(new Sniper(whiteSide, namesList[rand.nextInt(namesSize)], x, y++));
                default -> whiteSide.add(new Warlock(whiteSide, namesList[rand.nextInt(namesSize)], x, y++));
            }
        }

        x = GANG_SIZE;
        y = 1;
        for (int i = 0; i < GANG_SIZE; i++) {

            switch (new Random().nextInt(4)) {
                case 0 -> darkSide.add(new Peasant(darkSide, namesList[rand.nextInt(namesSize)], x, y++));
                case 1 -> darkSide.add(new Spearman(darkSide, namesList[rand.nextInt(namesSize)], x, y++));
                case 2 -> darkSide.add(new Crossbowman(darkSide, namesList[rand.nextInt(namesSize)], x, y++));
                default -> darkSide.add(new Monk(darkSide, namesList[rand.nextInt(namesSize)], x, y++));
            }
        }
    }

    public static void main(String[] args) {
        init();

        debugMessages = false; // выводить ли информацию о работе каждого шага игры?

        Scanner scanner = new Scanner(System.in);
        while (true){
            ConsoleView.view();
            whiteSide.forEach(n -> n.step(darkSide));
            callResetPeasants(whiteSide);
            darkSide.forEach(n -> n.step(whiteSide));
            callResetPeasants(darkSide);
            if (checkWin(darkSide)) {
                System.out.println("\uD83D\uDC9A\uD83E\u0D18\uD83D\u0C9AWhite side win!\u083D\uDC9A\uD83E\uDD18\uD83D\u0C9A");
                break;
            } else if (checkWin(whiteSide)) {
                System.out.println("\uD83D\uDC99\uD83E\u0018\uD83D\uDC99Dark side win!\uD83D\uDC99\uD83D\uDC99\uD83E\uDD18");
                break;
            }
            scanner.nextLine();
        }
    }

    public static boolean checkWin(ArrayList<Hero> teamList) {
        boolean win = true;
        for (Hero baseHero : teamList) {
            if (baseHero.getHealth() != 0) {
                win = false;
                break;
            }
        }
        return win;
    }

}

