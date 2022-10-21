# Test Plan

**Author**: Team 168, Fall 2021

**Version**: 3.4

**Last Update**: Oct 24th, 2021


## 1 Testing Strategy

### 1.1 Overall strategy

There shall be four types of testing performed on our application.
  - All data models will have automated Unit testing. The Developer responsible for implementing the models will write and run the automated tests before pushing code to the central repository.
  - There will be automated integration testing. The Developer responsible for implementing the Activities and Services will write and run the automated tests before pushing code to the central repository.
  - The QA role will provide manual system testing based on our testing script. This can be performed on device and on the Android virtual machines.
  - The QA role will be responsible to providing regression testing as the app development proceeds. This will occur after major changes and before each milestone.

### 1.2 Test Selection

Unit testing will be done on a white box method. The goal will be to have complete code coverage, and then to catch any bugs if they're added while development occurs.

Integration testing will be done on a white box method. The goal is to make sure the the screens receive data and output results as expected in the requirements.

System testing will be performed as a black box. Ideally, the QA role is a separate person from the development roles in order to ensure the QA doesn't know at all what's happening inside the app.

### 1.3 Adequacy Criterion

There shall be a test case that will succeed, and there shall be multiple test cases that will fail. These will involve inputting incorrect or incorrectly formatted data. 

The Unit testing will have test cases that expect success and expect failure.

The Integration tests will have test cases that expect success and expect failure.

The System and Regression testing will have prompts for the input in the scripts.

### 1.4 Bug Tracking

