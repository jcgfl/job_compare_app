# Assignment 5 Design Description
 
> 1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).
 
 
To realize this requirement, I added a 'startUp' function to the 'Main Menu' class. The function will handle the rendering of options within the GUI. To control the visibility of the comparison option, I added a calculated 'comparisonEnable' boolean to the class that can be synced with the button enabled state to match the criteria of: two or more job offers when there's no current job; or when there's a current job and at least one job offer.
 
> 2. When choosing to enter current job details, a user will:
 
> 2.a) Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job
 
To realize this requirement, I created a 'Current Job' subclass that inherits a 'Job' class.
 
The 'Job' class has the following fields:
 
| Field                          | Data type  |
| ------------------------------ | ---------- |
| Title                          | String     |
| Company                        | String     |
| Location                       | _Location_ |
| Cost of living in the location | Integer    |
| Yearly salary                  | _Money_    |
| Yearly bonus                   | _Money_    |
| Allowed weekly telework days   | Integer    |
| Leave time                     | Integer    |
| Gym membership allowance       | _Money_    |
 
The following utilities were also used to handle the special data types:
 
- _\<\<utility\>\> Money_
- _\<\<utility\>\> Location_: includes two String fields to represent the city and the state
 
The ability to enter and edit the information will be handled within the GUI.
 
 
> 2.b) Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu
 
To realize this requirement, I added two separate functions to the 'Current Job' class that can be triggered within the GUI to handle the appropriate data calls and screen transitions: 'saveCurrentJob()' and 'returnWithoutSaving()'.
 
 
> 3. When choosing to enter job offers, a user will:
 
> 3.a) Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job
 
To realize this requirement, I created a 'Job Offer' subclass that inherits the 'Job' class. This enables a 'Job Offer' to have the exact same fields from a 'Job' object.
 
> 3.b) Be able to either save the job offer details or cancel.
 
To realize this requirement, I added one function to the 'Job Offer' class that can be triggered within the GUI to handle the appropriate data calls and screen transitions: 'saveJobOffer()'. The cancel function would have the same effect as return to the Main Menu, which would be handled by the 'returnToMainMenu()' function.
 
> 3.c) Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
 
To realize this requirement, I added three separate functions to the 'Job Offer' class that can be triggered within the GUI to handle the appropriate data calls and screen transitions: 'enterAnotherJobOffer()', 'returnToMainMenu()' and 'compareWith(Current Job)'. 
 
The 'compareWith(Current Job)' function requires a 'Current Job' to exist and this check will be enforced within the GUI to guide users to the appropriate steps to perform such comparison.
 
 
> 4. When adjusting the comparison settings, the user can assign integer weights to:
 
To realize this requirement, I have added the following weights as settings to the connection between the 'Main Menu' class and the 'Job Offers Comparison' class:
 
| Weight Fields                | Default weight |
| ---------------------------- | -------------- |
| Yearly salary                | 1              |
| Yearly bonus                 | 1              |
| Allowed weekly telework days | 1              |
| Leave time                   | 1              |
| Gym membership allowance     | 1              |
 
> If no weights are assigned, all factors are considered equal.
 
This was realized by setting default values to the 'Comparison settings' parameters with an equal weight of '1' each.
 
> 5. When choosing to compare job offers, a user will:
 
> 5.a) Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
 
To realize this requirement, I added two aggregation relationships between the 'Job Offers Comparison' class and 'Job Offer' and 'Current Job'.
 
 
> 5.b) Select two jobs to compare and trigger the comparison.
 
This can be handled within the GUI given all data is currently available following the data relationship among classes.
 
> 5.c) Be shown a table comparing the two jobs, displaying, for each job:
 
The GUI would then be able to consume fields from those classes and display the information to the user.
 
> 5.c.iv) Yearly salary adjusted for cost of living and Yearly bonus adjusted for cost of living
 
> 5.c.v) Yearly bonus adjusted for cost of living
 
To realize this requirement, I added two calculated fields to the 'Job' class: '/ajustedYearlySalary' and '/ajustedYearlyBonus'. These would be calculated once a Job is created based on the provided values of 'costOfLivingIndex', 'yearlySalary' and 'yearlyBonus'.
 
> 5.d) Be offered to perform another comparison or go back to the main menu.
 
To realize this requirement, I added two separate functions to the 'Job Offers Comparison' class that can be triggered within the GUI to handle the appropriate screen transitions: 'performAnotherComparison()' and 'returnToMainMenu()'.
 
 
> 6. When ranking jobs, a job’s score is computed as the weighted sum of:
 
$$
AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
$$
 
To realize this requirement, I added the '/jobRankScore' calculated field to the association settings used by the 'Job Offers Comparison' class. The rank is calculated using information from the weights set for the comparison and information from classes inherited from 'Job'.
 
> 7. The user interface must be intuitive and responsive.
 
This will be addressed by the GUI implementation hence it wasn’t covered by my design.
 
> 8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
 
This will be addressed by the technical implementation hence it wasn’t covered by my design.
