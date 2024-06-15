package oracle.aconex.assignment;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// This abstract class represents a report based on organized data described below.
public abstract class Report {
    /**
     * Construct Report object based on String data.
     * @param rawData a String with the following data (it includes multiple lines), such as:
     *                2343225,2345,us_east,RedTeam,ProjectApple,3445s
     *                1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
     *                3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
     *                1233456,2345,us_west,BlueTeam,ProjectDate,2221s
     *                3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s
     *                The data is organized into columns delimited by a comma (,) in the following order:
     *                customerId,contractId,geozone,teamcode,projectcode,buildduration
     *                The first line of data would then be interpreted as
     *                2343225 is the customerId
     *                2345 is the contractId
     *                us_east is the geozone
     *                RedTeam is the teamcode
     *                ProjectApple is the projectcode
     *                3445s is the buildduration
     **/
    public Report(String rawData) {
        projectData = parseData(rawData);
    }

    /**
     * Print the report.
     * @param printer PrintStream to write output to.
     */
    public abstract void print(PrintStream print);

    protected List<Project> parseData(String rawData) {
        List<Project> projList = new ArrayList<Project>();
        String[] lines = rawData.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            String[] words = lines[i].split(",");
            if (words.length >= 6) {
                Project project = new Project(words[0], words[1], words[2], words[3], words[4],
                                              Integer.valueOf(words[5].substring(0, words[5].length() - 1)));
                projList.add(project);
            }
        }
        return projList;
    }

    // List of Project objects
    protected List<Project> projectData;
}

