package server;

import java.io.*;


public class Reader {

    BufferedInputStream bf = new BufferedInputStream(System.in);

    /**
     * @param path путь до файла с данными
     * @return содержимое файла
     * @throws IOException - ошибка, связанная с существованием, доступом и содержимом файла
     */
    public static String readFromFile(String path) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
        return new String(bufferedInputStream.readAllBytes(), "UTF-8");
    }


    public String readLine() throws IOException {
        String str = "";
        char point = 0;
        System.out.println(bf.read());
        System.out.println(bf.read());
        while ((int) point != 10) {
            if ((int) point == 10) {
                System.out.println("ТУТ ПЕРЕНОООООООООС");
            }
            point = (char) bf.read();
            str += point;
        }
        return str;
    }

    public String readElement() throws IOException {
        String str = "";
        char point = 0;
        while ((int) point != 123) { // 123 - код открывающейся скобки
            point = (char) bf.read();
        }
        str += point;
        int count = 1; // Счетчик открытых скобок
        while (count != 0) { //TODO Проблема вечного ввода (собсна как и везде)
            point = (char) bf.read();
            if ((int) point == 123) { //123 - код открывающейся скобки
                count += 1;
            } else if ((int) point == 125) { //125 - Код закрывающейся скобки
                count -= 1;
            }
            if (count < 0) {
                throw new IllegalArgumentException(); //TODO Элемент введен неверно
            }
            str += point;
        }
        return str;
    }

    public String readCommand() throws IOException {
        char point = 0;
        String str = "";
        while (!((97 <= (int) point && (int) point <= 149) || (String.valueOf(point) == "_"))) {
            str += point;
            point = (char) bf.read();
        }
        while ((97 <= (int) point && (int) point <= 149) || (String.valueOf(point) == "_")) {
            str += point;
            point = (char) bf.read();
        }

        return str;
    }

}
