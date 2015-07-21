import java.text.SimpleDateFormat

eventCreateWarStart = {warname, stagingDir ->

    if(!config.grails.plugin.exa.builddate.enabled){
        grailsConsole.log('Build Date disabled')
        return;
    }

    event("BuildInfoAddPropertiesStart", [warname, stagingDir])

    Ant.propertyfile(file: "${stagingDir}/WEB-INF/classes/application.properties") {
        entry(key: 'build.date', value: new SimpleDateFormat(config.grails.plugin.exa.builddate.format).format(new Date()))
    }

    grailsConsole.log('Build Date added to application.properties')

    event("BuildInfoAddPropertiesEnd", [warname, stagingDir])

}