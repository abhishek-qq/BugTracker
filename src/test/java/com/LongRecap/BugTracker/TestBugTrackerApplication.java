package com.LongRecap.BugTracker;

import org.springframework.boot.SpringApplication;

public class TestBugTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.from(BugTrackerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
