package br.com.batchlog.batchprocessing;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.batchlog.dto.LogDTO;

@StepScope
@Component
public class LogItemProcessor implements ItemProcessor<LogDTO, LogDTO> {

	@Override
	public LogDTO process(LogDTO item) throws Exception {

		if (item == null) {
			return null;
		}

		return item;
	}

}
