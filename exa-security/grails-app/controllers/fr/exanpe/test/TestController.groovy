package fr.exanpe.test

import grails.validation.Validateable

class TestController {

    static allowedMethods = [save: "POST", index : "GET", reset : "GET"]

    def index() {
        if(!flash.command){
            flash.command = new TestCommand()
        }

        [command : flash.command]
    }

    def save(TestCommand command){
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
        flash.command = new TestCommand()
        redirect(action: "index")
    }

    def doredirect(){
        flash.redirect = "http://www.google.fr"
        redirect(controller: "redirectEngine", action: "flash")
    }

    def doredirectkeep(){
        flash.redirect = "http://www.google.fr"
        flash.keepSession = true
        redirect(controller: "redirectEngine", action: "flash")
    }

}

@Validateable
class TestCommand{

}