package com.dirceu.service;

import java.util.List;

import com.dirceu.entity.Program;

public interface IProgramService {
     List<Program> getAllPrograms();
     Program getProgramById(long programId);
     boolean addProgram(Program program);
     void updateProgram(Program program);
     void deleteProgram(Program program);
}
