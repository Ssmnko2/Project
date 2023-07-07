package main.booking.utils;

import main.booking.objects.SurnameName;

import java.util.Scanner;

public interface MyLibrary {
    public static SurnameName inputString() {
        SurnameName ns = new SurnameName();
        Scanner scan = new Scanner(System.in);
        for (; ; ) {
            String s = scan.nextLine();
            s = s.toUpperCase();
            if (s.chars().filter(ch -> ch == ' ').count() == 1) {
                int space = s.indexOf(" ");
                String surname = s.substring(0, space);
                String name = s.substring(space + 1, s.length());
                if (surname.matches("^[а-яА-Я]*$") && name.matches("^[а-яА-Я]*$") && space != 0 && space != s.length() - 1) {

                    ns.setName(name);
                    ns.setSurname(surname);
                    ns.setSurnameName(surname + " " + name);
                    break;
                } else
                    System.out.print("Ошибка ввода, повторите еще раз! : ");
            } else
                System.out.print("Ошибка ввода, повторите еще раз! : ");
        }

        return ns;
    }

    public static int YesNo() {
        Scanner scan = new Scanner(System.in);
        for (; ; ) {
            String s = scan.next();

            if (s.equalsIgnoreCase("ДА") || s.equalsIgnoreCase("Д"))
                return 1;
            else if (s.equalsIgnoreCase("НЕТ") || s.equalsIgnoreCase("Н"))
                return 0;
            else
                System.out.print("Ошибка ввода, повторите еще раз! : ");
        }
    }

    public static String inputNumberBooking() {

        Scanner scan = new Scanner(System.in);
        String s = null;

        for (; ; ) {
            s = scan.nextLine();
            if (s.chars().filter(ch -> ch == ' ').count() == 0)
                if (s.matches("^[0-9]*$"))
                    break;
                else
                    System.out.print("Ошибка ввода, повторите еще раз! : ");
            else
                System.out.print("Ошибка ввода, повторите еще раз! : ");
        }
        return s;
    }

}
