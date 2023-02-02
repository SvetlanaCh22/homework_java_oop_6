import java.util.Collections;

public class ConsoleView {

    private static int step = 1;
    //----------------отрисовка строчек псевдографики таблицы ---------начало ----------------
    private static final String top10 = formatDiv("a") + String.join("",
            Collections.nCopies(Main.GANG_SIZE - 1, formatDiv("-b"))) + formatDiv("-c");
    private static final String mid10 = formatDiv("d") + String.join("",
            Collections.nCopies(Main.GANG_SIZE - 1, formatDiv("-e"))) + formatDiv("-f");
    private static final String bott10 = formatDiv("g") + String.join("",
            Collections.nCopies(Main.GANG_SIZE - 1, formatDiv("-h"))) + formatDiv("-i");

    //----------------отрисовка строчек псевдографики таблицы --------конец-----------------
    public static void view() {

        if (ConsoleView.step == 1) {
            System.out.println(AnsiColors.ANSI_GREEN + "First step" + AnsiColors.ANSI_RESET);
        } else {
            System.out.println("Step " + step + ".");
        }
        step++;

        // ! System.out.println(ConsoleView.top10);
        for (int i = 1; i <= Main.GANG_SIZE; i++) {
            for (int j = 1; j <= Main.GANG_SIZE; j++) {
                System.out.print(getHeroChar(new Vector2(j, i)));
            }
            System.out.println(getHeroes(i-1));
            // ! System.out.println(ConsoleView.mid10);
        }
        System.out.println();

        // System.out.println("|");
        // System.out.println();
        // ! System.out.println(ConsoleView.bott10);
        System.out.println("Press Enter");
    }

    private static String formatDiv(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
    }

    private static String getHeroes(int index) {
        Hero currentHeroW = Main.whiteSide.get(index);
        Hero currentHeroD = Main.darkSide.get(index);
        String currentHeroWInfo = String.format("%-35s", currentHeroW.getInfo());
        String currentHeroDInfo = String.format("%-35s", currentHeroD.getInfo());
        String str = "";
        if (currentHeroW.getHealth() > 0) {
            // если герой жив
            str += " ".repeat(3) + AnsiColors.ANSI_GREEN + currentHeroWInfo + AnsiColors.ANSI_RESET;
        } else {
            // если герой погиб
            str += " ".repeat(3) + AnsiColors.ANSI_RED + currentHeroWInfo + AnsiColors.ANSI_RESET;
        }
        if (currentHeroD.getHealth() > 0) {
            // если герой жив
            str += " ".repeat(5) + AnsiColors.ANSI_BLUE + currentHeroDInfo + AnsiColors.ANSI_RESET;
        } else {
            // если герой погиб
            str += " ".repeat(5) + AnsiColors.ANSI_RED + currentHeroDInfo + AnsiColors.ANSI_RESET;
        }
        return str;
    }

    private static String getHeroChar(Vector2 position){
        String str = "| "; // клетки поля
        for (int i = 0; i < Main.GANG_SIZE; i++) {
            Hero currentHeroW = Main.whiteSide.get(i);
            Hero currentHeroD = Main.darkSide.get(i);
            if (currentHeroD.getPosition().isEquals(position)) {
                if (currentHeroD.getHealth()>0) {
                    str = "|" + AnsiColors.ANSI_BLUE + currentHeroD.getRole().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET + "| ";
                } else {
                    str = "|" + AnsiColors.ANSI_RED + currentHeroD.getRole().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET + "| ";
                }
            }
            if (currentHeroW.getPosition().isEquals(position)){
                if (currentHeroW.getHealth()>0) {
                    // если герой жив
                    str = "|" + AnsiColors.ANSI_GREEN + currentHeroW.getRole().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
                } else {
                    // если герой погиб
                    str = "|" + AnsiColors.ANSI_RED + currentHeroW.getRole().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
                }
            }
        }
        return str;
    }

}
