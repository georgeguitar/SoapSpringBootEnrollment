package com.dirceu.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WSConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/soapws/*");
	}
	
	@Bean(name = "students")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema studentsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("StudentsPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.dirceupage.com/student-ws");
		wsdl11Definition.setSchema(studentsSchema);
		return wsdl11Definition;
	}
	@Bean
	public XsdSchema studentsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsds/students.xsd"));
	}
	
	@Bean(name = "programs")
	public DefaultWsdl11Definition programWsdl11Definition(XsdSchema programsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ProgramPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.dirceupage.com/program-ws");
		wsdl11Definition.setSchema(programsSchema);
		return wsdl11Definition;
	}
	@Bean
	public XsdSchema programsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsds/programs.xsd"));
	}
	
	@Bean(name = "enrollments")
	public DefaultWsdl11Definition enrollmentWsdl11Definition(XsdSchema enrollmentsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("EnrollmentPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.dirceupage.com/enrollment-ws");
		wsdl11Definition.setSchema(enrollmentsSchema);
		return wsdl11Definition;
	}
	@Bean
	public XsdSchema enrollmentsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsds/enrollment.xsd"));
	}
}
