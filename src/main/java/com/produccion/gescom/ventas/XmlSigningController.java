package com.produccion.gescom.ventas;


import org.apache.xml.security.Init;
//import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
//import org.bouncycastle.openssl.PEMParser;
//import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.transforms.TransformationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jakarta.persistence.criteria.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;



@Controller
@CrossOrigin
@RequestMapping ("/firma")
public class XmlSigningController {
	 private static final String INPUT_XML_FOLDER = "E:/Proyectos/Empresa/facturacion/xml/";
	 private static final String OUTPUT_XML_FOLDER = "E:/Proyectos/Empresa/facturacion/xmlfirma/";
	 
	 @PostMapping("/signxml")
	 //public String signXml() {
	 public ResponseEntity<?> signXml() {
		Map<String, Object> response = new HashMap<>();
	    try {
	    	// Load private key from PEM file
            //PrivateKey privateKey = loadPrivateKeyFromPem("E:/Proyectos/Empresa/facturacion/certificado/server_key.pem");
            //response.put("privateKey", privateKey.toString());

            // Load certificate from PEM file
            X509Certificate certificate = loadCertificateFromPem("E:/Proyectos/Empresa/facturacion/certificado/server.pem");
            //response.put("certificate", certificate.toString());
	    	
            // Load XML file
            //
            //Document document = loadXml(Paths.get(INPUT_XML_FOLDER, "20604051984-03-B001-19359.xml"));
            
            // Sign the XML document
            //signXmlDocument(document, privateKey, certificate);
            
            // Save signed XML to file
            //saveXml(document, Paths.get(OUTPUT_XML_FOLDER, "signed.xml"));
            
            response.put("Archivo Firmado", "Archivo Firmado");
	
	    	
	    	//return ResponseEntity.ok(privateKey.toString());
            return ResponseEntity.ok(response);
	    	
	        } catch (Exception e) {
	            e.printStackTrace();
	            //return "Error signing XML: " + e.getMessage();
	            return ResponseEntity.ok(response);
	        }
	    }
	 
	 /*private static PrivateKey loadPrivateKeyFromPem(String filename) throws Exception {
	        try (FileReader fileReader = new FileReader(filename);
	             BufferedReader reader = new BufferedReader(fileReader);
	             //PEMParser pemParser = new PEMParser(reader)) {

	            Object obj = pemParser.readObject();
	            //JcaPEMKeyConverter converter = new JcaPEMKeyConverter();

	           /* if (obj instanceof PrivateKeyInfo) {
	                return converter.getPrivateKey((PrivateKeyInfo) obj);
	            } else {
	                throw new IllegalArgumentException("Not a private key file");
	            }
	        }
	    }*/
	 
	 private static X509Certificate loadCertificateFromPem(String filename) throws Exception {
	        try (FileInputStream fis = new FileInputStream(filename);
	             BufferedInputStream bis = new BufferedInputStream(fis)) {

	            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
	            Certificate certificate = certificateFactory.generateCertificate(bis);

	            if (certificate instanceof X509Certificate) {
	                return (X509Certificate) certificate;
	            } else {
	                throw new IllegalArgumentException("Not an X.509 certificate file");
	            }
	        }
	    }
	 
	 /*private static Document loadXml(Path xmlFile) throws Exception {
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        dbf.setNamespaceAware(true);
	        DocumentBuilder builder = dbf.newDocumentBuilder();
	        return builder.parse(xmlFile.toFile());
	    }*/

