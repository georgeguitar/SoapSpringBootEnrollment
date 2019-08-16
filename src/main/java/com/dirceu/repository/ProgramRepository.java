package com.dirceu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dirceu.entity.Program;

public interface ProgramRepository extends CrudRepository<Program, Long>  {
	Program findByProgramId(long programId);
    List<Program> findByNameAndTitle(String name, String title);
}
