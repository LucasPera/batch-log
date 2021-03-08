package br.com.batchlog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.batchlog.dto.LogDTO;
import br.com.batchlog.entity.LogEntity;

@Repository
public interface LogRepository extends CrudRepository<LogEntity, Long>  {

}