	 /*private static void signXmlDocument(Document document, PrivateKey privateKey, X509Certificate certificate) throws Exception {
	        // Create a DOM XMLSignatureFactory that will be used to generate the signature
	        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");

	        // Create a reference to the root element of the XML document
	        Element rootElement = document.getDocumentElement();

	        // Create a reference to the child nodes of the root element to be signed
	        NodeList nodeList = rootElement.getChildNodes();

	        // Create a reference to the parent node of the child nodes to be signed
	        //Node parentNode = rootElement.getParentNode();
	        Node parentNode = (Node) rootElement.getParentNode();
	        // Create a reference to the transforms to be applied to the signed data
	        List<Transform> transforms = Collections.singletonList(signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));

	        // Create a reference to the data to be signed
	        Reference reference = signatureFactory.newReference("", signatureFactory.newDigestMethod(DigestMethod.SHA256, null), transforms, null, null);

	        // Create a reference to the signed data
	        SignedInfo signedInfo = signatureFactory.newSignedInfo(signatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), signatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA256, null), Collections.singletonList(reference));

	        // Create a reference to the signing key and certificate
	        KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
	        X509Data x509Data = keyInfoFactory.newX509Data(Collections.singletonList(certificate));
	        KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));

	        // Create a reference to the signing context
	        DOMSignContext signContext = new DOMSignContext(privateKey, (org.w3c.dom.Node) parentNode);

	        // Create a reference to the XMLSignature
	        //REPONER
	        //XMLSignature signature = signatureFactory.newXMLSignature(signedInfo, keyInfo);

	        // Sign the XML document
	        //REPONER
	        //signature.sign(signContext);
	    }
	 */
	 	/*private static void saveXml(Document document, Path outputFile) throws Exception {
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	        try (FileOutputStream fos = new FileOutputStream(outputFile.toFile());
	             OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
	             BufferedWriter writer = new BufferedWriter(osw)) {

	            transformer.transform(new DOMSource(document), new StreamResult(writer));
	        }
	    }*/
	 	
	 	
	 	/*@PostMapping("/signxml2")
		 //public String signXml() {
		 public ResponseEntity<?> signXml2() throws Exception {
			Map<String, Object> response = new HashMap<>();
			// Load private key from file
	        PrivateKey privateKey = loadPrivateKey("E:/Proyectos/Empresa/facturacion/certificado/server_key.pem/");
	        // Load certificate from file
	        X509Certificate certificate = loadCertificate("E:/Proyectos/Empresa/facturacion/certificado/server.pem");
	        // Load XML document
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = dbf.newDocumentBuilder();
	        Document doc = builder.parse(new File("E:/Proyectos/Empresa/facturacion/xml/20604051984-03-B001-19359.xml"));
	        
	        // Create a DOM XMLSignatureFactory that will be used to generate the signature
	        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
	        
	        // Create a reference to the root element of the XML document
	        Element rootElement = doc.getDocumentElement();
	        
	        // Create a reference to the parent node of the child nodes to be signed (usually the root element)
	        //Node parentNode = rootElement;
	        Node parentNode = rootElement;
	      
	        // Create a reference to the transforms to be applied to the signed data
	        Transform transform = signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
	        List<Transform> transforms = Collections.singletonList(transform);
	      
	        // Create a reference to the data to be signed
	        Reference reference = signatureFactory.newReference("", signatureFactory.newDigestMethod(DigestMethod.SHA256, null), transforms, null, null);
	        
	        // Create a reference to the signed data
	        SignedInfo signedInfo = signatureFactory.newSignedInfo(signatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), signatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA256, null), Collections.singletonList(reference));
	        
	        // Create a reference to the signing key and certificate
	        KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
	        X509Data x509Data = keyInfoFactory.newX509Data(Collections.singletonList(certificate));
	        KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
	        
	        // Create a reference to the signing context
	        //DOMSignContext signContext = new DOMSignContext(privateKey, parentNode);
	        DOMSignContext signContext = new DOMSignContext(privateKey, parentNode);
	        
	        // Create a reference to the XMLSignature
	        //REPONER
	        //XMLSignature signature = signatureFactory.newXMLSignature(signedInfo, keyInfo);
	        
	     // Sign the XML document
	        //REPONER
	        //signature.sign(signContext);

	        // Serialize the signed XML document
	        OutputStream os = new FileOutputStream("E:/Proyectos/Empresa/facturacion/xmlfirma/signed_invoice.xml");
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.transform(new DOMSource(doc), new StreamResult(os));
			
			return ResponseEntity.ok(response);
	 	}
	 	*/
	 	/*private static PrivateKey loadPrivateKey(String filename) throws Exception {
	        try (FileReader fileReader = new FileReader(filename);
	             BufferedReader reader = new BufferedReader(fileReader);
	             //PEMParser pemParser = new PEMParser(reader)) {

	            Object obj = pemParser.readObject();
	            //JcaPEMKeyConverter converter = new JcaPEMKeyConverter();

	            if (obj instanceof PrivateKeyInfo) {
	                return converter.getPrivateKey((PrivateKeyInfo) obj);
	            } else {
	                throw new IllegalArgumentException("Not a private key file");
	            }
	        }
	    }*/
	 	