All bug tracking and enhancement requests will occur in [https://github.gatech.edu](https://github.gatech.edu)

### 1.5 Technology

We will use JUnit and Espresso to run the unit and integration tests. We will use a human for the sytem and regression testing.

## 2 Test Cases

| Purpose | Steps to perform | Expected result | Actual result | Pass/Fail information | Adtl Info |
| ------- | ---------------- | --------------- | ------------- | --------------------- | --------- |
|Assert the Main Menu Loads| Open the app, and look for the options to enter or edit current job details, enter job offers, adjust the comparison settings, compare job offers|When the app loads, there shall be options to enter or edit current job details, enter job offers, adjust comparison settings, compare Job offers|When the app loads, there are options to enter or edit current job details, enter job offers, adjust comparison settings, compare Job offers|PASS|N/A|
|Assert a new job is shown when no job has been set|From Main menu, look at option to enter or edit job details. Select it and see the entry field. | When selecting it for the first time, no job details should be shown.|When selecting Add Your Current Job for the first time, no job details are shown.|PASS|~~Minor display adjustments required: Current Job Details text on the top of screen are partially covered by Title inputs~~ (Fixed 10/16/2021)|
|Assert an existing current job was saved|From Main menu, look at option to enter or edit job details. Select it, save job details, then quit app. Start app again, look at option to enter or edit job details. Select it, and assert that existing job was saved.|After restarting the App and selecting Add Your Current Job on Main Menu, the current job information that have previously been entered should be displayed.|After restarting the App and selecting Add Your Current Job on Main Menu, the current job information that have previously been entered are displayed.|PASS|~~One more button required: Cancel without saving; Return to Main Menu after save or cancel~~ (Fixed 10/16/2021)|
|Assert that all Job details are enterable| From Main menu, look at option to enter or edit job details. Select it and enter into Title, Company, Location (City, State, Cost of living), Yearly Salary, Yearly Bonus, Allowed weekly telework days, leave time, and gym membership allowance.|There should be fields for the following values: Title, Company, Location (City, State, Cost of living), Yearly Salary, Yearly Bonus, Allowed weekly telework days, leave time, and gym membership allowance.|There are fields for the following values: Title, Company, Location (City, State, Cost of living), Yearly Salary, Yearly Bonus, Allowed weekly telework days, leave time, and gym membership allowance.|PASS|N/A|
|Assert that job details incorporate necessary data validation for user inputs| From Main menu, look at option to enter or edit job details. Select it and enter into invalid Cost of living, Yearly Salary, Yearly Bonus, Allowed weekly telework days, leave time, and gym membership allowance.|The input validation should not allow invalid user inputs|The invalid inputs are blocked when user types invalid text information (such as non-numeric values) into Cost of living, Yearly Salary, Yearly Bonus, Allowed weekly telework days, leave time, or gym membership allowance.|PASS|N/A|
|Assert that Job offer has the same fields as Current Job|From main menu, look at option to enter a job offer|There should be fields for the following values: Title, Company, Location (City, State, Cost of living), Yearly Salary, Yearly Bonus, Allowed weekly telework days, leave time, and gym membership allowance.|There are fields for the following values: Title, Company, Location (City, State, Cost of living), Yearly Salary, Yearly Bonus, Allowed weekly telework days, leave time, and gym membership allowance.|PASS|N/A|
|Assert that offer details incorporate necessary data validation for user inputs| From Main menu, look at option to enter job offer details. Select it and enter into invalid Cost of living, Yearly Salary, Yearly Bonus, Allowed weekly telework days, leave time, and gym membership allowance.|The input validation should not allow invalid user inputs|The invalid inputs are blocked when user types invalid text information (such as non-numeric values) into Cost of living, Yearly Salary, Yearly Bonus, Allowed weekly telework days, leave time, or gym membership allowance.|PASS|N/A|
|Be able to either save job offer details or cancel.|From main menu, look at option to enter a job offer.|There should be a button to save details and a button to cancel the details|There is a button to save details "Save Job Offer" and a button to cancel the details "Cancel", both function as expected|PASS|Integration Test. ~~Investigation Required: see why "Data not inserted" showed up after selecting Save. Job offers are successfully entered and can be viewed in Compare Offer~~ (Fixed 10/23/2021)|
|Be able to enter another offer, return to the main menu, or compare the offer (if they saved it) with the current job details (if present).|From main menu, look at option to enter a job offer. Enter valid job details, and select save. |A menu should appear after successfully saving the details with options to enter another job, return to main menu, or compare the offer.|After successful saving, a message "data inserted" will show up. Next, the user is directed to another screen, where the user can further select enter another offer, compare offers, or return to main menu. After cancel by selecting "Cancel", the user can choose enter another offer or return to main menu|PASS|Integration Test. ~~Update required: After user select Save Job Offer and then select Compare Offers, take user to compare Current Job with the job offer entered (if exists).~~ (Fixed 10/24/2021)|
|When adjusting the comparison settings, the user can assign integer weights to Yearly Salary, Yearly bonus, Allowed weekly telework days, leave time, gym membership allowance. If no weights are assigned, all factors are considered equal.| From main menu, look at option to adjust the comparison settings.|There should be the ability to change the weights into different integer values.|There is ability to change weights into different integer values. When no weights are set by user, all weights are default to 1.|PASS|~~Minor adjustments required: rename button text from "Save Current Job" to "Save Settings"~~ (Fixed 10/16/2021)|
|Assert that Comparison Setting incorporates necessary data validation for user inputs|  From Main menu, look at option to adjust comparison settings. Select it and enter into invalid inputs |There should be the ability block invalid values.|Non-integer values are not permitted to enter.|PASS|N/A|
|When choosing to compare job offers, a user will be shown a list of job offers, displayed at Title and Company, ranked from best to worst and including the current job, clearly indicated|The tester will need to enter at least two job offers into the app. From the main menu, select the compare job option.|The jobs should be ranked in the correct order|The jobs are ranked in the correct order|PASS|System Test. ~~Minor Adjustment Required: The current rank number is not continuous and ascending, eg. somtimes all equals to 1, sometimes may above 1000.~~ The number in front of company/title represent job score for debugging purpose. (Fixed 10/24/2021)|
|When choosing to compare job offers, a user will be able to compare two jobs and trigger the comparison|The tester will need to enter at least two job offers into the app. From the main menu, select the compare job option, then select two jobs, and a comparison table should appear with Yearly salary adjusted for cost of living and Yearly bonus adjusted for cost of living, Yearly bonus adjusted for cost of living.|The jobs comparison table should appear with the correct information|The jobs comparison table appears with the correct information|PASS|System Test|
|When choosing to compare job offers, a user will be able to compare two jobs and then have the ability to perform another comparison or go back to the main menu.|The tester will need to enter at least two job offers into the app. From the main menu, select the compare job option, then select two jobs, and a comparison table should appear. There should also be an option |The jobs comparison table should have options for a new comparison or return to the main menu|The jobs comparison table has options for a new comparison or return to the main menu|PASS|System Test|
|Job offer score should be correct|Hand calculate the score for a job based on a set of criteria, and assert the calculation in the app is correct|The calculations should be correct|The calculations are correct|PASS|This should be a unit test with multiple sets of inputs. ~~Minor Adjustment Required: 1) adjusted yearly salary and bonus should be calculated as: Yearly Salary/index and Yearly Bonus/index, instead of multiplying by index. 2) The last two components in job score calculation (leave time and telework) should be calculated using Adjusted Yearly Salary instead of the simple yearly salary.~~ (Fixed 10/23/2021)|
|The user interface must be intuitive and responsive.|The QA role should go through each screen, and note any slow operations|All screen transitions and operations should have no appearance of slowness.|All screen transitions and operations are having no appearance of slowness.|PASS|This is all a human perception problem|


