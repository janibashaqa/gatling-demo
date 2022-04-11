# Gatling-demo

Prerequisites :
JDK 1.5 or above
maven 
Gatling 3.3.1 or above
intellij ide Community Edition

Import this project into Intellij and add required Scala SDK setup (2.12.2) and Click on Run Engine. It will ask for Run Description and results will be available in target folder.

This Repository is for Gatling Sample application demo load test execution through maven build.

In src folder BasicSimulation Scala file is available

Created three Objects

1. First Object Search for execution of Homepage and Search Computer requests along with Check assertion
2. Second object Browse for navigation of pages number wise through repeat for 4 times
3. Third Object Edit for Creation of New Computer Name addition

HTTP Protocol configuration contains the baseURL and other configurations such as user agent, language header, connection etc.

Created two variables Users and admins which consists of scenario definitions.

Added clear comment sections for better understanding in Simulation file.

********* 2nd Approach **********
Prerequisite : JDK 1.5 or above
Download the Gatling charts highcharts bundle 3.3.1.

Under user_files >> resources >> add search.csv for search criteria
Under user_files >> simulations >> com >> gatling >> demo >> Add BasicSimulation file

Go to Bin >> Based on OS select gatling.bat or gatling.sh and provide run description for execution of test for 30 Seconds.







