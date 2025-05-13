package com.produccion.gescom.ventas;

//import org.springframework.ws.client.core.WebServiceTemplate;
//import org.springframework.ws.soap.SoapMessage;
//import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

@Controller
@CrossOrigin
@RequestMapping ("/envio")
public class SunatClient {
	//private final WebServiceTemplate webServiceTemplate;
	
	/*@Autowired
    public SunatClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }*/
	
	/*@PostMapping("/factura")
	public void sendDocument() {
        try {
        	String xmlContent = "E:/Proyectos/Empresa/facturacion/xmlfirma/signed_invoice6.xml";
            // Convertir el string XML a un InputStream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes());
            StreamSource source = new StreamSource(inputStream);

            // Preparar la respuesta
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            // URL del servicio web de SUNAT para la factura electrónica
            String endpoint = "https://e-beta.sunat.gob.pe/ol-ti-itcpfegem-beta/billService"; // Asegúrate de usar el endpoint correcto

            // Realizar la llamada
            webServiceTemplate.sendSourceAndReceiveToResult(endpoint, source, new SoapActionCallback("action"), result);

            // Procesar la respuesta
            String responseContent = writer.toString();
            System.out.println("Respuesta de SUNAT: " + responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
	
	
}
