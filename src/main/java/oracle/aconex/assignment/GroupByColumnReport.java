package oracle.aconex.assignment;

// This abstract class represents report grouped by a specific column.
public abstract class GroupByColumnReport extends Report {
    /**
     * Construct GroupedReport object based on String data.
     * @param rawData String with the data.
     * @param byColumn one of the following: "customerId", "contractId", "geozone", "teamcode", "projectcode"
     **/
    public GroupByColumnReport(String rawData, GroupColumnType byColumn) {
        super(rawData);
        groupByColumn = byColumn;
    }

    // The groupby column name.
    protected GroupColumnType groupByColumn;
}
