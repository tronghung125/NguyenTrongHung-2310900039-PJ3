package lambda_expresion;

@FunctionalInterface
interface SayHello1 {
    void sayHello();
}

public class LE1 {
    public static void main(String[] args) {
        SayHello1 sayHello = () -> {
            System.out.println("Hello World");
        };
        sayHello.sayHello();
    }
}
