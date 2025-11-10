package lambda_expresion;

import java.util.Arrays;
import java.util.List;

public class LE4 {
    public static void main(String[] args){
        List<String> list=Arrays.asList("Java SpringBoot","C# NetCore","PHP","JavaScrip");
        //Using lambda exp
        list.forEach(item->System.out.println(item));
        System.out.println("=========================");
        list.forEach(System.out::println);
    }
}
