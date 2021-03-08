package br.com.batchlog.batchprocessing;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.batchlog.dto.LogDTO;
import br.com.batchlog.entity.LogEntity;
import br.com.batchlog.repository.LogRepository;

@Component
public class LogItemWriter implements ItemWriter<LogDTO>{
	
	@Autowired
	private LogRepository logRepository;

	@Override
	public void write(List<? extends LogDTO> items) throws Exception {
		
		for (LogDTO logDTO : items) {
			LogEntity logEntity = new LogEntity();
			
			logEntity.setData(logDTO.getData());
			logEntity.setIp(logDTO.getIp());
			logEntity.setRequest(logDTO.getRequest());
			logEntity.setStatus(logDTO.getStatus());
			logEntity.setUserAgent(logDTO.getUserAgent());
			
			logRepository.save(logEntity);
		}
		
		
	}

}