	 	private static X509Certificate loadCertificate(String filename) throws Exception {
	        try (FileInputStream fis = new FileInputStream(filename);
	             BufferedInputStream bis = new BufferedInputStream(fis)) {

	            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
	            Certificate certificate = certificateFactory.generateCertificate(bis);

	            if (certificate instanceof X509Certificate) {
	                return (X509Certificate) certificate;
	            } else {
	                throw new IllegalArgumentException("Not an X.509 certificate file");
	            }
	        }
	    }
	 	
	 	
	 	@PostMapping("/signxml3")
		 //public String signXml() {
		public ResponseEntity<?> signXml3() throws Exception {
	 		Map<String, Object> response = new HashMap<>();	
	 	// Load private key from file
	        //PrivateKey privateKey = loadPrivateKey("E:/Proyectos/Empresa/facturacion/certificado/server_key.pem/");

	        // Load certificate from file
	        X509Certificate certificate = loadCertificate("E:/Proyectos/Empresa/facturacion/certificado/server.pem");

	        // Load XML document
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = dbf.newDocumentBuilder();
	       // Document doc = builder.parse(new File("E:/Proyectos/Empresa/facturacion/xml/20604051984-03-B001-19359.xml"));
	        
	        // Obtain the <ext:ExtensionContent> node
	       // NodeList extContentList = doc.getElementsByTagName("ext:ExtensionContent");
	        //Element extContentNode = (Element) extContentList.item(0);

	        // Create a DOM XMLSignatureFactory that will be used to generate the signature
	        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");

	        // Create the elements for the signature structure
	        //REPONER
	        //Element signatureElement = doc.createElementNS(XMLSignature.XMLNS, "ds:Signature");
	        //Element signedInfoElement = doc.createElementNS(XMLSignature.XMLNS, "ds:SignedInfo");
	        //Element signatureValueElement = doc.createElementNS(XMLSignature.XMLNS, "ds:SignatureValue");
	        //Element keyInfoElement = doc.createElementNS(XMLSignature.XMLNS, "ds:KeyInfo");
	        //Element x509DataElement = doc.createElementNS(XMLSignature.XMLNS, "ds:X509Data");
	        //Element x509CertificateElement = doc.createElementNS(XMLSignature.XMLNS, "ds:X509Certificate");

	     // Set the ID attribute for the signature element
	        //signatureElement.setAttribute("ext", "ExtensionContent");
	        //REPONER
	        //signatureElement.setAttribute("Id", "FacturacionIntegralSign");

	        // Create a reference to the root element of the XML document
	       // Element rootElement = doc.getDocumentElement();

	        // Create a reference to the parent node of the child nodes to be signed (usually the root element)
	        //Node parentNode = rootElement;

	        // Create a reference to the transforms to be applied to the signed data
	        //Transform transform = signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
	        //List<Transform> transforms = Collections.singletonList(transform);

	        // Create a reference to the data to be signed
	        //Reference reference = signatureFactory.newReference("", signatureFactory.newDigestMethod(DigestMethod.SHA1, null), transforms, null, null);

	        // Create a reference to the signed data
	        //SignedInfo signedInfo = signatureFactory.newSignedInfo(signatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), signatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(reference));

	        // Set the signed info element
	        //signedInfoElement.appendChild(signedInfo.getElement());
	        //signatureElement.appendChild(signedInfoElement);

	        // Append the signed info element as a child of the signature element
	        //signatureElement.appendChild(signedInfoElement);

	        // Sign the XML document
	        //DOMSignContext signContext = new DOMSignContext(privateKey, parentNode, signatureElement);
	        //XMLSignature signature = signatureFactory.newXMLSignature(signedInfo, null);
	        //signature.sign(signContext);

	        // Set the signature value element
	        //signatureValueElement.setTextContent(Base64.getEncoder().encodeToString(signature.getSignatureValue().getValue()));

	        // Set the key info element
	        //x509CertificateElement.setTextContent(Base64.getEncoder().encodeToString(certificate.getEncoded()));
	        //x509DataElement.appendChild(x509CertificateElement);
	        //keyInfoElement.appendChild(x509DataElement);

	        // Append the key info element as a child of the signature element
	        //signatureElement.appendChild(keyInfoElement);
	        
	        

	        // Serialize the signed XML document
	        //OutputStream os = new FileOutputStream("E:/Proyectos/Empresa/facturacion/xmlfirma/signed_invoice.xml");
	        //TransformerFactory tf = TransformerFactory.newInstance();
	        //Transformer transformer = tf.newTransformer();
	        //transformer.transform(new DOMSource(doc), new StreamResult(os));
	 		
	 		
	 		return ResponseEntity.ok(response);
	 	}
	 	
