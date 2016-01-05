package fr.exanpe.grails.security.xml

import org.w3c.dom.Document
import org.xml.sax.SAXException
import spock.lang.Specification

import javax.xml.parsers.DocumentBuilder

/**
 * Tests XXE parsing protection.
 */
class XMLFactorySpec extends Specification {

    void "testbasic XML"() {
        setup :
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()

        when :
        Document d = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("data/basic.xml"))

        then :
        d != null
    }

    void "test basic xxe attack"() {
        setup :
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()

        when :
        Document d = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("data/xxe1.xml"))

        then :
        d != null
        noExceptionThrown()
    }

    void "test basic xxe protection"() {
        setup :
        DocumentBuilder builder = XMLFactory.createDocumentBuilder()

        when :
        Document d = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("data/xxe1.xml"))

        then :
        thrown(SAXException)

    }
}
