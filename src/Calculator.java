import java.util.Locale;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите математическую функцию = ");
        String input = sc.nextLine();
        input = input.toUpperCase(Locale.ROOT);
        String result = calc(input);
        System.out.println(result);
    }
    public static String calc(String input) throws Exception {
        String[] input1 = input.split(" ");
        if (input1.length > 3){
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (input1.length < 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String[] romes_text = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int[] romes_numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boolean bool_romes1_1 = false;
        boolean bool_romes1_2 = false;
        boolean bool_numbers1_1 = false;
        boolean bool_numbers1_2 = false;
        int numb1 = 0;
        int numb2 = 0;
        char operation = input1[1].charAt(0);
        String lastResult = "";
        if (((operation == '+') || (operation == '-')) || ((operation == '*') || (operation == '/'))){}
        else {throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");}
        Result result = new Result();
        RomeResult romeResult = new RomeResult();
        for (int i = 0; i < romes_text.length; i++) {
            if ((input1[0].equals(romes_text[i])) || (input1[2].equals(romes_text[i]))) {
                bool_romes1_1 = bool_romes1_1 || input1[0].equals(romes_text[i]);
                bool_romes1_2 = bool_romes1_2 || input1[2].equals(romes_text[i]);
            }
            else if ((Character.isDigit(input1[0].charAt(0))) || (Character.isDigit(input1[2].charAt(0)))) {
                bool_numbers1_1 = bool_numbers1_1 || Character.isDigit(input1[0].charAt(0));
                bool_numbers1_2 = bool_numbers1_2 || Character.isDigit(input1[2].charAt(0));
            }
        }
        if (bool_romes1_1 && bool_romes1_2) {
            for (int i = 0; i < romes_text.length; i++){
                if (input1[0].equals(romes_text[i])){
                    numb1 = romes_numbers[i];
                } else if (input1[2].equals(romes_text[i])) {
                    numb2 = romes_numbers[i];
                }
            }
            if (result.resultOperation(numb1, numb2, operation) == 0){
                throw new Exception("В римской системе счистления нет нуля");
            } else if (result.resultOperation(numb1, numb2, operation) < 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            else {
                lastResult = lastResult + RomeResult.romeResult(result.resultOperation(numb1, numb2, operation));

            }


        }
        else if (bool_numbers1_1 && bool_numbers1_2) {
            try {
                numb1 = Integer.parseInt(input1[0]);
                numb2 = Integer.parseInt(input1[2]);
                if ((numb1 >= 1) && (numb2 >= 1) && (numb1 <= 10) && (numb2 <= 10)) {
                   lastResult = lastResult + result.resultOperation(numb1, numb2, operation);
                } else {
                    throw new Exception("Использовать только от 1 до 10 числа");
                }
            } catch (NumberFormatException e) {
                throw new Exception("Необходимо использовать только целые числа");
            }
        }
        else if ((bool_romes1_1 || bool_romes1_2) && (bool_numbers1_1 || bool_numbers1_2)) {
            throw new Exception("используются одновременно разные системы счисления");
        } else if (!bool_romes1_1 || !bool_romes1_2) {
            throw new Exception("необходимо использовать от I до X");
        }
        return lastResult;
    }
}
class Result{
    int result;
    int resultOperation (int num1, int num2, char operation){
            if (operation == '+'){
                result = num1 + num2;
            } else if (operation == '-') {
                result = num1 - num2;
            } else if (operation == '*') {
                result = num1 * num2;
            } else if (operation == '/') {
                result = num1 / num2;
            }return result;
    }
}
class RomeResult{

    static String romeResult(int result1) {
        String result = "";
        String[] romes = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] numes = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int i = 0;
        StringBuilder numstr = new StringBuilder();
        while (i < numes.length) {
            while (result1 >= numes[i]) {
                int temp = result1 / numes[i];
                result1 = result1 % numes[i];
                numstr.append(romes[i].repeat(temp));
            }
            i = i + 1;
        }
        result = result + numstr;
        return result;
    }

}