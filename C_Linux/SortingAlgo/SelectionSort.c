// Copyright Apurav 2025
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>
#include <stdint.h>
void printArray(int* , int);
void SelectionSort(int*, int);
void exch(int*, int, int);
/**
 * main method creates an array of random elements of size n.
 * Prints sorted values of array and Time taken.
*/
int main() {
  printf("Enter size of array: ");
  int arr_size;
  scanf("%d", &arr_size);
  int* array = (int*)malloc(arr_size*sizeof(int));

  // srand(time(NULL)); This method is not thread safe
  // Since common resource is shared globally
  unsigned int seed = (unsigned int)time(NULL);
  for (int i = 0; i < arr_size; i++) {
    array[i] = rand_r(&seed);
  }

  printf("Array before Sorting: \n\t");
  printArray(array, arr_size);
  printf("\n");

  // Time before sorting
  struct timeval before;
  gettimeofday(&before, NULL);
  int64_t time_before_in_ms = before.tv_sec * 1000LL + before.tv_usec / 1000;
  int64_t time_before_in_us = before.tv_sec * 1000000LL + before.tv_usec;
  SelectionSort(array, arr_size);

  // Time after sorting
  struct timeval after;
  gettimeofday(&after, NULL);
  int64_t time_after_in_ms = after.tv_sec * 1000LL + after.tv_usec / 1000;
  int64_t time_after_in_us = after.tv_sec * 1000000LL + after.tv_usec;
  printf("Array after Sorting: \n\t");
  printArray(array, arr_size);
  printf("\n");
  int64_t time_taken_in_ms = time_after_in_ms - time_before_in_ms;
  int64_t time_taken_in_us = time_after_in_us - time_before_in_us;
  printf("Time Taken:  ");
  printf("%ld ms", time_taken_in_ms);
  printf(" or %ld us\n", time_taken_in_us);
}

/**
 * Print array elements
 * @param n - size of array
 * @param arr - array of size n
*/
void printArray(int* arr, int n) {
  for (int i = 0; i < n; i++) {
    printf("%d ", arr[i]);
  }
}

/**
 * Sorts the elements using Selection sort
 * @param n - size of array
 * @param arr - array of size n
*/
void SelectionSort(int* arr, int n) {
  for (int i = 0; i < n-1; i++) {
    int mini = i;
    for (int j = i+1; j < n; j++) {
      if (arr[j] < arr[mini]) {
        mini = j;
      }
    }
    exch(arr, i, mini);
  }
}

/**
 * @param i,j - element at position i is swapped with element at j.
 * @param arr - array at which two positions are to be swapped.
*/
void exch(int* arr, int i, int j) {
  int temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
}
