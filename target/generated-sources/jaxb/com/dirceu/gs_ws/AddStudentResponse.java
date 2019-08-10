//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.08.10 a las 11:32:07 AM BOT 
//


package com.dirceu.gs_ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="serviceStatus" type="{http://www.dirceupage.com/student-ws}serviceStatus"/&gt;
 *         &lt;element name="StudentInfo" type="{http://www.dirceupage.com/student-ws}StudentInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceStatus",
    "studentInfo"
})
@XmlRootElement(name = "addStudentResponse")
public class AddStudentResponse {

    @XmlElement(required = true)
    protected ServiceStatus serviceStatus;
    @XmlElement(name = "StudentInfo", required = true)
    protected StudentInfo studentInfo;

    /**
     * Obtiene el valor de la propiedad serviceStatus.
     * 
     * @return
     *     possible object is
     *     {@link ServiceStatus }
     *     
     */
    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    /**
     * Define el valor de la propiedad serviceStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceStatus }
     *     
     */
    public void setServiceStatus(ServiceStatus value) {
        this.serviceStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad studentInfo.
     * 
     * @return
     *     possible object is
     *     {@link StudentInfo }
     *     
     */
    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    /**
     * Define el valor de la propiedad studentInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentInfo }
     *     
     */
    public void setStudentInfo(StudentInfo value) {
        this.studentInfo = value;
    }

}