	 	static {
	        Init.init();
	    }
	 	
	 	@PostMapping("/signxml4") // Firmado cod ds:
		 //public String signXml() {
		public ResponseEntity<?> signXml4() throws Exception {
	 		Map<String, Object> response = new HashMap<>();
	 		// Cargar el documento XML
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        dbf.setNamespaceAware(true);
	        DocumentBuilder builder = dbf.newDocumentBuilder();
	        Document doc = builder.parse(new FileInputStream("E:/Proyectos/Empresa/facturacion/xml/20604051984-03-B001-19359.xml"));
	        
	     // Cargar la clave privada y certificado desde el keystore
	        KeyStore keyStore = KeyStore.getInstance("PKCS12");
	        try (FileInputStream fis = new FileInputStream("E:/Proyectos/Empresa/facturacion/certificado/DEMO-20513981491.pfx")) {
	            keyStore.load(fis, "1234567890".toCharArray());
	        }
	        String alias = keyStore.aliases().nextElement();
	        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, "1234567890".toCharArray());
	        Certificate cert = keyStore.getCertificate(alias);
	        //PublicKey publicKey = cert.getPublicKey();
	        
	        // Crear la firma XML
	        //XMLSignature sig = new XMLSignature(doc, "", XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256);
	        //sig.addResourceResolver(new org.apache.xml.security.samples.utils.resolver.OfflineResolver());
	        org.apache.xml.security.signature.XMLSignature sig = new org.apache.xml.security.signature.XMLSignature(doc, "", org.apache.xml.security.signature.XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256);

	        Transforms transforms = new Transforms(doc);
	        transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
	        //sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA256);
	        sig.addDocument("", transforms, "http://www.w3.org/2001/04/xmlenc#sha256");
	        
	     // Agregar la clave pública (certificado) a la firma
	        //sig.addKeyInfo(cert);
	        //sig.addKeyInfo(cert.getPublicKey());
	        
	        
	        //Certificate cert = keyStore.getCertificate(alias);

	        // Asegúrate de que 'cert' es realmente un X509Certificate antes de hacer el casting
	        if (cert instanceof X509Certificate) {
	        	X509Certificate x509Cert = (X509Certificate) cert;
	        	sig.addKeyInfo(x509Cert);
	        } else {
	        	throw new IllegalArgumentException("El certificado no es un X509Certificate");
	        }
	        
