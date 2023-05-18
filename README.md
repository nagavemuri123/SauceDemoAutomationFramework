About Framework Design: 

1. This is developed with BDD approach along with selenium java and Testng
2. I used updated webdriver manager dependencies so that to avoid external chrome driver exe files to be maintained. Based on this approach we can run the scenario's even     after the chrome version upgrades without any intervention.
3. I used Threadlocal concept in base factory to run the scenario's in multiple browsers at a time.
4. I Used Page factory as an approach for my page classes for easy mainatainance and intialization for page classes.
5. I maintained sepreate report formats like HTML and PDF reports.
6. I maintained screenshots for the failures attached to the same report structure where you can find it in reports folder.
7. Maintained seperate excel sheet for user details and fetching those to my scripts
8. Maintained different properties files like extent, config properties.
9. Based on modularity concept, I divided page classes and feature file in a proper way for easy maintainance and understandable.
10. I Maintained seperate folder for uI and API related feature files.
11. I maintianed stepdefinitions also as different for UI and API.
12. Created Utility folder to read config, excel data in a dynamic way.
13. Maintained dynamic wait concepts for all page classes from base factory implementation.
14. I used collections concept and retrieved the maximum value from the array list values from the list of elements.

Execution process :
Go to testRunner>TestRunnerUI>run the execution
