package ru.itpark32.module_1;

public class DzMethodsRecursion {
    public static void main(String[] args) {
        printNormalizeFullName(" иВАН иВАНОвИч ПЕтРоВ ");
        printNormalizeFullName(" сИДОров сеРгЕЙ ");
        printFormatInput("__ Привет!!! мир --- ");
        printFormatInput(" --Hello__World-- ");
        System.out.println(parsePrice("Цена: 1 234,50 RUB"));
        System.out.println(parsePrice("Price: $1,234.50"));
        String methodName = "createUser";
        String paramNames = " name , age, isActive ";
        System.out.println(buildSignature(methodName, splitNames(paramNames)));
        printRepeatCompression("aaabbcaaaa");
        printRepeatCompression("abcd");
        System.out.println(sumDigits(12345));
        System.out.println(sumDigits(9));
        System.out.println(reverse("abba"));
        System.out.println(reverse("Java"));
        System.out.println(ectsGrade(95));
        System.out.println(ectsGrade(73));
        System.out.println(ectsGrade(-1));
        System.out.println(daysInMonth(2, false));
        System.out.println(daysInMonth(2, true));
        System.out.println(daysInMonth(11, false));
    }

    // Задача 1. «Нормализатор ФИО»

    public static void printNormalizeFullName(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Пустое значение");
            return;
        }
        String[] splitName = splitName(name);
        normalizeWordCase(splitName);
        System.out.println(normalizeNameOrder(splitName));
    }

    private static String[] splitName(String name) {
        return name.trim().split("\\s+");
    }

    // Делает первую букву заглавной

    private static void normalizeWordCase(String[] splitName) {
        int length = splitName.length;
        for (int i = 0; i < length; i++) {
            splitName[i] = Character.toUpperCase(splitName[i].charAt(0)) +
                           (splitName[i].substring(1).toLowerCase());
        }
    }

    // Приводит к порядку: ФИО

    private static String normalizeNameOrder(String[] splitName) {
        String firstName = "";
        String lastName = "";
        String patronymic = "";
        for (String namePart : splitName) {
            if (namePart.matches(".*(ич|на)$")) {
                patronymic = namePart;
            } else if (namePart.matches(".*(ов|ва|ев|ева|ин|ина)$")) {
                lastName = namePart;
            } else {
                firstName = namePart;
            }
        }
        return String.format("%s %s %s", lastName, firstName, patronymic)
                .replaceAll("\\s+", " ").trim();
    }

    // Задача 2. «Санитар строки»

    public static void printFormatInput(String line) {
        if (line == null || line.isBlank()) {
            System.out.println("Пустое значение");
            return;
        }
        String newLine = line.replaceAll("[^\\p{L}\\p{N} _-]", "")
                .replaceAll("\\s+", " ");
        while (newLine.startsWith(" ") || newLine.startsWith("-") || newLine.startsWith("_")) {
            newLine = newLine.substring(1);
        }
        int length = newLine.length();
        while (newLine.endsWith(" ") || newLine.endsWith("-") || newLine.endsWith("_")) {
            newLine = newLine.substring(0, length - 1);
            length--;
        }
        System.out.println(newLine);
    }

    // Задача 3. «Парсер цены»

    public static double parsePrice(String input) {
        int firstIndex = -1;
        int lastIndex = -1;
        int length = input.length();
        for (int i = 0; i < length; i++) {
            if (Character.isDigit(input.charAt(i))) {
                firstIndex = i;
                break;
            }
        }
        for (int i = length - 1; i >= firstIndex; i--) {
            if (Character.isDigit(input.charAt(i))) {
                lastIndex = i;
                break;
            }
        }
        String price = input.substring(firstIndex, lastIndex + 1);
        price = price.replaceAll("\\s+", "").replace(",", ".");
        while (price.indexOf(".") != price.lastIndexOf(".")) {
            price = price.replaceFirst("\\.", "");
        }
        return Double.parseDouble(price);
    }

    // Задача 4. «Сигнатура метода»

    public static String buildSignature(String methodName, String[] paramNames) {
        if (methodName == null || methodName.isBlank() ||
                paramNames == null || paramNames.length == 0) {
            throw new IllegalArgumentException("Уточните данные");
        }
        return "public void " + methodName + "(" +
               formatNamesWithType(paramNames) +
               ")";
    }

    public static String[] splitNames(String paramName) {
        String[] paramNames = paramName.split(",");
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = paramNames[i].trim();
        }
        return paramNames;
    }

    private static String formatNamesWithType(String[] paramNames) {
        int length = paramNames.length;
        String[] namesWithTypes = new String[length];
        int count = 0;
        for (String paramName : paramNames) {
            if (!paramName.isBlank()) {
                String type = determineTypeByName(paramName);
                namesWithTypes[count++] = type + " " + paramName;
            }
        }
        return String.join(", ", namesWithTypes);
    }

    private static String determineTypeByName(String name) {
        if (name.matches("^is[A-Z].*")) return "boolean";
        if (name.matches(".*age.*")) return "int";
        return "String";
    }

    // Задача 5. «Сжатие повторов (RLE light)»

    public static void printRepeatCompression(String line) {
        if (line == null || line.isBlank()) {
            System.out.println("Пустое значение");
            return;
        }
        int i = 0;
        while (i < line.length()) {
            char c = line.charAt(i);
            int count = 0;
            while (i + 1 < line.length() && line.charAt(i + 1) == c) {
                count++;
                i++;
            }
            System.out.print(c);
            if (count > 0) System.out.print(count + 1);
            i++;
        }
        System.out.println();
    }

    // «Сумма цифр числа»

    public static int sumDigits(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Неверное значение, число должно быть > 0");
        }
        if (n < 10) {
            return n;
        }
        return sumDigits(n / 10) + sumDigits(n % 10);
    }

    // «Разворот строки»

    public static String reverse(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() <= 1) {
            return s;
        }
        return reverse(s.substring(1)) + s.charAt(0);
    }

    // «Оценка по шкале ECTS»

    public static String ectsGrade(int score) {
        if (score < 0 || score > 100) {
            return "Invalid";
        }
        int bucket = score / 10;
        switch (bucket) {
            case 10:
            case 9:
                return "A";
            case 8:
                return "B";
            case 7:
                return "C";
            case 6:
                return "D";
            case 5:
                return "E";
            default:
                return "F";
        }
    }

    public static int daysInMonth(int month, boolean leap) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> {
                if (leap) {
                    yield 29;
                }
                yield 28;
            }
            default -> -1;
        };
    }
}