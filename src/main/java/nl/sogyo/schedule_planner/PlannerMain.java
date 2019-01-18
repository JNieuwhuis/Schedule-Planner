package nl.sogyo.schedule_planner;

import java.util.HashMap;

public class PlannerMain {

	public static final String TASKS_FILENAME = "Tasks.txt";
	public HashMap<String, Integer> TaskTime;
	
	public static void main(String[] args) {
		
		PlannerMain plannermain = new PlannerMain();
		plannermain.preparation();
		plannermain.planning(plannermain.TaskTime);	
	}
	
	public void preparation() {
		
		TaskFileProcessor taskFileProcessor = new TaskFileProcessor(PlannerMain.TASKS_FILENAME);
		this.TaskTime = taskFileProcessor.getTaskTime();	
	}
	
	public void planning(HashMap<String, Integer> TaskTime) {

		ScheduleForWeek scheduleForWeek = new ScheduleForWeek();
		scheduleForWeek.getScheduleForWeek(TaskTime);
	}

}