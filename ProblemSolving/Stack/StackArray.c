// Copyright Apurav 2025
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <stdbool.h>
int* stack;
int max_size = 1;
int len = 0;
void resize_array(int new_size) {
  int* new_stack = (int*)malloc(new_size * sizeof(int));
  for (int i = 0; i < len; i++) {
    new_stack[i] = stack[i];
  }
  free(stack);
  stack = new_stack;
}

void push(int e) {
  stack[len++] = e;
  if (len == max_size) {
    max_size *= 2;
    resize_array(max_size);
  }
}
int pop() {
  if (len == 0)return -1;
  int item = stack[--len];
  if (len <= max_size/4 && max_size > 1) {
    max_size /= 2;
    resize_array(max_size);
  }
  return item;
}
int size() {
  return len;
}
bool empty() {
  return len == 0;
}
void testCases() {
  assert(max_size == 1);
  push(5);
  push(8);
  assert(max_size == 4);
  pop();
  push(1);
  push(12);
  push(34);
  assert(max_size == 8);
  assert(size() == 4);
  assert(empty() == false);
  pop();
  pop();
  pop();
  pop();
  assert(empty() == true);
  printf("All test cases pass\n");
}
int main() {
  stack = (int*)malloc(max_size * sizeof(int));
  testCases();
}
