package fr.exanpe.demo

class ExaSecurityDemoController {

    static allowedMethods = [save: "POST", index : "GET", reset : "GET"]

    def index() {

    }

    def save(){
        withForm {
            redirect(action: "index")
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

