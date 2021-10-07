import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sorting {
    public static void main(String[] args) {

        int[] myLengths = { 4, 8, 32, 128, 1024, 2048 };
        ArrayCollection myTests = new ArrayCollection(myLengths);
        int[][] data = myTests.fetch();

        BubbleSort[] bubbleSort = new BubbleSort[myLengths.length];
        for (int i = 0; i < bubbleSort.length; i++) {
            bubbleSort[i] = new BubbleSort(data[i]);
            System.out.println(Arrays.toString(bubbleSort[i].getSortedData()));
            System.out.println(bubbleSort[i].getStats());
        }

        SimpleQuickSort[] simpleQuickSorts = new SimpleQuickSort[myLengths.length];
        for (int i = 0; i < simpleQuickSorts.length; i++) {
            simpleQuickSorts[i] = new SimpleQuickSort(data[i]);
            System.out.println(Arrays.toString(simpleQuickSorts[i].getSortedData()));
            System.out.println(simpleQuickSorts[i].getStats());
        }





        
    }
}

class ArrayCollection {
    private int[][] data;
    private Random random;

    ArrayCollection(int[] lengths) {
        random = new Random();

        this.data = new int[lengths.length][];
        for (int i = 0; i < lengths.length; i++) {
            int currLength = lengths[i];
            this.data[i] = new int[currLength];
            for (int j = 0; j < currLength; j++) {
                this.data[i][j] = random.nextInt(100);
            }
        }
    }

    public int[][] fetch() {
        int[][] result = new int[data.length][];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i].clone();
        }
        return result;
    }
}

abstract class Sorted {
    final int[] rawData;
    final int[] sortedData;
    final long sortTime;

    abstract void sort();

    public Sorted(int[] rawData) {
        this.rawData = rawData;
        this.sortedData = rawData.clone();
        long startTime = System.nanoTime();
        sort();
        long endTime = System.nanoTime();
        this.sortTime = endTime - startTime;
    }

    public int[] getRawData() {
        return rawData;
    }

    public int[] getSortedData() {
        return sortedData;
    }

    public long getSortTime() {
        return sortTime;
    }

    public int getLength() {
        return rawData.length;
    }

    void swap(int pos1, int pos2) {
        int temp = sortedData[pos1];
        sortedData[pos1] = sortedData[pos2];
        sortedData[pos2] = temp;
    }

    public abstract String getAlgorithm();

    public String getStats() {
        return new StringBuilder()
                .append("array of ")
                .append(getLength())
                .append(" elements sorted in ")
                .append(sortTime)
                .append(" nanoseconds using ")
                .append(getAlgorithm())
                .toString();
    }
}

class BubbleSort extends Sorted {

    public BubbleSort(int[] rawData) {
        super(rawData);
    }

    @Override
    void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 1; i < sortedData.length; i++) {
                if (sortedData[i-1] > sortedData[i]) {
                    sorted = false;
                    swap(i-1, i);
                }
            }
        }
    }

    @Override
    public String getAlgorithm() {
        return "bubble sort";
    }
}

class SimpleQuickSort extends Sorted {

    public SimpleQuickSort(int[] rawData) {
        super(rawData);
    }

    @Override
    void sort() {
        List<Integer> toSort = new ArrayList<>();
        for (int i :
                rawData) {
            toSort.add(i);
        }
        List<Integer> sorted = quickSort(toSort);
        for (int i = 0; i < sortedData.length; i++) {
            sortedData[i] = sorted.get(i);
        }

    }

    private List<Integer> quickSort(List<Integer> toSort) {

        if (toSort.size() <= 1) {
            return toSort;
        }

        int pivot = toSort.get(0);
        List<Integer> smaller = new ArrayList<>();
        List<Integer> bigger = new ArrayList<>();
        for (int i = 1; i < toSort.size(); i++) {
            int curr = toSort.get(i);
            if (curr < pivot) {
                smaller.add(curr);
            } else {
                bigger.add(curr);
            }
        }
        smaller = quickSort(smaller);
        bigger = quickSort(bigger);

        List<Integer> sorted = new ArrayList<>();
        for (Integer i :
                smaller) {
            sorted.add(i);
        }
        sorted.add(pivot);
        for (Integer i :
                bigger) {
            sorted.add(i);
        }
        return sorted;
    }


        @Override
    public String getAlgorithm() {
        return "simple divide and conquer";
    }
}


