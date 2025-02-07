package com.group_imposter.migrate;

import com.group_imposter.migrate.model.ExampleModel;
import com.group_imposter.migrate.repository.ExampleRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MigrateApplication implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job handleDalytranRecordJob;

	public static void main(String[] args) {
		SpringApplication.run(MigrateApplication.class, args);
		System.out.println("Migrated Application");
	}

	@Override
	public void run(String... args) throws Exception {
//		JobParameters jobParameters = new JobParametersBuilder()
//				.addLong("time", System.currentTimeMillis())
//				.toJobParameters();
//
//		jobLauncher.run(handleDalytranRecordJob, jobParameters);
		System.out.println("fuck");
	}
}
