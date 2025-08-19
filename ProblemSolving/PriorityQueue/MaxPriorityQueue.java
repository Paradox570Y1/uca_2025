/**
 * Priority Queue with max-heap implementation.
 */
public class MaxPriorityQueue {
  private int[] priorityQueue;
  private int size;

  /**
   * Initializes priority queue with given capacity.
   * Initializes priority queue size to 0.
   *
   * @param capacity capacity of the priority queue
   */
  public MaxPriorityQueue(int capacity) {
    this.priorityQueue = new int[capacity + 1];
    this.size = 0;
  }

  /**
   * Checks if the priority queue is empty.
   *
   * @return true if priority queue is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Gets the number of elements in the priority queue.
   *
   * @return number of elements in the priority queue
   */
  public int size() {
    return this.size;
  }

  /** Swaps elements at the given indices. */
  private void exch(int i, int j) {
    int temp = this.priorityQueue[i];
    this.priorityQueue[i] = this.priorityQueue[j];
    this.priorityQueue[j] = temp;
  }

  /** Compares two elements at given indices. */
  private boolean less(int i, int j) {
    return this.priorityQueue[i] < this.priorityQueue[j];
  }

  /**
   * Inserts an element in the priority queue.
   *
   * @param element element to be inserted in the priority queue
   */
  public void insert(int element) {
    this.priorityQueue[++size] = element;
    swim(size);
  }

  /**
   * Moves the element up based on heap constraints.
   *
   * @param k index of the element to swim
   */
  private void swim(int k) {
    while (k > 1 && less(k / 2, k)) {
      exch(k, k / 2);
      k = k / 2;
    }
  }

  /**
   * Gets the maximum element in the priority queue.
   *
   * @return the maximum element in the priority queue,
   *     or -1 if the queue is empty
   */
  public int getMax() {
    if (this.isEmpty()) {
      return -1;
    }
    return this.priorityQueue[1];
  }

  /**
   * Removes and returns the maximum element.
   *
   * @return the maximum element in the priority queue,
   *     or -1 if the queue is empty
   */
  public int delMax() {
    if (this.isEmpty()) {
      return -1;
    }
    final int element = this.priorityQueue[1];
    exch(1, size--);
    sink(1);
    priorityQueue[size + 1] = 0;
    return element;
  }

  /** Moves the element down based on heap constraints. */
  private void sink(int k) {
    while (2 * k <= size) {
      int j = 2 * k;
      if (j < size && less(j, j + 1)) {
        j++;
      }
      if (less(j, k)) {
        return;
      }
      exch(j, k);
      k = j;
    }
  }

  /**
   * Runs basic test cases to validate the MaxPriorityQueue.
   *
   * @param priorityQueue queue instance to test
   */
  private static void testCases(MaxPriorityQueue priorityQueue) {
    priorityQueue.insert(5);
    priorityQueue.insert(7);
    priorityQueue.insert(2);
    priorityQueue.insert(9);

    assert (priorityQueue.size() == 4);
    assert (priorityQueue.getMax() == 9);

    priorityQueue.delMax();
    priorityQueue.delMax();

    assert (priorityQueue.size() == 2);
    assert (priorityQueue.getMax() == 5);
    assert (!priorityQueue.isEmpty());

    System.out.println("All test cases passed");
  }

  /**
   * Entry point for testing MaxPriorityQueue.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    MaxPriorityQueue obj = new MaxPriorityQueue(5);
    testCases(obj);
  }
}
 
