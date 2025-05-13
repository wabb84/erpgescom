package com.produccion.gescom.ventas.dto;

/*import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;*/
import lombok.Getter;
import lombok.Setter;

//@XmlRootElement(name = "Documento", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2")
//@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Documento {
    //@XmlElement(name = "Nombre", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2")
    private String nombre;

    // Getters y setters
}