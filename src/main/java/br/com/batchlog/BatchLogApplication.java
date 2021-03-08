package br.com.batchlog;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BatchLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchLogApplication.class, args);
		System.exit(SpringApplication.exit(SpringApplication.run(BatchLogApplication.class, args)));
	}

}
