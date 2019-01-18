package nl.sogyo.schedule_planner;

import java.util.HashMap;

public class ScheduleForWeek extends ScheduleForDay {
	
	public void getScheduleForWeek (HashMap<String, Integer> TaskTime) {
				
		ScheduleForDay scheduleForDay = new ScheduleForDay();
		
		scheduleForDay.printDay();
		
		for (int i=0; i<5; i++) {
			scheduleForDay.setDaySchedule(TaskTime);
			scheduleForDay.setTotalDuration();
			scheduleForDay.clearDaySchedule();
			scheduleForDay.setShouldIPrint(false);
		}				
		
		scheduleForDay.getTotalDuration();
		scheduleForDay.getWeekWarning();
	}
}