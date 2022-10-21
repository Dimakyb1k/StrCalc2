import java.util.Scanner;


public class StrCalc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Calc(sc.nextLine()));
    }

    public static String Calc(String ty) {
        String[] operation = {"+", "-", "*", "/"};
        String s0 = "";
        String s1 = "";
        String s2 = "";
        for (String value : operation) {
            if (ty.contains(" " + value + " ")) {
                s0 = ty.substring(0, ty.indexOf(" " + value + " "));
                if (s0.charAt(0) != '\"') {
                    throw new RuntimeException("Строки должны передаваться в кавычках");
                }
                s0 = s0.replaceAll("\"", "");
                s1 = value;
                s2 = ty.substring(s0.length() + 5).replaceAll("\"", "");
            }
        }
        if (s0.length() > 10 || s2.length() > 10) {
            throw new RuntimeException("Строка не должна состоять длиннее 10 символов ");
        }
        String operationS = s1;
        return switch (operationS) {
            case "+" -> Plus(s0, s2);
            case "-" -> Minus(s0, s2);
            case "*" -> Umn(s0, s2);
            case "/" -> Del(s0, s2);
            default -> throw new RuntimeException("Не верно выбрано выражение");
        };
    }

    public static String Minus(String s0, String s2) {
        return StrResult(s0.replace(s2, ""));
    }

    public static String Plus(String s0, String s2) {
        return StrResult(s0 + s2);
    }

    public static String Umn(String s0, String s2) {
        return StrResult(s0.repeat(checkInt(s2)));
    }

    public static String Del(String s0, String s2) {
        int x = s0.length() / checkInt(s2);
        return StrResult(s0.substring(0, x));
    }

    public static Integer checkInt(String s) {
        int i = Integer.parseInt(s);
        if (i <= 10 && i > 0) {
            return i;
        } else {
            throw new RuntimeException("Число должно быть не больше 10");
        }
    }

    public static String StrResult(String s) {
        return "\"" + ((s.length() <= 40) ? s : (s.substring(0, 40) + "...")) + "\"";
    }

}