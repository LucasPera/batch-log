package br.com.batchlog.batchprocessing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import br.com.batchlog.dto.LogDTO;

@StepScope
@Component
public class LogItemReader implements ItemReader<LogDTO> {


	List<LogDTO> logDtoList = new ArrayList<>();
	
	private static final Logger logger = LoggerFactory.getLogger(LogItemReader.class);
	
	private Scanner entrada;
	
	public LogItemReader() {
		
		try {
			logger.info("iniciando leitura");
			entrada = new Scanner(new FileReader("access.log"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public LogDTO read() throws Exception {
		
		if(entrada.hasNext()) {
			String line = entrada.nextLine();
			String[] dadosLog = line.split("\\|");
			
			LogDTO logDTO = new LogDTO();
			
			String data = dadosLog[0];
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
			
			logDTO.setData(dateTime);
			logDTO.setIp(dadosLog[1]);
			logDTO.setRequest(dadosLog[2]);
			logDTO.setStatus(Integer.parseInt(dadosLog[3]));
			logDTO.setUserAgent(dadosLog[4]);
			
			return logDTO;
		}
		
		logger.info("encerrando");
		
		return null;
	}
	


}
