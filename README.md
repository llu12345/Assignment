# Assignment
assignment

## How to Run:

* Run `mvn verify`

   - it first completes all the junit tests (ReportTest),
   - then it run the integration test (ReportWithTestsIT) which prints out the solution for the assignment like the following:

Running oracle.aconex.assignment.ReportWithTestsIT
================= Run Integration ===================
----------------------- Input -----------------------
2343225,2345,us_east,RedTeam,ProjectApple,3445s
1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
1233456,2345,us_west,BlueTeam,ProjectDate,2221s
3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s

----------------------- Output ----------------------
The number of unique customerId for each contractId
contractId: unique customerId number
2346:       2
2345:       3
The number of unique customerId for each geozone
geozone: unique customerId number
eu_west:       2
us_west:       2
us_east:       1
The average buildduration for each geozone
geozone: average buildduration
eu_west:       4222s
us_west:       2216s
us_east:       3445s
The list of unique customerId for each geozone
geozone: unique customerId list
eu_west:       3244132,3244332
us_west:       1233456,1223456
us_east:       2343225
=====================================================
