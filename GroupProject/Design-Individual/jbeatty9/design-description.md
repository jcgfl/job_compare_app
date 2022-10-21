# Design Description

Requirements
1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

To realize this requirement, there is an App model that provides the operations of presenting the main menu. Most of the requirement for editing the models with be performed in the GUI.
  
2. When choosing to _enter current job details, a user will:
    1. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
        1. Title
        2. Company
        3. Location (entered as city and state)
        4. Cost of living in the location (expressed as an [index])
        5. Yearly salary
        6. Yearly bonus
        7. Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
        8. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
        9. Gym membership allowance ($0 to $500 annually)
    2. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
  
To realize this requirement, there is a Job model, with a sub model, Current Job. Job has the text `title`, text `company`, money `yearly Salary`, money `yearly Bonus`, integer from 0 to 5, inclusive, `telework Days`, integer `leave Time`, and money `gym Membership Allowance`. Since a each Job has a Location, but that could be duplicated between multiple Jobs, there is a `Location` class with a text `city`, text `state`, and decimal value `cost Of Living Index`. The interactions will be handled in the GUI. 
    
3. When choosing to _enter job offers, a user will:
    1. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
    2. Be able to either save the job offer details or cancel.
    3. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
  
To realize this requirement, there is a Job model, with a sub model, Job Offer. Job Offer gets to have the same characteristics and Job described earlier. The interactions will be handled in the GUI. 
    
4. When adjusting the comparison settings, the user can assign integer weights to:
    1. Yearly salary
    2. Yearly bonus
    3. Allowed weekly telework days
    4. Leave time
    5. Gym membership allowance
    If no weights are assigned, all factors are considered equal.

To realize this requirement, there is a Comparison Settings model. Comparison Settings has an integer `yearly Salary Weight`, an integer `yearly Bonus Weight`, an integer `allowed Bonus Weight`, an integer `leave Time Weight`, and an integer `gym Membership Weight`. The interactions will be handled in the GUI. 
    
5.	When choosing to compare job offers, a user will:
    1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
    2. Select two jobs to compare and trigger the comparison.
    3. Be shown a table comparing the two jobs, displaying, for each job:
        1. Title
        2. Company
        3. Location 
        4. Yearly salary adjusted for cost of living
        5. Yearly bonus adjusted for cost of living
        6. Allowed weekly telework days
        7. Leave time
        8. Gym Membership Allowance
  4. Be offered to perform another comparison or go back to the main menu.
  
There is a Current Job model, and a Job Offer model that both use the Job model. Since they share a parent class, they can be compared. The data that needs to be compared is in both the Job Offer and Current Job classes. The interactions will be handled in the GUI. 
    
6.	When ranking jobs, a job’s score is computed as the **weighted** sum of:
  AYS + AYB + GYM + (LT \* AYS / 260) - ((260 - 52 \* RWT) \* (AYS / 260) / 8)
  where:
  AYS = yearly salary adjusted for cost of living
  AYB = yearly bonus adjusted for cost of living
  GYM = Gym Membership Allowance ($0 to $500 annually)
  LT = leave time
  RWT = telework days per week
  The rationale for the RWT subformula is:
    1. value of an employee hour = (AYS / 260) / 8
    2. commute hours per year (assuming a 1-hour/day commute) = 1 \* (260 - 52 \* RWT)
    3. therefore **travel-time cost =** **(260 - 52 \* RWT) \* (AYS / 260) / 8**
  For example, if the weights are 2 for the yearly salary, 2 for Gym Membership, and 1 for all other factors, the score would be computed as:
  2/7 \* AYS + 1/7 \* AYB + 2/7 \* GYM + 1/7 \* (LT \* AYS / 260) - 1/7 \* ((260 - 52 \* RWT) \* (AYS / 260) / 8)

To realize the requirement, the App model will be responsible to orchestrating the calculation, using each Job Offer and the Comparison Settings. The interactions will be handled in the GUI. 

7.  The user interface must be intuitive and responsive.

The interactions will be handled in the GUI. 
  
8.  For simplicity, you may assume there is a _single system_ running the app (no communication or saving between devices is necessary).

The requirements assume only one system.
  