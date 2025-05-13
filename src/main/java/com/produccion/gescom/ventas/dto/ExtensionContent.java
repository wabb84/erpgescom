package com.produccion.gescom.ventas.dto;

/*import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;*/
import lombok.Getter;
import lombok.Setter;

//@XmlRootElement(name = "ExtensionContent", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2")
//@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class ExtensionContent {
    //@XmlElement(name = "DatosAdicionales", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2")
    private DatosAdicionales datosAdicionales;

    // Getters y setters
}