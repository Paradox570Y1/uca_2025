package UnsolvedSA;
import UnsolvedSA.model.Task;
import UnsolvedSA.algos.SRTFScheduler;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Task> tasks = new ArrayList<>();
	tasks.add(new Task(1, 0, 5, 1)); // arrival=0, burst=5
        tasks.add(new Task(2, 1, 3, 1)); // arrival=1, burst=3
        tasks.add(new Task(3, 2, 4, 1)); // arrival=2, burst=4
        tasks.add(new Task(4, 3, 2, 1)); // arrival=3, burst=2

        SRTFScheduler scheduler = new SRTFScheduler(); 
        scheduler.execute(tasks);
    }
}
