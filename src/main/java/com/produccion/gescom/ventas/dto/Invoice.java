package com.produccion.gescom.ventas.dto;

/*import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlSchema;*/


//import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

//@XmlRootElement(name = "Invoice", namespace = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2")

//@XmlRootElement(name="Invoice",namespace="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2")
//@XmlAccessorType(XmlAccessType.FIELD)

//@XmlRootElement(name = "Invoice", namespace = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2")
//@XmlType(propOrder = {"codigo", "nombre"}) // Agrega tus elementos en el orden deseado

//@XmlRootElement(name = "Invoice", namespace = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2")
//@XmlAccessorType(XmlAccessType.FIELD)
@Setter
//@Getter
public class Invoice{

	//@XmlElement(namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2")
	//@XmlElement
    //private UBLExtensions exten;

	//@XmlElement
	private String codigo;
	
	//@XmlElement
	private String nombre;
	

	
}
