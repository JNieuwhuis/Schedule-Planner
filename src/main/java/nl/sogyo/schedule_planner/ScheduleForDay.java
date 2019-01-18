package nl.sogyo.schedule_planner;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ScheduleForDay {

	private String randKey;
	private Integer randValue;
	private boolean shouldIPrint =true;
	private Integer outline;
	private HashMap<String, Integer> DaySchedule = new HashMap<String, Integer>();
	private boolean isScheduleFull;
	private Integer scheduleDuration;
	private String day;
	private Integer totalScheduleDuration =0;
	
	public ScheduleForDay() {
	}	
	
	public void printDay() {
		this.getDayOfWeek();
		System.out.println("This is the schedule for: " + this.day);
	}
	
	private void setRandomKey(HashMap<String, Integer> TaskTime) {
		
		Object[] crunchifyKeys = TaskTime.keySet().toArray();
		this.randKey = String.valueOf(crunchifyKeys[new Random().nextInt(crunchifyKeys.length)]);	
	}
	
	private Object getRandomKey() {		
		
		if (shouldIPrint == true) {
			System.out.format("%n" + this.randKey);	
		}		
		return this.randKey;
	}
	
	private Integer getOutline() {
		
		outline = (40 -(randKey.toString()).length());
		
		return outline;
	}
	
	private Integer setRandomValuetoKey(HashMap<String, Integer> TaskTime) {
		
		this.randValue = TaskTime.get(this.randKey);
		if (shouldIPrint == true) {	
			this.getOutline();
			String randValue2 = String.format("%"+outline+"d", (this.randValue));
			System.out.print(randValue2 + " minutes");
		}		
		return this.randValue;
	}
	
	private Integer getCheckIfScheduleFull(HashMap<String, Integer> DaySchedule) {
		
		this.scheduleDuration = 0;
		
		for (Object key: (DaySchedule.keySet())) {
			Integer scheduleValue = DaySchedule.get(key);
			this.scheduleDuration = this.scheduleDuration + scheduleValue;
		}
			
		if (this.scheduleDuration >= (8*60)) {
			this.isScheduleFull = true;
			if (this.scheduleDuration >= (9*60) && (shouldIPrint == true)) {
				System.out.format("%n%nYou're going into overtime for today!");
			}	
		}
		else if (this.scheduleDuration < (8*60)) {
			this.isScheduleFull = false;
		}			
		return this.scheduleDuration;
		
	}
	
	private HashMap<String, Integer> getDaySchedule(HashMap<String, Integer> TaskTime) {
		
		this.setRandomKey(TaskTime);
		this.getRandomKey();
		this.setRandomValuetoKey(TaskTime);
		
		this.DaySchedule = new HashMap<String, Integer>(); 
		
		do {
			this.setRandomKey(TaskTime);
			this.getRandomKey();
			this.setRandomValuetoKey(TaskTime);
			DaySchedule.put(this.randKey, this.randValue);
			this.getCheckIfScheduleFull(DaySchedule);
			
		} while (isScheduleFull == false); 		
		
		return this.DaySchedule;
	}
	
	public void setDaySchedule(HashMap<String, Integer> TaskTime) {
		
		this.getDaySchedule(TaskTime);
		
		Integer scheduleDurationHours = (scheduleDuration/60);
		Integer scheduleDurationMinutes = (scheduleDuration%60);
		
		if (shouldIPrint == true) {
			System.out.format("%n%n" +day +"'s schedule contains " + scheduleDurationHours +" hours and " + scheduleDurationMinutes +" minutes.");
		}
		
	}
	
	private String getDayOfWeek() {
		
		Integer dayNum = ThreadLocalRandom.current().nextInt(1, 6);
		
		if (dayNum == 1) {
			this.day = "Monday";
		}
		else if (dayNum == 2) {
			this.day = "Tuesday";
		}
		else if (dayNum == 3) {
			this.day = "Wednesday";
		}
		else if (dayNum == 4) {
			this.day = "Thursday";
		}
		else if (dayNum == 5) {
			this.day = "Friday";
		}		
		return this.day;
	}
	
	public void clearDaySchedule() {
		this.DaySchedule.clear();
	}
	
	private void calculateTotalDuration() {
		
		this.totalScheduleDuration = this.totalScheduleDuration + this.scheduleDuration;
	}
	
	public int setTotalDuration() {
		this.calculateTotalDuration();
		return this.totalScheduleDuration;
	}
	
	public void getTotalDuration() {
		
		Integer totalDurationHours = (this.totalScheduleDuration/60);
		Integer totalDurationMinutes = (this.totalScheduleDuration%60);
	
		System.out.format("%n%nYou have " +totalDurationHours + " hours and " +totalDurationMinutes +" minutes planned for this week.");
	}
	
	public void getWeekWarning() {
		
		if (this.totalScheduleDuration >= (40*60)) {
			System.out.format("%n%nYou know it's not healthy to work more than 40 hours per week!");
		} // Honestly, it's completely unrealistic to set a limit for 40 hours, when the upper limit warning for a day is at 9 hours.
	}

	public void setShouldIPrint(boolean shouldIPrint) {
		this.shouldIPrint = shouldIPrint;
	}
	
}