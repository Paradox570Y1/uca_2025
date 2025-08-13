package UnsolvedSA.algos;
import UnsolvedSA.model.Task;

import java.util.List;

public interface Scheduler {
    public void  execute(List<Task> taskList) throws InterruptedException;
}
