# Job_Queue

Project 4 README.dat File
Written by: Langston Hughes
19 November 2017
Project Description: This project uses a simulated clock to determine and display the start time, run time, end time, and delay of jobs. Input of all fields
are taken from a file.

A. File Layout - the file is read from left to right. The job is read first, then the start time, and lastly the run time. When information in a line runs out, the
file reader moves to the next line.
	I. Sample File:

		Job1	1	3
		Job2	2	2
		Job3	7	2
		Job4	9	3
		Job5	14	2

	II. The first number represents the starting time of the job. The second number represents the run time of the job. If there is an overlap of the start time 
	of a job and the run time of the previous job, the current job will experience a delay and wait for the previous job to finish before starting. For example, 
	job2 wants to start at time 2. However, job1 is still running at time 2, so job2 will wait until job1 is done and then start.

B. Output - The program will output like a table. The first column will display the jobs, the second column will display the arrival time of the jobs, the
third column will display the run time of the jobs, the fourth column will display the start time of the jobs, and the fifth column will display the
wait time of the jobs (if the job needed a delay time).
	I. Sample Output of File:

		Job Control Analysis: Summary Report
		------------------------------------

		Job ID		Arrival	Time	Run Time	Start Time	Wait Time	Turnaround Time
		------		------------	--------	----------	---------	---------------

		Job1		1		3		1		0		3
		Job2		2		2		4		2		4
		Job3		7		2		7		0		2
		Job4		9		3		9		0		3
		Job5		14		2		14		0		2


		Average Wait Time => 0.40
		CPU Usage => 12.00
		CPU Idle => 3.00
		CPU Usage (%) => 80.00%
		-----------------------------------------------------------------------------------------------

C. Tester Class(TimeShare) Methods
	I. fileSelect(): this method allows the user to select a file to get the job information
	II. queuePrint(): displays the file information
	III. inputQueue(): fills the queue sent into it with the information needed
	IV. jobQueue(): fills the queue sent into it with the information needed
	V. finishQueue(): fills the queue sent into it with the information needed
	VI. processQueue(): fills the queue sent into it with the information needed
	VII. clockSim(): a clock simulator for the program; starts at 1
	VIII. idleCPU(): keeps track of the time units when the CPU is idle; starts at 0; updates if the job queue is empty and input queue is not
	IX. useCPU(): keeps track of the time units when the CPU is in use; starts at 0; updates if the job queue is not empty
	X. checkInputQueue(): if the input queue is not empty, check the first record, store it in the job queue, nd remove it from the input queue if the arrival time
is equal to the clock time
	XI. checkJobQueue(): if the job queue is not empty, check the first record to see if any jobs need to be removed at the current clock time. If it has already started
and its run time is equal is equal to the clock time minus start time, remove it from the job queue. If a job is removed from the job queue, update its tutnaraound time and
place it in the finish queue. If the arrival time is less than or equal to the clock time and it hasn't been started, start the job. Set the start time equal to the clock
time and set its wait time.
