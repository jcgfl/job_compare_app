# Design Description

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).

	I use **MainMenu** class as the entry point of the system, from **MainMenu**, the user will be able to navigate to **CurrentJob**, **JobOffers**, **ComparisonSettings** and **CompareOffers**. The user will also be able to return to **MainMenu** when finish each task.

2. When choosing to _enter current job details,_ a user will:
    a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
    i. Title
    ii. Company
    iii. Location (entered as city and state)
    iv. Cost of living in the location (expressed as an index)
    v. Yearly salary
    vi. Yearly bonus
    vii. Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
    viii. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
    ix. Gym membership allowance ($0 to $500 annually)
   b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

   Since both **CurrentJob** and **JobOffers** require same details for jobs, I use a **Job** class for all job instances. It includes all the details listed in 2.a., with extra **otherNotes** attributes, because in most of the cases, a job cannot be perfectly described by the above i-viii metrics and the user can put more information in **otherNotes**. The Job class includes methods to display job details, create new job instances or edit existing job instances when user enter job details from **CurrentJob** or **JobOffers**.
    **CurrentJob** class uses **Job** class to creat or edit a job instance (based on **isFirstTime**, which is a boolean value to indicate if a current job present). The user can choose to **save**, **cancel** or **delete** it, and then return to **MainMenu**. I add **showCurrentJob** so if present, the user can view the current job details in the interface.

3. When choosing to _enter job offers,_ a user will:
a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
b. Be able to either save the job offer details or cancel.
c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

    Similar to **CurrentJob**, **JobOffers** also uses **Job** class to create or edit job instances. **numberofOffers** is used to keep track of the number of offers save. The user can compare an offer with current job by using **compareWithCurrent**.

4. When _adjusting the comparison settings,_ the user can assign integer _weights_ to:
a. Yearly salary
b. Yearly bonus
c. Allowed weekly telework days
d. Leave time
e. Gym membership allowance
If no weights are assigned, all factors are considered equal.
The **ComparisonSettings** class includes five attributes corresponding to requirements a-e. The user can set weights and return to **MainMenu** when finish.

5. When choosing to _compare job offers,_ a user will:
a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
b. Select two jobs to compare and trigger the comparison.
c. Be shown a table comparing the two jobs, displaying, for each job:
i. Title
ii. Company
iii. Location
iv. Yearly salary adjusted for cost of living
v. Yearly bonus adjusted for cost of living
vi. Allowed weekly telework days
vii. Leave time
viii. Gym Membership Allowance
d. Be offered to perform another comparison or go back to the main menu.

    The **CompareOffers** class shows all jobs saved in **CurrentJob** and **JobOffers**. The indication of current job is a trivial property of how the jobs are presented so it’s not included in my design. The jobs are ranked by **rankJobs**, based on **ComparisonSettings**. This class also need to show comparison results in a table format.

6. When ranking jobs, a job’s score is computed as the **weighted** sum of: AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
GYM = Gym Membership Allowance ($0 to $500 annually)
LT = leave time
RWT = telework days per week
The rationale for the RWT subformula is:
a. value of an employee hour = (AYS / 260) / 8
b. commute hours per year (assuming a 1-hour/day commute) = 1 * (260 - 52 * RWT)
c. therefore **travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8**
For example, if the weights are 2 for the yearly salary, 2 for Gym Membership, and 1 for all other factors, the score would be computed as:
2/7 * AYS + 1/7 * AYB + 2/7 * GYM + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) *(AYS / 260) / 8)

    This is a detailed mathematical calculation of how the jobs are ranked based on their weights, and will be handled in code implementation, so it’s not included in my design.

7. The user interface must be intuitive and responsive.

    The user interface will be handled in GUI implementation, so it’s not included in my design.

8. For simplicity, you may assume there is a _single system_ running the app (no communication or saving between devices is necessary).

    Since it a single-user, single-system app, I don’t need to include functions associated with communication between devices, save data for multiple users, etc., so they are not included in my design.