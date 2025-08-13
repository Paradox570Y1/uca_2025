/**
 * Copyright Apurav 2025
 * Stack implementation with LinkedList
*/

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <assert.h>

// Represents a node of LinkedList
struct Node {
  int item;
  struct Node* next;
};

struct Node* head = NULL;
int N = 0;

/**
 * Is this stack empty?
 * @return true if stack is empty; false otherwise
*/
bool isEmpty() {
  return N == 0;
}

/**
 * Count of items in stack.
 * @return number of items in stack.
*/ 
int size() {
  return N;
}

/**
 * Add an element to stack.
 * @param - item to be pushed into stack.
*/ 
void push(int item) {
  struct Node* oldHead = head;
  head = (struct Node*)malloc(sizeof(struct Node*));
  head->item = item;
  head->next = oldHead;
  N++;
}

/**
 * Removes most recently pushed element from stack.
 * @return top most element from stack; -1 otherwise
*/
int pop() {
  if (head == NULL) return -1;
  int item = head->item;
  head = head->next;
  N--;
  return item;
}

// Tests stack data structure
void Testcases() {
  assert(isEmpty());
  push(5);
  push(8);
  assert(size() == 2);
  assert(pop() == 8);
  push(12);
  push(3);
  push(6);
  assert(size() == 4);
  pop();
  pop();
  pop();
  pop();
  assert(isEmpty());
  printf("All test cases passed");
}
int main() {
  Testcases();
}
