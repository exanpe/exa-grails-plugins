@artifact.package@

import grails.validation.Validateable

class @artifact.name@ {

    static allowedMethods = [save: "POST", index : "GET"]

    def index() {
        if(!flash.command){
            flash.command = new @shortName@Command()
        }

        [command : flash.command]
    }

    def save(@shortName@Command command){
        withForm {
            if(command.hasErrors()){
                flash.command = command
                redirect(action: "index")
            }

        }.invalidToken {
            csrfHandler(this, request, params)
        }
    }

    def reset(){
        flash.command = new @shortName@Command()
        redirect(action: "index")
    }
}

@Validateable
class @shortName@Command{

}