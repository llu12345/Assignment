package oracle.aconex.assignment;

// This class represents the types of columns that can be grouped by for GroupedReport.
public class GroupColumnType {
    public static final GroupColumnType CUSTOMER_ID = new GroupColumnType("customerId");
    public static final GroupColumnType CONTRACT_ID = new GroupColumnType("contractId");
    public static final GroupColumnType GEOZONE = new GroupColumnType("geozone");
    public static final GroupColumnType TEAMCODE = new GroupColumnType("teamcode");
    public static final GroupColumnType PROJECTCODE = new GroupColumnType("projectcode");

    @Override
    public String toString() {
        return _columnName;
    }

    private GroupColumnType(String columnName) {
        _columnName = columnName;
    }

    private String _columnName;
}

