package java7.concurrent.practice.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.logging.Logger;

/**
 * Created by senyuanwang on 15/5/30.
 */
public class TaskManager<T> {

    private List<ForkJoinTask<T>> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public synchronized void addTask(ForkJoinTask<T> task) {
        this.tasks.add(task);
    }

    public synchronized void cancelTasksOtherThan(ForkJoinTask<T> task) {
        for (ForkJoinTask<T> otherTask : tasks) {
            if (otherTask instanceof HasParent
                    && !isAnchorOf((HasParent) otherTask, (HasParent) task)
                    && !otherTask.isCancelled()) {
                otherTask.cancel(false);
                if (otherTask instanceof TaskInfo) {
                    TaskInfo taskInfo = (TaskInfo) otherTask;
                    Logger.getGlobal().fine(() -> taskInfo.getTaskInfo());
                }
            }
        }
        Logger.getGlobal().fine("cancelTasks called.");
    }

    private <U> boolean isAnchorOf(HasParent<U> otherTask, HasParent<U> task) {
        HasParent<U> tmp = task;

        while (tmp != null) {
            if (tmp == otherTask) {
                return true;
            }
            tmp = tmp.getParent();
        }

        return false;
    }
}