	        // Anexar la firma al documento XML
	        Element root = doc.getDocumentElement();
	        root.appendChild(sig.getElement());
	        
	        // Firmar el documento
	        sig.sign(privateKey);
	        
	     // Guardar el documento firmado
	        try (FileOutputStream fos = new FileOutputStream("E:/Proyectos/Empresa/facturacion/xmlfirma/signed_invoice3.xml")) {
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer trans = tf.newTransformer();
	            trans.transform(new DOMSource(doc), new StreamResult(fos));
	        }
	        
	 		return ResponseEntity.ok(response);
	 		
	 	}
	 	
	 	@PostMapping("/signxml5") // Firmado cod ds:
		 //public String signXml() {
		public ResponseEntity<?> signXml5() throws Exception {
	 		Map<String, Object> response = new HashMap<>();
	 		// Cargar el documento XML
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        dbf.setNamespaceAware(true);
	        DocumentBuilder builder = dbf.newDocumentBuilder();
	        Document doc = builder.parse(new FileInputStream("E:/Proyectos/Empresa/facturacion/xml/20604051984-03-B001-19359.xml"));
	        
	        // Buscar o crear el nodo <ext:ExtensionContent> donde insertar la firma
	        NodeList extList = doc.getElementsByTagNameNS("*", "ExtensionContent");
	        Element extensionContent;
	        
	        //System.out.println(extList.getLength());
	        if (extList.getLength() > 0) {
	        	
	        	
	            // Usa el primer <ext:ExtensionContent> encontrado
	            extensionContent = (Element) extList.item(0);
	        } else {
	            // Si no existe, crea la estructura necesaria
	            Element ublExtensions = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2", "ext:UBLExtensions");
	            Element ublExtension = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2", "ext:UBLExtension");
	            extensionContent = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2", "ext:ExtensionContent");

	            ublExtension.appendChild(extensionContent);
	            ublExtensions.appendChild(ublExtension);
	            // Asumiendo que quieres insertar <ext:UBLExtensions> como el primer elemento del documento
	            Node firstChild = doc.getDocumentElement().getFirstChild();
	            doc.getDocumentElement().insertBefore(ublExtensions, firstChild);
	        }
	        
	        // Cargar la clave privada y certificado desde el keystore
	        KeyStore keyStore = KeyStore.getInstance("PKCS12");
	        try (FileInputStream fis = new FileInputStream("E:/Proyectos/Empresa/facturacion/certificado/DEMO-20513981491.pfx")) {
	            keyStore.load(fis, "1234567890".toCharArray());
	        }
	        String alias = keyStore.aliases().nextElement();
	        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, "1234567890".toCharArray());
	        Certificate cert = keyStore.getCertificate(alias);
	       // PublicKey publicKey = cert.getPublicKey();
	        
	        // Crear la firma XML
	        //XMLSignature sig = new XMLSignature(doc, "", XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256);
	        //sig.addResourceResolver(new org.apache.xml.security.samples.utils.resolver.OfflineResolver());
	        org.apache.xml.security.signature.XMLSignature sig = new org.apache.xml.security.signature.XMLSignature(doc, "", org.apache.xml.security.signature.XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256);

	        Transforms transforms = new Transforms(doc);
	        transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
	        //sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA256);
	        sig.addDocument("", transforms, "http://www.w3.org/2001/04/xmlenc#sha256");
	        
	     // Agregar la clave pública (certificado) a la firma
	        //sig.addKeyInfo(cert);
	        //sig.addKeyInfo(cert.getPublicKey());
	        
	        
	        //Certificate cert = keyStore.getCertificate(alias);

	        // Asegúrate de que 'cert' es realmente un X509Certificate antes de hacer el casting
	        if (cert instanceof X509Certificate) {
	        	X509Certificate x509Cert = (X509Certificate) cert;
	        	sig.addKeyInfo(x509Cert);
	        } else {
	        	throw new IllegalArgumentException("El certificado no es un X509Certificate");
	        }
	        
	        // Anexar la firma al documento XML
	        Element root = doc.getDocumentElement();
	        root.appendChild(sig.getElement());
	        
	        // Firmar el documento
	        sig.sign(privateKey);
	        
		     // Configurar el contexto de la firma para insertarla en <ext:ExtensionContent>
	     // Configurar el contexto de la firma para insertarla en <ext:ExtensionContent>
	        DOMSignContext signContext = new DOMSignContext(privateKey, extensionContent);
	        
	     // Guardar el documento firmado
	        try (FileOutputStream fos = new FileOutputStream("E:/Proyectos/Empresa/facturacion/xmlfirma/signed_invoice5.xml")) {
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer trans = tf.newTransformer();
	            trans.transform(new DOMSource(doc), new StreamResult(fos));
	        }
	        
	 		return ResponseEntity.ok(response);
	 		
	 	}
	 	
	 	@PostMapping("/signxml6") // Firmado cod ds:
		 //public String signXml() {
		public ResponseEntity<?> signXml6() throws Exception {
	 		Map<String, Object> response = new HashMap<>();
	 		
	 	// Cargar el documento XML
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        dbf.setNamespaceAware(true);
	        DocumentBuilder builder = dbf.newDocumentBuilder();
	        Document doc = builder.parse(new FileInputStream("E:/Proyectos/Empresa/facturacion/xml/20604051984-03-B001-19359.xml"));

	        // Buscar o crear <ext:ExtensionContent>
	        //NodeList extList = doc.getElementsByTagNameNS("*", "c");
	        //Element extensionContent;
	        //if (extList.getLength() > 0) {
	        //    extensionContent = (Element) extList.item(0);
	        //} else {
	            // Si no existe, crea la estructura necesaria
	        //    Element ublExtensions = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2", "ext:UBLExtensions");
	        //    Element ublExtension = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2", "ext:UBLExtension");
	        //    extensionContent = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2", "ext:ExtensionContent");

	        //    ublExtension.appendChild(extensionContent);
	        //    ublExtensions.appendChild(ublExtension);
	        //    doc.getDocumentElement().insertBefore(ublExtensions, doc.getDocumentElement().getFirstChild());
	       /// }

	        // Cargar la clave privada y certificado
	        KeyStore keyStore = KeyStore.getInstance("PKCS12");
	        try (FileInputStream fis = new FileInputStream("E:/Proyectos/Empresa/facturacion/certificado/DEMO-20513981491.pfx")) {
	            keyStore.load(fis, "1234567890".toCharArray());
	        }
	        String alias = keyStore.aliases().nextElement();
	        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, "1234567890".toCharArray());
	        X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);

	        // Crear la firma XML
	        XMLSignature sig = new XMLSignature(doc, "", XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256);
	        Transforms transforms = new Transforms(doc);
	        transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
	        sig.addDocument("", transforms, "http://www.w3.org/2001/04/xmlenc#sha256");
	        //sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA256);

	        // Agregar la clave pública (certificado) a la firma
	        sig.addKeyInfo(cert);
	        sig.addKeyInfo(cert.getPublicKey());

	        // Configurar el contexto de firma
	        //DOMSignContext signContext = new DOMSignContext(privateKey, extensionContent);
	        
	     // Configura dónde en el documento XML quieres que aparezca la firma
	        Element root = (Element) doc.getDocumentElement().getElementsByTagName("ext:ExtensionContent").item(0);
	        root.appendChild(sig.getElement());

	        // Firmar el documento
	        //sig.sign(signContext);
	        sig.sign(privateKey);

	        // Guardar el documento firmado
	        try (FileOutputStream fos = new FileOutputStream("E:/Proyectos/Empresa/facturacion/xmlfirma/signed_invoice6.xml")) {
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer trans = tf.newTransformer();
	            trans.transform(new DOMSource(doc), new StreamResult(fos));
	        }
	 		return ResponseEntity.ok(response);
	 		
	 	}
 }