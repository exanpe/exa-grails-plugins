package fr.exanpe.grails.security

import org.codehaus.groovy.grails.commons.GrailsDomainClassProperty
import org.codehaus.groovy.grails.scaffolding.AbstractGrailsTemplateGenerator

/**
 * ExaGrailsTemplateGenerator
 * Based on DefaultGrailsTemplateGenerator
 * Created by jmaupoux on 17/07/15.
 */
class ExaGrailsTemplateGenerator extends AbstractGrailsTemplateGenerator {

    ExaGrailsTemplateGenerator(ClassLoader classLoader) {
        super(classLoader)
    }

    def renderEditor = { GrailsDomainClassProperty property ->
        def domainClass = property.domainClass
        def cp
        boolean hasHibernate = pluginManager?.hasGrailsPlugin('hibernate') || pluginManager?.hasGrailsPlugin('hibernate4')
        if (hasHibernate) {
            cp = domainClass.constrainedProperties[property.name]
        }

        if (!renderEditorTemplate) {
            // create template once for performance
            renderEditorTemplate = engine.createTemplate(getTemplateText('renderEditor.template'))
        }

        def binding = [
                pluginManager: pluginManager,
                property: property,
                domainClass: domainClass,
                cp: cp,
                domainInstance:getPropertyName(domainClass)]
        return renderEditorTemplate.make(binding).toString()
    }

}
