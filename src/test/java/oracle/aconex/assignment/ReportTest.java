package oracle.aconex.assignment;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test for Hello.
 * <p/>
 * A unit test aims to test all code and code paths of a specific class.
 */
public class ReportTest {

    @Test
    public void testUniqueCustomerIdListByContractId() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                             "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                             "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                             "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";
        UniqueCustomerIdReport report = new UniqueCustomerIdReport(inputString, GroupColumnType.CONTRACT_ID);
        report.print(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(4)));
        assertThat(result[0], is(equalTo("The list of unique customerId for each contractId")));
        assertThat(result[1], is(equalTo(GroupColumnType.CONTRACT_ID + ": unique customerId list")));

        for (int i = 2; i < result.length; i++) {
            if (result[i].startsWith("2345")) {
                String[] custIds = result[i].substring(12).split(",");
                assertThat(custIds.length, is(equalTo(3)));
                String[] expected = {"2343225", "1223456", "1233456"};
                boolean findAll = Arrays.asList(custIds).containsAll(Arrays.asList(expected));
                assertThat(findAll, is(equalTo(true)));
            } else if (result[i].startsWith("2346")) {
                String[] custIds = result[i].substring(12).split(",");
                assertThat(custIds.length, is(equalTo(2)));
                String[] expected = {"3244332", "3244132"};
                boolean findAll = Arrays.asList(custIds).containsAll(Arrays.asList(expected));
                assertThat(findAll, is(equalTo(true)));
            } else {
                fail("incorrect contractId found in the result: " + result[i]);
            }
        }
        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testUniqueCustomerIdNumberByContractId() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                             "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                             "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                             "3244332,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";
        UniqueCustomerIdReport report = new UniqueCustomerIdReport(inputString, GroupColumnType.CONTRACT_ID);
        report.printNumber(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(4)));
        assertThat(result[0], is(equalTo("The number of unique customerId for each contractId")));
        assertThat(result[1], is(equalTo(GroupColumnType.CONTRACT_ID + ": unique customerId number")));

        for (int i = 2; i < result.length; i++) {
            if (result[i].startsWith("2345")) {
                assertThat(result[i].substring(12), is(equalTo("3")));
            } else if (result[i].startsWith("2346")) {
                assertThat(result[i].substring(12), is(equalTo("1")));
            } else {
                fail("incorrect contractId found in the result: " + result[i]);
            }
        }
        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testUniqueCustomerIdListByGeozone() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                             "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                             "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";
        UniqueCustomerIdReport report = new UniqueCustomerIdReport(inputString, GroupColumnType.GEOZONE);
        report.printList(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(5)));
        assertThat(result[0], is(equalTo("The list of unique customerId for each geozone")));
        assertThat(result[1], is(equalTo(GroupColumnType.GEOZONE + ": unique customerId list")));

        for (int i = 2; i < result.length; i++) {
            if (result[i].startsWith("us_east")) {
                String[] custIds = result[i].substring(15).split(",");
                assertThat(custIds.length, is(equalTo(1)));
                String[] expected = {"2343225"};
                boolean findAll = Arrays.asList(custIds).containsAll(Arrays.asList(expected));
                assertThat(findAll, is(equalTo(true)));
            } else if (result[i].startsWith("us_west")) {
                String[] custIds = result[i].substring(15).split(",");
                assertThat(custIds.length, is(equalTo(1)));
                String[] expected = {"1223456"};
                boolean findAll = Arrays.asList(custIds).containsAll(Arrays.asList(expected));
                assertThat(findAll, is(equalTo(true)));
            } else if (result[i].startsWith("eu_west")) {
                String[] custIds = result[i].substring(15).split(",");
                assertThat(custIds.length, is(equalTo(2)));
                String[] expected = {"3244332", "3244132"};
                boolean findAll = Arrays.asList(custIds).containsAll(Arrays.asList(expected));
                assertThat(findAll, is(equalTo(true)));
            } else {
                fail("incorrect contractId found in the result: " + result[i]);
            }
        }
        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testUniqueCustomerIdNumberByGeozone() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                             "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                             "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                             "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";
        UniqueCustomerIdReport report = new UniqueCustomerIdReport(inputString, GroupColumnType.GEOZONE);
        report.printNumber(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(5)));
        assertThat(result[0], is(equalTo("The number of unique customerId for each geozone")));
        assertThat(result[1], is(equalTo(GroupColumnType.GEOZONE + ": unique customerId number")));

        for (int i = 2; i < result.length; i++) {
            if (result[i].startsWith("us_east")) {
                assertThat(result[i].substring(15), is(equalTo("1")));
            } else if (result[i].startsWith("us_west")) {
                assertThat(result[i].substring(15), is(equalTo("2")));
            } else if (result[i].startsWith("eu_west")) {
                assertThat(result[i].substring(15), is(equalTo("2")));
            } else {
                fail("incorrect contractId found in the result: " + result[i]);
            }
        }
        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testUniqueCustomerIdNumberByTeamcode() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                             "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                             "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";
        UniqueCustomerIdReport report = new UniqueCustomerIdReport(inputString, GroupColumnType.TEAMCODE);
        report.printNumber(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(5)));
        assertThat(result[0], is(equalTo("The number of unique customerId for each teamcode")));
        assertThat(result[1], is(equalTo(GroupColumnType.TEAMCODE + ": unique customerId number")));

        for (int i = 2; i < result.length; i++) {
            if (result[i].startsWith("RedTeam")) {
                assertThat(result[i].substring(15), is(equalTo("1")));
            } else if (result[i].startsWith("BlueTeam")) {
                assertThat(result[i].substring(16), is(equalTo("1")));
            } else if (result[i].startsWith("YellowTeam3")) {
                assertThat(result[i].substring(19), is(equalTo("2")));
            } else {
                fail("incorrect contractId found in the result: " + result[i]);
            }
        }
        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testAverageBuilddurationByGeozone() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                             "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                             "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                             "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";
        BuilddurationReport report = new BuilddurationReport(inputString, GroupColumnType.GEOZONE);
        report.printAverage(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(5)));
        assertThat(result[0], is(equalTo("The average buildduration for each geozone")));
        assertThat(result[1], is(equalTo(GroupColumnType.GEOZONE + ": average buildduration")));

        for (int i = 2; i < result.length; i++) {
            if (result[i].startsWith("us_east")) {
                assertThat(result[i].substring(15), is(equalTo("3445s")));
            } else if (result[i].startsWith("us_west")) {
                assertThat(result[i].substring(15), is(equalTo("2216s")));
            } else if (result[i].startsWith("eu_west")) {
                assertThat(result[i].substring(15), is(equalTo("4222s")));
            } else {
                fail("incorrect contractId found in the result: " + result[i]);
            }
        }
        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testMaximumBuilddurationByGeozone() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                             "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                             "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                             "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";
        BuilddurationReport report = new BuilddurationReport(inputString, GroupColumnType.GEOZONE);
        report.printMaximum(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(5)));
        assertThat(result[0], is(equalTo("The maximum buildduration for each geozone")));
        assertThat(result[1], is(equalTo(GroupColumnType.GEOZONE + ": maximum buildduration")));

        for (int i = 2; i < result.length; i++) {
            if (result[i].startsWith("us_east")) {
                assertThat(result[i].substring(15), is(equalTo("3445s")));
            } else if (result[i].startsWith("us_west")) {
                assertThat(result[i].substring(15), is(equalTo("2221s")));
            } else if (result[i].startsWith("eu_west")) {
                assertThat(result[i].substring(15), is(equalTo("4322s")));
            } else {
                fail("incorrect contractId found in the result: " + result[i]);
            }
        }
        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testMinimumBuilddurationByGeozone() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                             "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                             "1233456,2345,us_west,BlueTeam,ProjectDate,2211s\n" +
                             "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";
        BuilddurationReport report = new BuilddurationReport(inputString, GroupColumnType.GEOZONE);
        report.printMinimum(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(5)));
        assertThat(result[0], is(equalTo("The minimum buildduration for each geozone")));
        assertThat(result[1], is(equalTo(GroupColumnType.GEOZONE + ": minimum buildduration")));

        for (int i = 2; i < result.length; i++) {
            if (result[i].startsWith("us_east")) {
                assertThat(result[i].substring(15), is(equalTo("3445s")));
            } else if (result[i].startsWith("us_west")) {
                assertThat(result[i].substring(15), is(equalTo("2211s")));
            } else if (result[i].startsWith("eu_west")) {
                assertThat(result[i].substring(15), is(equalTo("4122s")));
            } else {
                fail("incorrect contractId found in the result: " + result[i]);
            }
        }
        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testAverageBuilddurationByTeamcode() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                             "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                             "3244332,2346,eu_west,BlueTeam,ProjectCarrot,4322s\n" +
                             "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                             "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";
        BuilddurationReport report = new BuilddurationReport(inputString, GroupColumnType.TEAMCODE);
        report.print(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(5)));
        assertThat(result[0], is(equalTo("The average buildduration for each teamcode")));
        assertThat(result[1], is(equalTo(GroupColumnType.TEAMCODE + ": average buildduration")));

        for (int i = 2; i < result.length; i++) {
            if (result[i].startsWith("RedTeam")) {
                assertThat(result[i].substring(15), is(equalTo("3445s")));
            } else if (result[i].startsWith("BlueTeam")) {
                assertThat(result[i].substring(16), is(equalTo("2918s")));
            } else if (result[i].startsWith("YellowTeam3")) {
                assertThat(result[i].substring(19), is(equalTo("4122s")));
            } else {
                fail("incorrect contractId found in the result: " + result[i]);
            }
        }
        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testMinimumBuilddurationByProjectcode() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n";
        BuilddurationReport report = new BuilddurationReport(inputString, GroupColumnType.PROJECTCODE);
        report.print(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(3)));
        assertThat(result[0], is(equalTo("The average buildduration for each projectcode")));
        assertThat(result[1], is(equalTo(GroupColumnType.PROJECTCODE + ": average buildduration")));
        assertThat(result[2].startsWith("ProjectApple"), is(equalTo(true)));
        assertThat(result[2].substring(20), is(equalTo("3445s")));

        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    @Test
    public void testAverageBuilddurationByTeamcodeEmptyInput() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        String inputString = "";
        BuilddurationReport report = new BuilddurationReport(inputString, GroupColumnType.TEAMCODE);
        report.print(stream);
        String[] result = os.toString().split(System.lineSeparator());
        assertThat(result.length, is(equalTo(2)));
        assertThat(result[0], is(equalTo("The average buildduration for each teamcode")));
        assertThat(result[1], is(equalTo(GroupColumnType.TEAMCODE + ": average buildduration")));

        String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        dumpTestResult(testName, inputString, os.toString());
    }

    private void dumpTestResult(String testName, String input, String output) {
        System.out.println("=========== " + testName + " =============");
        System.out.println("-------------------------- input ----------------------------");
        System.out.println(input);
        System.out.println("------------------------- output ----------------------------");
        System.out.println(output);
        System.out.println("=============================================================");
    }
}

