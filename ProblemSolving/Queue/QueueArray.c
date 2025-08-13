// Copyright 2025 Apurav Gautam
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <stdbool.h>

int* queue;
int max_size = 1;
int len = 0;
int front = 0;
int rear = 0;

void resize_array(int new_size) {
    int* new_queue = (int*)malloc(new_size * sizeof(int));
    for (int i = 0; i < len; i++) {
        new_queue[i] = queue[(front + i) % max_size];
    }
    free(queue);
    queue = new_queue;
    front = 0;
    rear = len;
    max_size = new_size;
}

void enqueue(int e) {
    if (len == max_size) {
        resize_array(max_size * 2);
    }
    queue[rear] = e;
    rear = (rear + 1) % max_size;
    len++;
}

int dequeue() {
    if (len == 0) return -1;
    int item = queue[front];
    front = (front + 1) % max_size;
    len--;
    if (len <= max_size / 4 && max_size > 1) {
        resize_array(max_size / 2);
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
    enqueue(5);
    enqueue(8);
    assert(max_size == 2);
    dequeue();
    enqueue(1);
    enqueue(12);
    enqueue(34);
    assert(max_size == 4);
    assert(size() == 4);
    assert(empty() == false);
    dequeue();
    dequeue();
    dequeue();
    dequeue();
    assert(empty() == true);
}

int main() {
    queue = (int*)malloc(max_size * sizeof(int));
    testCases();
    printf("All queue tests passed!\n");
    return 0;
}

