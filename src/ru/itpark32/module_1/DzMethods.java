package ru.itpark32.module_1;

public class DzMethods {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        int n = 4;
        System.out.println(isEven(n));
        System.out.println("Задание 2");
        int a = 1;
        int b = 2;
        int c = 3;
        System.out.println(max3(a, b, c));
        System.out.println("Задание 3");
        a = -1;
        System.out.println(abs(a));
        double numb = -0.0;
        System.out.println(abs(numb));
        System.out.println("Задание 4");
        b = 0;
        try {
            System.out.println(safeDivide(c, b));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Задание 5");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(sum(arr));
        System.out.println("Задание 6");
        System.out.println(countPositive(arr));
        System.out.println("Задание 7");
        System.out.println(sum(1, 11000, 2, 4));
        System.out.println("Задание 8");
        n = 121;
        System.out.println(isNumericPalindrome(n));
        System.out.println("Задание 9");
        System.out.println(isPrime(n));
        System.out.println("Методы на строки:");
        System.out.println(reverse("Методы на строки:"));
        System.out.println(countWords("Методы на строки:"));
        System.out.println(squeezeSpaces("Методы на строки   :"));
    }

    // вернуть true, если n чётное.

    public static boolean isEven(int n) {
        return (n % 2) == 0;
    }

    // вернуть наибольшее из трёх.

    public static int max3(int a, int b, int c) {
        int firstMax = Math.max(a, b);
        return Math.max(c, firstMax);
    }

    // вернуть модуль.

    public static int abs(int x) {
        return (x < 0.0) ? -x : x;
    }

    public static double abs(double x) {
        return (x < 0.0 || x == 0) ? -x : x;
    }

    // бросить IllegalArgumentException, если b == 0. Иначе вернуть a / b.

    public static int safeDivide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("На ноль делить нельзя");
        }
        return a / b;
    }

    /**
     * сумма элементов, null → 0.
           (или бросить исключение — выбери и задокументируй)
     *
     * @param arr массив чисел; если null -> бросает исключение IllegalArgumentException
     * @return сумма элементов массива; если массив пустой ->0
     */

    public static int sum(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Массив чисел не передан: \"null\"");
        }
        if (arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int numb : arr) {
            sum += numb;
        }
        return sum;
    }

    // сумма произвольного количества аргументов.

    public static long sum(long... nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Массив чисел не передан: \"null\"");
        }
        long sum = 0;
        for (long numb : nums) {
            sum += numb;
        }
        return sum;
    }

    /**
     * Подсчёт положительных.
     *
     * @param arr массив чисел; если null -> бросает исключение IllegalArgumentException
     * @return количество элементов > 0
     */

    public static int countPositive(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Массив чисел не передан: \"null\"");
        }
        int countPositive = 0;
        for (int numb : arr) {
            if (numb > 0) {
                countPositive++;
            }
        }
        return countPositive;
    }

    /**
     * Палиндром для числа.
     * проверка на однозначные числа
     * записываем исходное число в переменную input
     * в цикле берем последнюю цифру числа n и ставим в начало reverse
     *
     * @param n число для определения палиндрома
     * @return равенство исходного числа и перевернутого
     */

    public static boolean isNumericPalindrome(int n) {
        if (n < 10) {
            return false;
        }
        int input = n;
        int reverse = 0;
        while (n != 0) {
            reverse = reverse * 10 + (n % 10);
            n /= 10;
        }
        return (input == reverse);
    }

    // наибольший общий делитель, корректно для отрицательных.
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return (a < 0) ? -a : a;
    }

    // проверить простоту.

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // вернуть перевёрнутую строку.

    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // слова разделены одним или несколькими пробелами/табами/переводами строк.

    public static int countWords(String s) {
        if (s == null || s.isBlank()) {
            return 0;
        }
        int count = 0;
        String[] wordsSplit = s.trim().split("\\s+");
        return wordsSplit.length;
    }

    // заменить последовательности пробелов на один пробел.

    public static String squeezeSpaces(String s) {
        return s.replaceAll("\\s+", " ").trim();
    }
}