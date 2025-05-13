package com.produccion.gescom.ventas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

///import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;

//import jakarta.xml.parsers.DocumentBuilder;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;

/*import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;*/

//import jakarta.xml.bind.JAXBContext;
//import jakarta.xml.bind.JAXBException;
//import jakarta.xml.bind.Marshaller;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.produccion.gescom.ventas.dto.Invoice;
import com.produccion.gescom.ventas.dto.UBLExtension;
import com.produccion.gescom.ventas.dto.UBLExtensions;

@Controller
@CrossOrigin
@RequestMapping ("/comprobante")
public class Comprobante {
	
	@PostMapping("/factura5")
	//public ResponseEntity<?> Factura2() throws ParserConfigurationException, TransformerException {
	public ResponseEntity<?> generateXml2()throws ParserConfigurationException, TransformerException {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.setContentDispositionFormData("filename", "example.xml");
        
        
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
     // Crear el documento XML
        Document doc = docBuilder.newDocument();
        
        doc.setXmlStandalone(true);
        
        //doc.setXmlEncoding("ISO-8859-1");
        
     // Crear el elemento raíz con el espacio de nombres deseado
        Element invoiceElement = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2", "Invoice");
        invoiceElement.setAttribute("xmlns", "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
        invoiceElement.setAttribute("xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
        invoiceElement.setAttribute("xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        invoiceElement.setAttribute("xmlns:ccts", "urn:un:unece:uncefact:documentation:2");
        invoiceElement.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
        invoiceElement.setAttribute("xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        invoiceElement.setAttribute("xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
        invoiceElement.setAttribute("xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
        invoiceElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        doc.appendChild(invoiceElement);
        
     // Agregar elementos
        Element ublExtensionsElement = doc.createElement("ext:UBLExtensions");
        invoiceElement.appendChild(ublExtensionsElement);
        
        Element ublExtensionElement = doc.createElement("ext:UBLExtension");
        ublExtensionsElement.appendChild(ublExtensionElement);
        
        Element extensionContentElement = doc.createElement("ext:ExtensionContent");
        ublExtensionElement.appendChild(extensionContentElement);
        
        
        addCBCElement(doc, invoiceElement, "UBLVersionID", "2.1");
        addCBCElement(doc, invoiceElement, "CustomizationID", "2.0");
        addCBCElement(doc, invoiceElement, "ID", "F001-1");
        addCBCElement(doc, invoiceElement, "IssueDate", "2024-03-25");
        addCBCElement(doc, invoiceElement, "IssueTime", "01:48:58");
        addCBCElement(doc, invoiceElement, "DueDate", "2024-03-25");
        
        Element invoiceTypeCodeElement = doc.createElement("cbc:InvoiceTypeCode");
        invoiceTypeCodeElement.setTextContent("01"); // Establecer el contenido de la etiqueta

        // Añadir atributos
        /*invoiceTypeCodeElement.setAttribute("listID", "0101");
        invoiceTypeCodeElement.setAttribute("listAgencyName", "PE:SUNAT");
        invoiceTypeCodeElement.setAttribute("listName", "Tipo de Documento");
        invoiceTypeCodeElement.setAttribute("listURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");
        invoiceTypeCodeElement.setAttribute("name", "Tipo de Operacion");
        invoiceTypeCodeElement.setAttribute("listSchemeURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51");*/

        // Agregar la etiqueta InvoiceTypeCode al elemento Invoice
        //invoiceElement.appendChild(invoiceTypeCodeElement);
        
        Attr listIDAttr = doc.createAttributeNS(null, "listID");
        listIDAttr.setValue("0101");
        invoiceTypeCodeElement.setAttributeNodeNS(listIDAttr);
        //invoiceElement.appendChild(invoiceTypeCodeElement);

        Attr listAgencyNameAttr = doc.createAttributeNS(null, "listAgencyName");
        listAgencyNameAttr.setValue("PE:SUNAT");
        invoiceTypeCodeElement.setAttributeNodeNS(listAgencyNameAttr);
        invoiceElement.appendChild(invoiceTypeCodeElement);

        /*Attr listNameAttr = doc.createAttributeNS(null, "listName");
        listNameAttr.setValue("Tipo de Documento");
        invoiceTypeCodeElement.setAttributeNodeNS(listNameAttr);

        Attr listURIAttr = doc.createAttributeNS(null, "listURI");
        listURIAttr.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");
        invoiceTypeCodeElement.setAttributeNodeNS(listURIAttr);

        Attr nameAttr = doc.createAttributeNS(null, "name");
        nameAttr.setValue("Tipo de Operacion");
        invoiceTypeCodeElement.setAttributeNodeNS(nameAttr);

        Attr listSchemeURIAttr = doc.createAttributeNS(null, "listSchemeURI");
        listSchemeURIAttr.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51");
        invoiceTypeCodeElement.setAttributeNodeNS(listSchemeURIAttr);*/
        
        // Agregar la etiqueta InvoiceTypeCode al elemento Invoice
        //invoiceElement.appendChild(invoiceTypeCodeElement);
        
        
        //String invoiceTypeCodeXML = "<cbc:InvoiceTypeCode listID=\"0101\" listAgencyName=\"PE:SUNAT\" listName=\"Tipo de Documento\" " +
         //       "listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01\" name=\"Tipo de Operacion\" " +
          //      "listSchemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51\">01</cbc:InvoiceTypeCode>";

        // Crear un elemento a partir del XML
        //Element invoiceTypeCodeElement = doc.createElement("Invoice");
        //invoiceTypeCodeElement.appendChild(doc.createTextNode(invoiceTypeCodeXML));

        // Obtener el primer hijo que es el elemento deseado
        //Element invoiceTypeCode = (Element) invoiceTypeCodeElement.getFirstChild();

        // Agregar la etiqueta InvoiceTypeCode al elemento Invoice
        //invoiceElement.appendChild(invoiceTypeCode);
        
        
        
        //NodeList nodes = invoiceTypeCodeElement.getChildNodes();
        /*for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeName().equals("ext:UBLExtensions")) {
            	invoiceTypeCodeElement.insertBefore(invoiceTypeCodeElement, node);
                break;
            }
        }*/
        

        doc.getDocumentElement().normalize();
        
        /*Element ublExtensionElement = doc.createElement("ext:UBLExtension");
        ublExtensionsElement.appendChild(ublExtensionElement);

        // Crear el elemento ExtensionContent
        Element extensionContentElement = doc.createElement("ext:ExtensionContent");
        ublExtensionElement.appendChild(extensionContentElement);
        
        Element cacElement2 = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2", "ext:UBLExtension");
        invoiceElement.appendChild(cacElement1);
        invoiceElement.appendChild(cacElement2);*/
        

        // Convertir el documento XML a String
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("encoding", "ISO-8859-1");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        
        
        String xmldocument = writer.toString();
        
        //return new ResponseEntity<>(transformer, headers, HttpStatus.OK);
        //return writer.toString();
        return new ResponseEntity<>(xmldocument, headers, HttpStatus.OK);
        
	}
	
	private static void addCBCElement(Document doc, Element parentElement, String tagName, String textContent) {
        Element element = doc.createElement("cbc:" + tagName);
        element.appendChild(doc.createTextNode(textContent));
        parentElement.appendChild(element);
    }
	
	
	@PostMapping("/factura")
	public ResponseEntity<?> Factura() {
		
		//Map<String, Object> response = new HashMap<>();
		
		Invoice invoice = new Invoice();
		invoice.setCodigo("00001");
		invoice.setNombre("Factura");
		UBLExtensions ublex = new UBLExtensions();
		ublex.setUbl("Prueba");
		//invoice.setExt(ublex);
		
//		JAXBContext jaxbContext;
//		try {
            /*jaxbContext = JAXBContext.newInstance(Invoice.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(invoice, new File("invoice.xml"));*/
            
	//		jaxbContext = JAXBContext.newInstance(Invoice.class);
	//		Marshaller marshaller = jaxbContext.createMarshaller();
	//		StringWriter sw = new StringWriter();
	//		marshaller.marshal(invoice, sw);
			
			//System.out.println(sw.toString());
            
            //return ResponseEntity.ok().build();
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_XML);
	        headers.setContentDispositionFormData("filename", "example.xml");
            
            return new ResponseEntity<>(invoice, headers, HttpStatus.OK);
     //   } catch (JAXBException e) {
      //      e.printStackTrace();
       //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        //}
		
		//return ResponseEntity.ok(invoice);
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
		
	}
	
	@PostMapping("/factura1")
	//public ResponseEntity<?> Factura1() {
	public ResponseEntity<String> generateXml() {
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<root>\n" +
                            "    <data>Hello, world!</data>\n" +
                            "</root>";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.setContentDispositionFormData("filename", "example.xml");
        
        return new ResponseEntity<>(xmlContent, headers, HttpStatus.OK);
    }
	
	//@PostMapping("/factura2")
	//public ResponseEntity<?> Factura1() {
	//**public ResponseEntity<?> generateXml2() {
	//	Invoice invoice = new Invoice();
	//	invoice.setCodigo("00001");
	//	invoice.setNombre("Factura");
	//	UBLExtensions ublex = new UBLExtensions();
	//	ublex.setUbl("Prueba");
		//invoice.setExten(ublex);
		
		//JAXBContext jaxbContext;
		//try {
            /*jaxbContext = JAXBContext.newInstance(Invoice.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(invoice, new File("invoice.xml"));*/
            
	//		jaxbContext = JAXBContext.newInstance(Invoice.class);
	//		Marshaller marshaller = jaxbContext.createMarshaller();
//			StringWriter sw = new StringWriter();
//			marshaller.marshal(invoice, sw);
	        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        //marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			//String xmlContent = sw.toString();
			
			//System.out.println(sw.toString());
			
		//	HttpHeaders headers = new HttpHeaders();
	     //   headers.setContentType(MediaType.APPLICATION_XML);
	      //  headers.setContentDispositionFormData("filename", "example.xml");
            
			//return new ResponseEntity<>(invoice, headers, HttpStatus.OK);
			//return new ResponseEntity<>(xmlContent, headers, HttpStatus.OK);
        //} catch (JAXBException e) {
         //   e.printStackTrace();
          //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }		
	
//}	