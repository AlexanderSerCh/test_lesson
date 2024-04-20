import java.util.Locale;
import java.util.Scanner;

public class Main_v2 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите любое целое число от 1 - 10 через пробел, операцию (+ сложение, - вычитание, * умножение, / деление) затем пробел и второе число затем Ввод\n= ");
        String input = sc.nextLine();
        String result = calc(input);
        System.out.println(result);
    }
    public static String calc(String input) throws Exception {
        input = input.toUpperCase(Locale.ROOT); // сделать все буквы большими
        String[] input1 = input.split(" "); // засунуть в массив через пробел
        char operator;
        int numb1;
        int numb2;
        int result = 0;
        boolean boo = false;
        boolean boo1 = false;
        String result_text = "";
        int result_rome = 0;
        int result1; // вообще он не нужен, но написал для того чтобы в результате показывало арабскими цифрами и римскими
        String[] rim = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; // какие цифры будут применены для расчета
        String[] romes = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; // из википедии
        int[] numes = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}; // из википедии
        int[] rim_numb = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // массив для приравнивания римских к цифре
        StringBuilder numstr = new StringBuilder(); // само добавилось когда пытался сделать конкатенацию строки эта строки сюда плюсуются новые строки
        int inumes = 0; // индекс для запуска цикла и перебора количество объектов в массиве

        if (input1.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }// выдает ошибку если меньше трех символов
        else if (input1.length < 3) {
            throw new Exception("строка не является математической операцией");
        }// выдает ошибку если больше трех символов
        operator = input1[1].charAt(0); // второй символ в массиве всегда оператор тут определяет какой
        if ((operator == '+') || (operator == '-') || (operator == '/') || (operator == '*')) {
        } else {
            throw new Exception("формат математической операции не удовлетворяет заданию один оператор (+, -, /, *)");
        }
        for (int j = 0; j < rim.length; j++) {
            for (int k = 0; k < rim.length; k++) {
                if ((Character.isDigit(input1[0].charAt(0))) && (Character.isDigit(input1[2].charAt(0)))) {
                    numb1 = Integer.parseInt(input1[0]);
                    numb2 = Integer.parseInt(input1[2]);

                    if (numb1 > 10 || numb2 > 10) {
                        throw new Exception("использовать только от 1 до 10");
                    } else if (numb1 < 1 || numb2 < 1) {
                        throw new Exception("использовать только от 1 до 10");
                    }
                    if (operator == '+') {
                        result = numb1 + numb2;
                    } else if (operator == '-') {
                        result = numb1 - numb2;
                    } else if (operator == '*') {
                        result = numb1 * numb2;
                    } else {
                        result = numb1 / numb2;
                    }
                } // если две цифры
                else if ((input1[0].equals(rim[j])) && (input1[2].equals(rim[k]))) {
                    numb1 = rim_numb[j];
                    numb2 = rim_numb[k];
                    if (operator == '+') {
                        result_rome = numb1 + numb2;
                        result1 = result_rome;

                    } else if (operator == '-') {
                        result_rome = numb1 - numb2;
                        result1 = result_rome;
                        if (result_rome < 0) {
                            throw new Exception("в римской системе нет отрицательных чисел");
                        } else if (result_rome < 1) {
                            throw new Exception("В римской системе нет нуля");
                        }
                    } else if (operator == '*') {
                        result_rome = numb1 * numb2;
                        result1 = result_rome;
                    } else {
                        result_rome = numb1 / numb2;
                        result1 = result_rome;
                    }
                    while (inumes < numes.length) {
                        while (result1 >= numes[inumes]) {
                            int temp = result1 / numes[inumes]; // после деление из массива находим целое без остатка число
                            result1 = result1 % numes[inumes]; // находим остаток для повторного запуска цикла
                            numstr.append(romes[inumes].repeat(temp)); // плюсуем по очереди каждый индекс и строкового массива
                        }
                        inumes = inumes + 1;
                    } // цикл для перебора и конвертации арабских цифр в римские строки numstr выводит этот результат
                    //System.out.println(numstr);
                    //result_text = result_text + numstr;

                } // если римские цифры

                else if ((Character.isDigit(input1[0].charAt(0))) || (Character.isDigit(input1[2].charAt(0)))) {
                    throw new Exception("используются одновременно разные системы счисления");
                }// исключение если 1 буква и цифра


            } // цикл для второго числа
            boo = boo || input1[0].equals(rim[j]) || (Character.isDigit(input1[0].charAt(0)));
            boo1 = boo1 || input1[2].equals(rim[j]) || (Character.isDigit(input1[2].charAt(0)));
        } // цикл для первого числа
        if ((!boo) || (!boo1)) {
            throw new Exception("использовать только от I до X");
        }
        if (result_rome != 0) {
            result_text = result_text + numstr;
        } else if (result == 0) {

        }
        else if (result != 0){
            result_text = result_text + result;
        }
        return result_text;
    }
}
