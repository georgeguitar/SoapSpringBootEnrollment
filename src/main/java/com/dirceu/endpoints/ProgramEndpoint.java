package com.dirceu.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.dirceu.entity.Program;
import com.dirceu.gs_ws.AddProgramRequest;
import com.dirceu.gs_ws.AddProgramResponse;
import com.dirceu.gs_ws.DeleteProgramRequest;
import com.dirceu.gs_ws.DeleteProgramResponse;
import com.dirceu.gs_ws.GetAllProgramsResponse;
import com.dirceu.gs_ws.GetProgramByIdRequest;
import com.dirceu.gs_ws.GetProgramByIdResponse;
import com.dirceu.gs_ws.ProgramInfo;
import com.dirceu.gs_ws.ServiceStatusProgram;
import com.dirceu.gs_ws.UpdateProgramRequest;
import com.dirceu.gs_ws.UpdateProgramResponse;
import com.dirceu.service.IProgramService;


@Endpoint
public class ProgramEndpoint {
	private static final String NAMESPACE_URI = "http://www.dirceupage.com/program-ws";
	@Autowired
	private IProgramService ProgramService;	

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProgramByIdRequest")
	@ResponsePayload
	public GetProgramByIdResponse getProgram(@RequestPayload GetProgramByIdRequest request) {
		GetProgramByIdResponse response = new GetProgramByIdResponse();
		ProgramInfo programInfo = new ProgramInfo();
		BeanUtils.copyProperties(ProgramService.getProgramById(request.getProgramId()), programInfo);
		response.setProgramInfo(programInfo);
		return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProgramsRequest")
	@ResponsePayload
	public GetAllProgramsResponse getAllPrograms() {
		GetAllProgramsResponse response = new GetAllProgramsResponse();
		List<ProgramInfo> ProgramInfoList = new ArrayList<>();
		List<Program> programList = ProgramService.getAllPrograms();
		for (int i = 0; i < programList.size(); i++) {
			 ProgramInfo ob = new ProgramInfo();
		     BeanUtils.copyProperties(programList.get(i), ob);
		     ProgramInfoList.add(ob);    
		}
		response.getProgramInfo().addAll(ProgramInfoList);
		return response;
	}	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProgramRequest")
	@ResponsePayload
	public AddProgramResponse addProgram(@RequestPayload AddProgramRequest request) {
		AddProgramResponse response = new AddProgramResponse();		
    	ServiceStatusProgram serviceStatusProgram = new ServiceStatusProgram();
		Program program = new Program();
		program.setName(request.getName());
		program.setSemesters(request.getSemesters());
		program.setTitle(request.getTitle());
		program.setCredits(request.getCredits());
        boolean flag = ProgramService.addProgram(program);
        if (flag == false) {
        	serviceStatusProgram.setStatusCode("CONFLICT");
        	serviceStatusProgram.setMessage("Content Already Available");
        	response.setServiceStatusProgram(serviceStatusProgram);
        } else {
			ProgramInfo ProgramInfo = new ProgramInfo();
	        BeanUtils.copyProperties(program, ProgramInfo);
			response.setProgramInfo(ProgramInfo);
			serviceStatusProgram.setStatusCode("SUCCESS");
			serviceStatusProgram.setMessage("Content Added Successfully");
        	response.setServiceStatusProgram(serviceStatusProgram);
        }
        return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProgramRequest")
	@ResponsePayload
	public UpdateProgramResponse updateProgram(@RequestPayload UpdateProgramRequest request) {
		Program program = new Program();
		BeanUtils.copyProperties(request.getProgramInfo(), program);
		ProgramService.updateProgram(program);
    	ServiceStatusProgram serviceStatusProgram = new ServiceStatusProgram();
    	serviceStatusProgram.setStatusCode("SUCCESS");
    	serviceStatusProgram.setMessage("Content Updated Successfully");
    	UpdateProgramResponse response = new UpdateProgramResponse();
    	response.setServiceStatusProgram(serviceStatusProgram);
    	return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProgramRequest")
	@ResponsePayload
	public DeleteProgramResponse deleteProgram(@RequestPayload DeleteProgramRequest request) {
		Program program = ProgramService.getProgramById(request.getProgramId());
    	ServiceStatusProgram serviceStatusProgram = new ServiceStatusProgram();
		if (program == null ) {
			serviceStatusProgram.setStatusCode("FAIL");
			serviceStatusProgram.setMessage("Content Not Available");
		} else {
			ProgramService.deleteProgram(program);
			serviceStatusProgram.setStatusCode("SUCCESS");
			serviceStatusProgram.setMessage("Content Deleted Successfully");
		}
    	DeleteProgramResponse response = new DeleteProgramResponse();
    	response.setServiceStatusProgram(serviceStatusProgram);
		return response;
	}	
}
