package nl.sogyo.schedule_planner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TaskFileProcessor {
	
	private String task;
	private Integer time;
	private List<Integer> timevalues = new ArrayList<Integer>();
	private List<String> taskkeys = new ArrayList<String>();
	public HashMap<String, Integer> TaskTime = new HashMap<String, Integer>();

	public TaskFileProcessor(String filename) {
		
		try {
			this.processFile(this.getFile(filename));
		} catch (IOException ioe) {
			System.out.println("Error reading file '" + filename + "'!");
		}		
	}
	
	private File getFile(String filename) {
		
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());
		
		return file;
	}
	
	private void processFile(File file) throws IOException {
		
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			this.timevalues.add(getTimeFromLine(line));
			this.taskkeys.add(getTaskFromLine(line));
		} 
		scanner.close();
	}
	
	public HashMap<String, Integer> getTaskTime() {
		
		for (int i = 0; i < timevalues.size(); i++) {
			this.TaskTime.put(taskkeys.get(i), timevalues.get(i));
		}//System.out.println(TaskTime.keySet());

		return this.TaskTime;
	}
		
	private String getTaskFromLine(String line) {
		
		this.task = line.substring(0, line.indexOf('-')).trim();
		this.task = (this.task.substring(0,1).toUpperCase() + this.task.substring(1).toLowerCase());
		
		return this.task;
	}

	private Integer getTimeFromLine(String line) {
		
		String timeString = line.substring(line.indexOf('-')+2).trim();
		this.time = Integer.parseInt(timeString);
		
		return (this.time);
	}	
}