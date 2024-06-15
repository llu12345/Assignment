package oracle.aconex.assignment;

// This class represents a project.
public class Project {
    /**
     * Constructor.
     * @param customerId
     * @param contractId
     * @param geozone
     * @param teamcode
     * @param projectCode
     * @param buildduration
     */
    public Project(String customerId,
                   String contractId,
                   String geozone,
                   String teamcode,
                   String projectcode,
                   int buildduration) {
        _customerId = customerId;
        _contractId = contractId;
        _geozone = geozone;
        _teamcode = teamcode;
        _projectcode = projectcode;
        _buildduration = buildduration;
    }

    /**
     * Get customerId.
     * @return String customerId value.
     */
    public String getCustomerId() {
        return _customerId;
    }

    /**
     * Get contractId.
     * @return String contractId value.
     */
    public String getContractId() {
        return _contractId;
    }

    /**
     * Get geozone.
     * @return String geozone value.
     */
    public String getGeozone() {
        return _geozone;
    }

    /**
     * Get teamcode.
     * @return String teamcode value.
     */
    public String getTeamcode() {
        return _teamcode;
    }

    /**
     * Get projectcode.
     * @return String project code value.
     */
    public String getProjectcode() {
        return _projectcode;
    }

    /**
     * Get buildduration.
     * @return String buildduration value.
     */
    public int getBuildduration() {
        return _buildduration;
    }

    /**
     * Get groupby column value.
     * @param col column name.
     * @return String value of the column.
     */
    public String getColumnValue(GroupColumnType col) {
        if (GroupColumnType.GEOZONE.equals(col)) {
            return getGeozone();
        } else if (GroupColumnType.CONTRACT_ID.equals(col)) {
            return getContractId();
        } else if (GroupColumnType.CUSTOMER_ID.equals(col)) {
            return getCustomerId();
        } else if (GroupColumnType.TEAMCODE.equals(col)) {
            return getTeamcode();
        } else if (GroupColumnType.PROJECTCODE.equals(col)) {
            return getProjectcode();
        }
        return null;
    }

    private String _customerId;
    private String _contractId;
    private String _geozone;
    private String _teamcode;
    private String _projectcode;
    private int _buildduration;
}

