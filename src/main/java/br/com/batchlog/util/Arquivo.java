package br.com.batchlog.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import br.com.batchlog.dto.LogDTO;

public class Arquivo {
	
	private final String caminho = "C:\\Users\\lucas\\Documents\\access.log";
	
	public List<LogDTO> lerArquivo() {

		List<LogDTO> logDtoList = new ArrayList<>();
		try {
			Path path = Paths.get(caminho);
			Stream<String> lines = Files.lines(path);
			lines.forEach(linha -> {

				String[] dadosArquivos = linha.split("|");
				LogDTO logDto = new LogDTO();
				
//				logDto.setData(new Date(s) dadosArquivos[0]);
				logDto.setIp(dadosArquivos[1]);
				logDto.setRequest(dadosArquivos[2]);
				logDto.setStatus(Integer.parseInt(dadosArquivos[3]));
				logDto.setUserAgent(dadosArquivos[4]);


				logDtoList.add(logDto);
			});
			
			lines.close();
			

			return logDtoList;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
