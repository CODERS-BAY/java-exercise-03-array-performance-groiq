# Sorting

Your task is to implement at least two sorting algorithms:

* Bubble Sort
* Quick Sort

If you have already implemented one of these, choose another arbitrary one.

Test your implementations with different sizes of arrays.
It is sufficient if you use int arrays.

## Generate Random Numbers
~~~java
// Values are smaller 100 (= bound).
int number new Random().nextInt(100);
~~~

## Measure Runtime
~~~java
long startTime = System.nanoTime();
/*
 * your algorithm
 */
long endTime = System.nanoTime();
System.out.println("time: " + (endTime - startTime));
~~~

**Use methods for the different algorithms**. You also write a method for generating the arrays.
