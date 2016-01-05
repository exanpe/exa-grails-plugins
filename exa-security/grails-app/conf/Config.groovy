grails.config.defaults.locations = [
        ExaScaffolding2DefaultConfig
]

log4j = {
    error 'org.codehaus.groovy.grails',
          'org.springframework'
}

exa{
    scaff{
        build {
            date {
                enabled = true
            }
        }
        secure{
            cookie {
                enabled = false
                productionOnly = true
            }
        }
    }
}
