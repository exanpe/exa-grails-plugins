package fr.exanpe.test

class TestController {

    static allowedMethods = [save: "POST", index : "GET", reset : "GET"]

    def index() {
        if(!flash.command){
            flash.command = new YoCommand()
        }

        return [command : flash.command]
    }

    def save(YoCommand command){
        withForm {
            if(command.hasErrors()){
                flash.command = command
                redirect(action: "index")
            }

        }.invalidToken {
            csrfHandler(this, request, params)
        }
    }

    def link(){
        withForm {
            redirect(action: "index")
        }.invalidToken {
            csrfHandler(this, request, params)
        }
    }

    def reset(){
        flash.command = new YoCommand()
        redirect(action: "index")
    }

    def redirect(){
        flash.redirect = "http://www.google.fr"
        redirect(controller: "redirectEngine", action: "flash")
    }

    def redirectkeep(){
        flash.redirect = "http://www.google.fr"
        flash.keepSession = true
        redirect(controller: "redirectEngine", action: "flash")
    }

}

