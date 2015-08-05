package fr.exanpe.demo

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONArray

class ExaDatatablesDemoController {

    def index() {
        [data: getData() as JSON]
    }

    def custom() {
        [data: getData() as JSON]
    }

    private def getData() {
        def data = """
            [
                {"arrondissement": 75015, "adresse": "65 boulevard Pasteur", "prix_salle": "-", "geoloc": [48.841007, 2.31466], "nom_du_cafe": "les montparnos", "prix_terasse": "-", "date": "2012-06-27", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "21 rue Copreaux", "prix_salle": "1", "geoloc": [48.841494, 2.307117], "nom_du_cafe": "Le drapeau de la fidelit\\u00e9", "prix_terasse": "-", "date": "2012-10-22", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "125 rue Blomet", "prix_salle": "1", "geoloc": [48.839743, 2.296898], "nom_du_cafe": "Le caf\\u00e9 des amis", "prix_terasse": "-", "date": "2012-10-22", "prix_comptoir": 1}
            ]
        """
        return new JSONArray(data)
    }
}
