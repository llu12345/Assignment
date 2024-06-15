package oracle.aconex.assignment;

public class ReportApp {

    /**
     * The main method of this program. It prints out the solution of the assignment.
     *
     * @param args Arguments passed to this program.
     */
    public static void main(String[] args) {

        if (args.length >= 1) {
            // 1. Report the number of unique customerId for each contractId
            UniqueCustomerIdReport report1 = new UniqueCustomerIdReport(args[0], GroupColumnType.CONTRACT_ID);
            report1.printNumber(System.out);

            // 2. Report the number of unique customerId for each geozone
            UniqueCustomerIdReport report2 = new UniqueCustomerIdReport(args[0], GroupColumnType.GEOZONE);
            report2.printNumber(System.out);

            // 3. Report average buildduration for each geozone
            BuilddurationReport report3 = new BuilddurationReport(args[0], GroupColumnType.GEOZONE);
            report3.print(System.out);

            // 4. Report list of unique customerId for each geozone
            report2.printList(System.out);
        }
    }
}
