package oracle.aconex.assignment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Integration test for the ReportApp program.
 * <p>
 * This integration test takes the input String from the assignment and produce the full report.
 */
public class ReportWithTestsIT {
    @BeforeEach
    public void before() {
    }

    @AfterEach
    public void cleanUp() {
    }

    @Test
    public void generateReportTest() {
        String[] args = { "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                          "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                          "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                          "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                          "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n" };
        System.out.println("================= Run Integration ===================");
        System.out.println("----------------------- Input -----------------------");
        System.out.println(args[0]);
        System.out.println("----------------------- Output ----------------------");
        ReportApp.main(args);
        System.out.println("=====================================================");
    }
}
