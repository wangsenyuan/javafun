package java7.concurrent.practice.chapter5;

import java.util.Optional;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

/**
 * Created by senyuanwang on 15/5/30.
 */
public class SearchArrayTask extends RecursiveTask<Optional<Integer>> implements TaskInfo, HasParent<SearchArrayTask> {
    public static final int TASK_SIZE = 1000;

    private final int[] array;
    private final int start, end;
    private final int target;

    private final TaskManager<Optional<Integer>> taskManager;
    private final SearchArrayTask parent;
    private boolean jobDone = false;

    public SearchArrayTask(int[] array, int start, int end, int target, TaskManager<Optional<Integer>> taskManager, SearchArrayTask parent) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.target = target;
        this.taskManager = taskManager;
        taskManager.addTask(this);
        this.parent = parent;
    }

    @Override
    protected Optional<Integer> compute() {
        Optional<Integer> result;
        if (end - start < TASK_SIZE) {
            result = search();
        } else {
            int mid = (start + end) / 2;
            SearchArrayTask taskOne = new SearchArrayTask(array, start, mid, target, taskManager, this);
            SearchArrayTask taskTwo = new SearchArrayTask(array, mid, end, target, taskManager, this);
//            taskManager.addTask(taskOne);
//            taskManager.addTask(taskTwo);
            invokeAll(taskOne, taskTwo);
            Optional<Integer> resultOne = taskOne.join();
            Optional<Integer> resultTwo = taskTwo.join();
            if (resultOne.isPresent()) {
                result = resultOne;
            } else if (resultTwo.isPresent()) {
                result = resultTwo;
            } else {
                result = Optional.empty();
            }
        }

        Logger.getGlobal().finest(() ->
                String.format("Search %d -> %d, found? %s", start, end, result.isPresent()));

        jobDone = true;
        return result;
    }

    private Optional<Integer> search() {
        for (int i = start; i < end; i++) {
            if (array[i] == target) {
                taskManager.cancelTasksOtherThan(this);
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    @Override
    public String getTaskInfo() {
        return String.format("SearchArrayTask(%d, %d) cancelled ? %s", start, end, !jobDone);
    }

    @Override
    public HasParent<SearchArrayTask> getParent() {
        return this.parent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchArrayTask{");
        sb.append("end=").append(end);
        sb.append(", start=").append(start);
        sb.append('}');
        return sb.toString();
    }
}
