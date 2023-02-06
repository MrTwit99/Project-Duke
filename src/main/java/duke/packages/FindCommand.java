package duke.packages;

import java.util.ArrayList;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command for finding tasks in the Duke's taskList and hard disk.
 *
 * @author MrTwit99
 * @since 2023-02-06
 */
public class FindCommand implements Command {

    /**
     * Returns a String message of the tasks that match the search input. Executes the find command and find all tasks
     * description and statuses that match the given string.
     *
     * @param parsedCmd ArrayList of String type that contains parsed information about the task description and
     *                  status generated by Parser.parse().
     * @param tasks Duke's TaskList object that stores all Tasks objects.
     * @param storage Duke's Storage object to allow file access.
     * @param ui Duke's Ui object to display and print messages to the user. Acts as a screen.
     * @return String message of all tasks description and statueses that match the given string.
     */
    @Override
    public String execute(ArrayList<String> parsedCmd, TaskList tasks, Storage storage, Ui ui) {
        String message;
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> taskList = tasks.getTaskList();
        int taskIndex = 1;
        String subString = parsedCmd.get(1);
        String[] words = subString.split(" ");
        sb.append("    ____________________________________________________________\n")
                .append("    Here are the matching tasks in your list:\n");

        switch (words.length) {
        case 1:
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getTaskInfo().toLowerCase().contains(subString)) {
                    sb.append("    ").append(taskIndex++).append(".").append(taskList.get(i).getTaskInfoStatus())
                            .append("\n");
                }
            }
            break;
        default:
            for (String word: words) {
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskInfo().toLowerCase().contains(word)) {
                        sb.append("    ").append(taskIndex++).append(".").append(taskList.get(i)
                                .getTaskInfoStatus()).append("\n");
                    }
                }
            }
            break;
        }

        sb.append("    ____________________________________________________________\n");
        message = sb.toString();
        sb.setLength(0);
        ui.printCommand(message);
        return message;
    }
}
