package com.dirceu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dirceu.entity.Program;
import com.dirceu.repository.ProgramRepository;
@Service
public class ProgramService implements IProgramService {
	@Autowired
	private ProgramRepository ProgramRepository;
	
	@Override
	public Program getProgramById(long programId) {
		Program obj = ProgramRepository.findByProgramId(programId);
		return obj;
	}	
	@Override
	public List<Program> getAllPrograms(){
		List<Program> list = new ArrayList<>();
		ProgramRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addProgram(Program program){
	   List<Program> list = ProgramRepository.findByNameAndTitle(program.getName(), program.getTitle());
       if (list.size() > 0) {
    	   return false;
       } else {
    	   program = ProgramRepository.save(program);
    	   return true;
       }
	}
	@Override
	public void updateProgram(Program program) {
		ProgramRepository.save(program);
	}
	@Override
	public void deleteProgram(Program program) {
		ProgramRepository.delete(program);
	}
}
