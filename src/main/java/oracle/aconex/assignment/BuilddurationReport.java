package oracle.aconex.assignment;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// This class represents a report of build duration information grouped by a specified column.
public class BuilddurationReport extends GroupByColumnReport {
    /**
     * @inheritDoc
     **/
    public BuilddurationReport(String rawData, GroupColumnType byColumn) {
        super(rawData, byColumn);
        builddurationMap = generateBuilddurationByColumn();
    }

    @Override
    public void print(PrintStream printer) {
        printAverage(printer);
    }

    /**
     * Print out average buildduration for each group column.
     * @param printer PrintStream to write output to.
     */
    public void printAverage(PrintStream printer) {
        printer.println("The average buildduration for each " + groupByColumn);
        printer.println(groupByColumn + ": average buildduration");

        Set<Map.Entry<String, List<Integer>>> entries = builddurationMap.entrySet();
        for (Map.Entry<String, List<Integer>> entry: entries) {
            List<Integer> durations = entry.getValue();
            int num = 0;
            if (durations.size() > 0) {
                for (Integer duration : durations) {
                    num += duration;
                }
                num = num / durations.size();
            }
            printer.println(entry.getKey() + ":       " + num + "s");
        }
    }

    /**
     * Print out maximum buildduration for each group column.
     * @param printer PrintStream to write output to.
     */
    public void printMaximum(PrintStream printer) {
        printer.println("The maximum buildduration for each " + groupByColumn);
        printer.println(groupByColumn + ": maximum buildduration");

        Set<Map.Entry<String, List<Integer>>> entries = builddurationMap.entrySet();
        for (Map.Entry<String, List<Integer>> entry: entries) {
            List<Integer> durations = entry.getValue();
            int max = 0;
            for (Integer duration : durations) {
                max = Math.max(duration, max);
            }
            printer.println(entry.getKey() + ":       " + max + "s");
        }
    }

    /**
     * Print out minimum buildduration for each group column.
     * @param printer PrintStream to write output to.
     */
    public void printMinimum(PrintStream printer) {
        printer.println("The minimum buildduration for each " + groupByColumn);
        printer.println(groupByColumn + ": minimum buildduration");

        Set<Map.Entry<String, List<Integer>>> entries = builddurationMap.entrySet();
        for (Map.Entry<String, List<Integer>> entry: entries) {
            List<Integer> durations = entry.getValue();
            int min = Integer.MAX_VALUE;
            if (durations.size() > 0) {
                for (Integer duration : durations) {
                    min = Math.min(duration, min);
                }
            } else {
                min = 0;
            }
            printer.println(entry.getKey() + ":       " + min + "s");
        }
    }

    private Map<String, List<Integer>> generateBuilddurationByColumn() {
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        for (Project project : projectData) {
            String key = project.getColumnValue(groupByColumn);
            if (!map.containsKey(key)) {
                List<Integer> durationList = new ArrayList<Integer>();
                durationList.add(project.getBuildduration());
                map.put(key, durationList);
            } else {
                List<Integer> durationList = map.get(key);
                durationList.add(project.getBuildduration());
            }
        }
        return map;
    }

    // Map with Key=groupBy column value and value=List of buildduration data for that column value
    protected Map<String, List<Integer>> builddurationMap;
}
