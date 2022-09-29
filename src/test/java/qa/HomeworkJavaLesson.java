package qa;

public class HomeworkJavaLesson {

    public static void main(String[] args) {
        int value = Integer.MAX_VALUE-1;
        for(int i = 0; i < 4; i++, value++) {
            System.out.println(value);
        }

        //String stringThatShouldBeDisplayedInConsole = "Hello, World!";

        //boolean flag = true;

        //byte aByte; // 8 бит
        //short aShort; // 16 бит
        //int aInt; // 32 бит
        //long aLong; //64 бит

        // float aFloat;
        // double aDouble = 1.5;
        // char aChar = '0'


        // Операторы математические + - * / %
        // Операторы условные > < >= <= == != !
        // Операторы логические && - русское слово "И" || - русское слово "ИЛИ"

        int a = 15;
        int b = 10;

        double x = 1.5;
        double y = 2.5;


        System.out.println(a * y);
        System.out.println(b + x);
        System.out.println(b / y);
        System.out.println(b - a);
    }
}
