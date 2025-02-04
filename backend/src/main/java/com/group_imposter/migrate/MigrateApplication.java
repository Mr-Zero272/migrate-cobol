package com.group_imposter.migrate;

import com.group_imposter.migrate.util.FieldFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MigrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MigrateApplication.class, args);
		System.out.println("Migrated Application");
	}

}
