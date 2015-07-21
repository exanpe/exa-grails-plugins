import fr.exanpe.grails.security.ExaGrailsTemplateGenerator

grails{
    plugin{
        exa{
            sec{
                cookie {
                    secure {
                        enabled = false //test OK :: true/false
                        productionOnly = true
                    }
                    name = null
                }
                headers{
                    nocache = false // test OK :: true/false
                    server = "-" // test OK
                    xframe = null //DENY
                }
                scaff {
                    baseView = "tpl/_base.gsp"
                }
                redirects{
                    map = [:]//key value
                }
            }
            builddate{
                enabled = false //test OK :: true/false
                format = "dd/MM/yyyy" //test OK :: custom format
            }
        }
    }
}
grails.plugin.scaffolding.customTemplateGenerator = ExaGrailsTemplateGenerator