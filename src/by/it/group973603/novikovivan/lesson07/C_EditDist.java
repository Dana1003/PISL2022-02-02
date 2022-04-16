package by.it.group973603.novikovivan.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int matr[][] = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i < one.length() + 1; i++)
            matr[i][0] = i;
        for (int j = 0; j < two.length() + 1; j++)
            matr[0][j] = j;
        for (int i = 1; i < one.length() + 1; i++) {
            for (int j = 1; j < two.length() + 1; j++) {
                matr[i][j] = Math.min(matr[i - 1][j] + 1, Math.min(
                        matr[i][j - 1] + 1,
                        matr[i - 1][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1)));
            }
        }
        StringBuilder result = new StringBuilder();
        int i = one.length();
        int j = two.length();

        while (i != 0 || j != 0) {
            if (i == 0) {
                result.append("+").append(two.charAt(j - 1)).append(",");
                j--;
                continue;
            }
            if (j == 0) {
                result.append("-").append(one.charAt(i - 1)).append(",");
                i--;
                continue;
            }
            if (matr[i - 1][j - 1] <= matr[i - 1][j] && matr[i - 1][j - 1] <= matr[i][j - 1]) {
                if (matr[i - 1][j - 1] == matr[i][j]) {
                    result.append("#").append(",");
                    i--;
                    j--;
                    continue;
                }
                result.append("~").append(two.charAt(j - 1)).append(",");
                i--;
                j--;
                continue;
            }
            if (matr[i - 1][j] <= matr[i][j - 1]) {
                result.append("-").append(one.charAt(i - 1)).append(",");
                i--;
            }
            else {
                result.append("+").append(two.charAt(i - 1)).append(",");
                j--;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}