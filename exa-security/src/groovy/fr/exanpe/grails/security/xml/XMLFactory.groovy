package fr.exanpe.grails.security.xml

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

/**
 * This class create secured XML parser - escaping XXE attacks
 * Use alternatively groovy.util.XmlParser
 * @see https://www.owasp.org/index.php/XML_External_Entity_%28XXE%29_Processing
 * Created by jmaupoux on 17/07/15.
 */
class XMLFactory {

    static DocumentBuilder createDocumentBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);

        return factory.newDocumentBuilder();
    }

}
