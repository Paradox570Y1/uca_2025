// Copyright Apurav 2025
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>
#include <stdint.h>

// Function defination
void InsertionSort(int*, int);
void exch(int*, int, int);

/**
 * Method to create array with random elements of size n.
 * Prints Sorted Elements along with Time Taken to sort.
*/
int main() {
  int arr_size;
  printf("Enter the size of array: ");
  scanf("%d", &arr_size);
  int* arr = (int*)malloc(sizeof(int) * arr_size);
  unsigned int seed = (unsigned int)time(NULL);
  printf("Before sorting:\n\t");
  for (int i = 0; i < arr_size; i++) {
    arr[i] = rand_r(&seed);
    printf("%d ", arr[i]);
  }
  printf("\n");

  struct timeval before;
  gettimeofday(&before, NULL);
  int64_t before_sort_in_ms = before.tv_sec * 1000LL + before.tv_usec / 1000LL;
  int64_t before_sort_in_us = before.tv_sec * 1000000LL + before.tv_usec;

  InsertionSort(arr, arr_size);

  struct timeval after;
  gettimeofday(&after, NULL);
  int64_t after_sort_in_ms = after.tv_sec * 1000LL + after.tv_usec / 1000LL;
  int64_t after_sort_in_us = after.tv_sec * 1000000LL + after.tv_usec;

  printf("After sorting: \n\t");
  for (int i = 0; i < arr_size; i++) {
    printf("%d ", arr[i]);
  }
  printf("\n");
  int64_t time_span_in_ms = after_sort_in_ms - before_sort_in_ms;
  int64_t time_span_in_us = after_sort_in_us - before_sort_in_us;
  printf("Time Taken in %ld ms or %ld us\n", time_span_in_ms, time_span_in_us);
}

/**
 * @param arr - array of random elements
 * @param n - size of array
*/ 
void InsertionSort(int* arr, int n) {
  for (int i = 1; i < n; i++) {
    int j = i;
    while (j > 0 && arr[j] < arr[j-1]) {
      exch(arr, j, j-1);
      j--;
    }
  }
}

/**
 * @param i,j - swap element at index i with element at index j.
 * @param arr - array of random elements
*/
void exch(int *arr, int i, int j) {
  int temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
}

