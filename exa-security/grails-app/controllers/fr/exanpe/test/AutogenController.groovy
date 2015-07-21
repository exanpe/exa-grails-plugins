package fr.exanpe.test



import grails.validation.Validateable

class AutogenController {

    static allowedMethods = [save: "POST", index : "GET"]

    def index() {
        if(!flash.command){
            flash.command = new AutogenCommand()
        }

        return [command : flash.command]
    }

    def save(AutogenCommand command){
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
        flash.command = new AutogenCommand()
        redirect(action: "index")
    }
}

@Validateable
class AutogenCommand{

}
