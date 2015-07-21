includeTargets << grailsScript("_GrailsInit")
includeTargets << grailsScript("_GrailsCreateArtifacts")

target ('default': "Creates a new secured controller") {
    depends(checkVersion, parseArguments, createConfig)

    def type = "Controller"
    promptForName(type: type)

    for (name in argsMap["params"]) {
        name = purgeRedundantArtifactSuffix(name, type)

        def shortName = name.lastIndexOf(".") == -1 ? name : name.substring(name.lastIndexOf(".")+1)

        createArtifact(name: name, suffix: type, type: "SecController", path: "grails-app/controllers", replacements: ["@shortName@" : shortName])

        def viewsDir = "${basedir}/grails-app/views/${propertyName}"
        ant.mkdir(dir:viewsDir)

        def baseView = config.grails.plugin.exa.sec.scaff.baseView;
        if(baseView){
            if(!new File("${basedir}/grails-app/views/"+baseView).exists()){
                grailsConsole.log("ERROR : File ${baseView} does not exists");
            }else{
                grailsConsole.log("Initialisation of view from ${basedir}/grails-app/views/${baseView}");
                ant.copy(file : "${basedir}/grails-app/views/${baseView}", tofile : "${basedir}/grails-app/views/${propertyName}/index.gsp")
            }
        }

        event("CreatedFile", [viewsDir])

        createUnitTest(name: name, suffix: type, superClass: "ControllerUnitTestCase")
    }

}

USAGE = """
    create-sec-controller [NAME]

where
    NAME       = The name of the controller. If not provided, this
                 command will ask you for the name.
"""