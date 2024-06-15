package oracle.aconex.assignment;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// This class represents a report of unique customer id grouped by a specific column.
public class UniqueCustomerIdReport extends GroupByColumnReport {
    /**
     * @inheritDoc
     **/
    public UniqueCustomerIdReport(String rawData, GroupColumnType byColumn) {
        super(rawData, byColumn);
        uniqueCustomerIdMap = generateUniqueCustomerIdMapByColumn();
    }

    @Override
    public void print(PrintStream printer) {
        printList(printer);
    }

    /**
     * Print out number of unique customerId for each group column.
     * @param printer PrintStream to write output to.
     */
    public void printNumber(PrintStream printer) {
        printer.println("The number of unique customerId for each " + groupByColumn);
        printer.println(groupByColumn + ": unique customerId number");

        Set<Map.Entry<String, Set<String>>> entries = uniqueCustomerIdMap.entrySet();
        for (Map.Entry<String, Set<String>> entry: entries) {
            printer.println(entry.getKey() + ":       " + entry.getValue().size());
        }
    }

    /**
     * Print out list of unique customerId for each group column.
     * @param printer PrintStream to write output to.
     */
    public void printList(PrintStream printer) {
        printer.println("The list of unique customerId for each " + groupByColumn);
	    printer.println(groupByColumn + ": unique customerId list");

        Set<Map.Entry<String, Set<String>>> entries = uniqueCustomerIdMap.entrySet();
        for (Map.Entry<String, Set<String>> entry: entries) {
            Set<String> customerIds = entry.getValue();
            String idStr = "";
            for (String customerId : customerIds) {
                if (!idStr.isEmpty()) {
                    idStr += ",";
                }
                idStr += customerId;
            }
            printer.println(entry.getKey() + ":       " + idStr);
        }
    }

    private Map<String, Set<String>> generateUniqueCustomerIdMapByColumn() {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        for (Project project : projectData) {
            String key = project.getColumnValue(groupByColumn);
            if (!map.containsKey(key)) {
                Set<String> customerIdSet = new HashSet<String>();
                customerIdSet.add(project.getCustomerId());
                map.put(key, customerIdSet);
            } else {
                Set<String> customerIdSet = map.get(key);
                if (!customerIdSet.contains(project.getCustomerId())) {
                    customerIdSet.add(project.getCustomerId());
                }
            }
        }
        return map;
    }

    // Map with Key=groupBy column value and value=Set of unique customerId for that column value
    protected Map<String, Set<String>> uniqueCustomerIdMap;
}
