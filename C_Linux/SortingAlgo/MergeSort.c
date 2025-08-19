// Copyright Apurav 2025
#include <stdio.h>

void MergeSort(int*, int, int, int*);
void merge(int*, int, int, int, int*);

int main() {
  int n;
  printf("Enter size of the array: ");
  scanf("%d",&n);
  int arr = (int*)malloc(sizeof(int) * n);
  unsigned int seed = (unsigned int)time(NULL);
  
  int aux = (int*)malloc(sizeof(int) * n);
  for (int i = 0; i < n; i++) {
    arr[i] = rand_r(seed);
  }


  MergeSort(arr, 0, n-1, aux);
  for (int i = 0; i < n; i++ ) {
    printf("%d ",arr[i]);
  }
}

void MergeSort(int arr[], int L, int R, int aux[]) {
  if (L >= R) return;
  int mid = (L + R) / 2;
  MergeSort(arr, L, mid, aux);
  MergeSort(arr, mid+1, R, aux);
  merge( arr, L, mid, R, aux);
}

void merge(int arr[], int L, int mid, int R, int aux[]) {
  int i = L;
  int j = mid + 1;
  int k = L;
  for (;k <= R; k++) {
    if (i > mid) aux[k] = arr[j++];
    else if (j > R) aux[k] = arr[i++];
    else if (arr[i] <= arr[j]) aux[k] = arr[i++];
    else aux[k] = arr[j++];
  }

  for (k = L; k <= R; k++) {
    arr[k] = aux[k];
  }
}
