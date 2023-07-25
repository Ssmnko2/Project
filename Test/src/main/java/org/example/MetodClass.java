package org.example;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service

public class MetodClass {


    public int[] createArray() {
        int[] arrInt = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).
                toArray();
        return arrInt;
    }

    public int[] generateArray() {
        int[] arrInt = IntStream.
                generate(() -> (int) (Math.random() * 100)).
                limit(10).
                toArray();
        return arrInt;
    }

    public int[] createArrayByStartToEnd() {
        int[] arrInt = IntStream.rangeClosed(1, 10).toArray();
        return arrInt;
    }

    public int[] quicksort(int[] arrInt, int low, int high) {
        if (low < high) {
            int baseIndex = partition(arrInt, low, high);
            quicksort(arrInt, low, baseIndex - 1);
            quicksort(arrInt, baseIndex + 1, high);
        }
        return arrInt;
    }

    private int partition(int[] arrInt, int low, int high) {
        int base = arrInt[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arrInt[j] < base) {
                i++;
                swap(arrInt, i, j);
            }
        }
        swap(arrInt, i + 1, high);
        return i + 1;
    }

    private void swap(int[] arrInt, int i, int j) {
        int temp = arrInt[i];
        arrInt[i] = arrInt[j];
        arrInt[j] = temp;
    }

    public void sort(int[] arrInt) {
        int k = arrInt.length - 1;
        for (int i = 0; i < arrInt.length - 1; i++) {
            for (int j = 0; j < k; j++)
                if (arrInt[j] > arrInt[j + 1]) {
                    int temp = arrInt[j + 1];
                    arrInt[j + 1] = arrInt[j];
                    arrInt[j] = temp;
                }
            k--;
        }
    }

    public void getMaxMin(int[] arrInt){
        Supplier<Stream<Integer>> streamInt = () -> Arrays.stream(arrInt).boxed();
        int max = streamInt.get().reduce(Integer::max).get();
        int min = streamInt.get().reduce(Integer::min).get();

        System.out.println(" max = " + max);
        System.out.println(" min = " + min);


    }

    public void determParity(int[] arrInt){

        Arrays.stream(arrInt).
                boxed().
                filter(x -> x%2 > 0).
                forEach(x -> System.out.println(" x = " + x + " "));

        Arrays.stream(arrInt).
                boxed().
                filter( x -> (x & 1) == 1).
                forEach(x -> System.out.println(" x = " + x + " "));


    }

    public int [][] createTDM(){
        int [][] arrInt2 = new int[10][10];
        Supplier <Stream<Integer>> streamInt = () -> IntStream.rangeClosed(0, 9).boxed();
        streamInt.get().
                forEach(i -> streamInt.get().forEach(j -> arrInt2[i][j] = (int) (Math.random() * 10)));
        return arrInt2;
    }

    public void printTDM(int[][] arrInt2){

        Supplier <Stream<Integer>> streanInt = () -> IntStream.rangeClosed(0, arrInt2.length -1).boxed();

        streanInt.get().forEach(i -> { StringBuilder sb = new StringBuilder();
                                    sb.append("[ ");
                                    streanInt.get().forEach(j -> sb.append(arrInt2[i][j]).append(" "));
                                    sb.append("]");
                                    System.out.println(sb);});

    }
}
