package com.dirceu.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.dirceu.entity.Enrollment;
import com.dirceu.gs_ws.AddEnrollmentRequest;
import com.dirceu.gs_ws.AddEnrollmentResponse;
import com.dirceu.gs_ws.DeleteEnrollmentRequest;
import com.dirceu.gs_ws.DeleteEnrollmentResponse;
import com.dirceu.gs_ws.EnrollmentInfo;
import com.dirceu.gs_ws.GetAllEnrollmentsResponse;
import com.dirceu.gs_ws.GetEnrollmentByIdRequest;
import com.dirceu.gs_ws.GetEnrollmentByIdResponse;
import com.dirceu.gs_ws.ServiceStatusEnrollment;
import com.dirceu.gs_ws.UpdateEnrollmentRequest;
import com.dirceu.gs_ws.UpdateEnrollmentResponse;
import com.dirceu.service.IEnrollmentService;


@Endpoint
public class EnrollmentEndpoint {
	private static final String NAMESPACE_URI = "http://www.dirceupage.com/enrollment-ws";
	@Autowired
	private IEnrollmentService enrollmentService;	

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEnrollmentByIdRequest")
	@ResponsePayload
	public GetEnrollmentByIdResponse getenrollment(@RequestPayload GetEnrollmentByIdRequest request) {
		GetEnrollmentByIdResponse response = new GetEnrollmentByIdResponse();
		EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
		BeanUtils.copyProperties(enrollmentService.getEnrollmentById(request.getEnrollmentId()), enrollmentInfo);
		response.setEnrollmentInfo(enrollmentInfo);
		return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEnrollmentsRequest")
	@ResponsePayload
	public GetAllEnrollmentsResponse getAllenrollments() {
		GetAllEnrollmentsResponse response = new GetAllEnrollmentsResponse();
		List<EnrollmentInfo> enrollmentInfoList = new ArrayList<>();
		List<Enrollment> enrollmentList = enrollmentService.getAllEnrollments();
		for (int i = 0; i < enrollmentList.size(); i++) {
			 EnrollmentInfo ob = new EnrollmentInfo();
		     BeanUtils.copyProperties(enrollmentList.get(i), ob);
		     enrollmentInfoList.add(ob);    
		}
		response.getEnrollmentInfo().addAll(enrollmentInfoList);
		return response;
	}	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEnrollmentRequest")
	@ResponsePayload
	public AddEnrollmentResponse addenrollment(@RequestPayload AddEnrollmentRequest request) {
		AddEnrollmentResponse response = new AddEnrollmentResponse();		
		ServiceStatusEnrollment serviceStatus = new ServiceStatusEnrollment();		
		Enrollment enrollment = new Enrollment();
		enrollment.setYear(request.getYear());
		enrollment.setPeriod(request.getPeriod());
		enrollment.setLevel(request.getLevel());
		enrollment.setDateEnrollment(request.getDateEnrollment());
		enrollment.setStudentId(request.getStudentId());
		enrollment.setProgramId(request.getProgramId());
        boolean flag = enrollmentService.addEnrollment(enrollment);
        if (flag == false) {
        	serviceStatus.setStatusCode("CONFLICT");
        	serviceStatus.setMessage("Content Already Available");
        	response.setServiceStatusEnrollment(serviceStatus);
        } else {
			EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
	        BeanUtils.copyProperties(enrollment, enrollmentInfo);
			response.setEnrollmentInfo(enrollmentInfo);
        	serviceStatus.setStatusCode("SUCCESS");
        	serviceStatus.setMessage("Content Added Successfully");
        	response.setServiceStatusEnrollment(serviceStatus);
        }
        return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEnrollmentRequest")
	@ResponsePayload
	public UpdateEnrollmentResponse updateenrollment(@RequestPayload UpdateEnrollmentRequest request) {
		Enrollment enrollment = new Enrollment();
		BeanUtils.copyProperties(request.getEnrollmentInfo(), enrollment);
		enrollmentService.updateEnrollment(enrollment);
    	ServiceStatusEnrollment serviceStatusEnrollment = new ServiceStatusEnrollment();
    	serviceStatusEnrollment.setStatusCode("SUCCESS");
    	serviceStatusEnrollment.setMessage("Content Updated Successfully");
    	UpdateEnrollmentResponse response = new UpdateEnrollmentResponse();
    	response.setServiceStatusEnrollment(serviceStatusEnrollment);
    	return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEnrollmentRequest")
	@ResponsePayload
	public DeleteEnrollmentResponse deleteenrollment(@RequestPayload DeleteEnrollmentRequest request) {
		Enrollment enrollment = enrollmentService.getEnrollmentById(request.getEnrollmentId());
    	ServiceStatusEnrollment serviceStatusEnrollment = new ServiceStatusEnrollment();
		if (enrollment == null ) {
			serviceStatusEnrollment.setStatusCode("FAIL");
			serviceStatusEnrollment.setMessage("Content Not Available");
		} else {
			enrollmentService.deleteEnrollment(enrollment);
			serviceStatusEnrollment.setStatusCode("SUCCESS");
			serviceStatusEnrollment.setMessage("Content Deleted Successfully");
		}
    	DeleteEnrollmentResponse response = new DeleteEnrollmentResponse();
    	response.setServiceStatusEnrollment(serviceStatusEnrollment);
		return response;
	}	
}
