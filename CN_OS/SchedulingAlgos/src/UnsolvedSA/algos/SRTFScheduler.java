package UnsolvedSA.algos;

import UnsolvedSA.model.Task;

import java.util.*;

public class SRTFScheduler implements Scheduler {
    int timeQuantum;
    Map<Task, Thread> taskThreadMap;

    public SRTFScheduler() {
        this.timeQuantum = 1;
        this.taskThreadMap = new HashMap<>();
    }

    @Override
    public void execute(List<Task> taskList) throws InterruptedException {
        PriorityQueue<Task> readyQueue = new PriorityQueue<>((t1,t2)->{
	    if(t1.getRemainingTime() != t2.getRemainingTime()){
	        return Integer.compare(t1.getRemainingTime(),t2.getRemainingTime());
	    }
	    if(t1.getPriority() != t2.getPriority()){
	        return Integer.compare(t2.getPriority(),t1.getPriority());
	    }
	    return Integer.compare(t1.getArrivalTime(), t2.getArrivalTime());
	});
	for(Task task:taskList){
	    Thread t = new Thread(task);
	    t.start();
	    taskThreadMap.put(task,t);
	}
	int totalTask = taskList.size();
        int currentTime = 0;
	int completedTask = 0;
	int taskIdx = 0;
	while(completedTask < totalTask){
	    while(taskIdx<totalTask && taskList.get(taskIdx).getArrivalTime() <= currentTime){
	        readyQueue.offer(taskList.get(taskIdx));
		taskIdx++;
	    }
	    if(readyQueue.isEmpty()){
	        currentTime++;
		Thread.sleep(100);
		continue;
	    }
	    Task currentTask = readyQueue.poll();
	    int executedUnit = 0;
	    while(!currentTask.isCompleted() && executedUnit<timeQuantum){
	        currentTask.resume();
		Thread.sleep(100);
		currentTime++;
		executedUnit++;

		while(taskIdx<totalTask && taskList.get(taskIdx).getArrivalTime() <= currentTime){
		    readyQueue.offer(taskList.get(taskIdx));
		    taskIdx++;
		}
	    }
	    if(currentTask.isCompleted()){
		currentTask.calculateTimes(currentTime);
	        completedTask++;
	    } else {
	        readyQueue.offer(currentTask);
	    }
	}
	for(Thread t:taskThreadMap.values()){
	    t.join();
	}
	printStatistic(taskList);
    }

    private void printStatistic(List<Task> taskList) {
        System.out.println("\nTask | Completion | Turnaround | Waiting");
        for (Task t : taskList) {
            System.out.printf(" %2d  |     %2d     |     %2d     |    %2d\n",
                    t.getTaskId(), t.getCompletionTime(),
                    t.getTurnaroundTime(), t.getWaitingTime());
        }
    }
}


