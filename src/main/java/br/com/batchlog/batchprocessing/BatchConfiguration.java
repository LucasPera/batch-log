package br.com.batchlog.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import br.com.batchlog.dto.LogDTO;
import lombok.SneakyThrows;

@Configuration
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	LogItemWriter writer;
	
	@Autowired
	LogItemProcessor processor;
	
	@Autowired
	LogItemReader reader;
	
	@Bean
	public Job job(final Step step1) {
	  return jobBuilderFactory.get("job")
	   .incrementer(new RunIdIncrementer())
	   .flow(step1)
	   .end()
	   .build();
	}

	@Bean
	public Step step1(final TaskExecutor executor) {
	  return stepBuilderFactory.get("step1")
	    .<LogDTO, LogDTO> chunk(1)
	    .reader(reader) 
	    .processor(processor)
	    .writer(writer)
	    .taskExecutor(executor)
	    .build();
	}
	
	@Bean
	public TaskExecutor executor () {
	  final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	  taskExecutor.setCorePoolSize(20);
	  return taskExecutor;
	}
	
	
}
