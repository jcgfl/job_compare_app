1. When the app is started, the user is presented with the main menu, which allows the
user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison
settings, or (4) compare job offers (disabled if no job offers were entered yet1).

- These four operations will be implemented in main menu UI when user start the app. In my desgin, the main menu class serves as the driving class which incorporates these functions. Sepcifically, the following methods will be implemented:

	1) addCurrentJob(Job):void
	2) addJobOffer(Job):void
	4) adjustCompSetting(comparisonSetting):void
	4) compareOffers(Jobs): void

----------------------------------------------------------------------------

2. When choosing to enter current job details, a user will:
	a. Be shown a user interface to enter (if it is the first time) or edit all of the details of
their current job, which consist of:
		1. Title
		2. Company
		3. Location (entered as city and state)
		4. Cost of living in the location (expressed as an index)
		5. Yearly salary
		6. Yearly bonus
		7. Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
		8. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
		9. Gym membership allowance ($0 to $500 annually)
	b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

- For requirement a, these information around job details will show in the UI when the user choose to enter/edit current job. The user will then be able to type in the job details in the UI. The UI is not directly shown in my class diagram. For requirement b, there will be buttons in UI allowing the user to either save the job details or exit without saving.

---------------------------------------------------------------------------

3. When choosing to enter job offers, a user will:
	1. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
	2. Be able to either save the job offer details or cancel.
	3. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

- For requirement a, similar to 2a, the information around offer details will shown in UI when user choose to enter job offers. The user will then type in offer details. For requirement b, similar to 2b, the UI will have buttons for save and cancel respectively. For requirement c, the UI will have additional buttons allowing the user to enter another offer, return to main menu, and compre offer with current job.

----------------------------------------------------------------------------

4. When adjusting the comparison settings, the user can assign integer weights to:
	1. Yearly salary
	2. Yearly bonus
	3. Allowed weekly telework days
	4. Leave time
	5. Gym membership allowance
If no weights are assigned, all factors are considered equal.

- These weights will be saved as integers in the comparison settings class. The user will be able to navigate to this setting from the main menu. When user input weights, the weights setters will assign weights accordingly. Otherwise, if the user does not input their prefered weights, a equally weights of 20% will be assigned for each of the aspect.

----------------------------------------------------------------------------

5. When choosing to compare job offers, a user will:
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

- In main menu UI, the user can choose to compare offers by pressing the Compare Offer button. When this happens, the method compareOffers(Jobs) will be called. This method will show a list of job offers by their ranking from best to worst, as well as the current job. In this display, the user can further choose any two jobs to compare. If the user select two jobs to compare, the job details will show up as requested. From there, the user can also choose to either compare other job offers, or return to main menu.

----------------------------------------------------------------------------

6. When ranking jobs, a job’s score is computed as the weighted sum of:
AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
where:
	AYS = yearly salary adjusted for cost of living
	AYB = yearly bonus adjusted for cost of living
	GYM = Gym Membership Allowance ($0 to $500 annually)
	LT = leave time
	RWT = telework days per week
The rationale for the RWT subformula is:
	1. value of an employee hour = (AYS / 260) / 8
	2. commute hours per year (assuming a 1-hour/day commute) = 1 * (260 - 52 * RWT)
	3. therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8
For example, if the weights are 2 for the yearly salary, 2 for Gym Membership, and 1 for all other factors, the score would be computed as:
2/7 * AYS + 1/7 * AYB + 2/7 * GYM + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) *
(AYS / 260) / 8)

- In the job class, a method called calcJobScore(comparisonSetting) will take care of this function. The job details such as annual salary and bonus are attributes in the job class. When calculating the job scores, the annual salary and bonus will be adjusted by cost of living using method calcAdjYearlySalary and calcAdjYearlyBonus. Then, the adjusted pay together with other unadjusted job details will be weighted by the user defined weights or default weights. The final weighted result will be the job score.

----------------------------------------------------------------------------

7. The user interface must be intuitive and responsive.

- This is not represented directly in my design. The UI will be designed to be intuitive and responsive.

----------------------------------------------------------------------------

8. For simplicity, you may assume there is a single system running the app (no
communication or saving between devices is necessary).

- The main menu class will be the driving class, serving as a single system running the app. The major methods are all part of the operations in this class.

