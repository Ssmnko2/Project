package org.example;



import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class Autorun {

    private final MetodClass metodClass;

    @Bean
    public void test(){
        System.out.println("Hello, world!!!!!");

        System.out.println(Arrays.toString(metodClass.createArray()));
        int [] arrInt = metodClass.generateArray();
        System.out.println(Arrays.toString(arrInt));
        System.out.println(Arrays.toString(metodClass.createArrayByStartToEnd()));

        metodClass.sort(arrInt);


        System.out.println(Arrays.toString(arrInt));
        metodClass.getMaxMin(arrInt);

        int[][] int2Arr = metodClass.createTDM();
        metodClass.printTDM(int2Arr);
    }
}
