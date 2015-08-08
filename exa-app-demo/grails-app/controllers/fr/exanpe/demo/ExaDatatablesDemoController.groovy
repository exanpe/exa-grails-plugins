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

    def bootstrap() {
        [data: getData() as JSON]
    }

    private def getData() {
        def data = """
            [
                {"arrondissement": 75015, "adresse": "65 boulevard Pasteur", "prix_salle": "-", "geoloc": [48.841007, 2.31466], "nom_du_cafe": "les montparnos", "prix_terasse": "-", "date": "2012-06-27", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "21 rue Copreaux", "prix_salle": "1", "geoloc": [48.841494, 2.307117], "nom_du_cafe": "Le drapeau de la fidelit\\u00e9", "prix_terasse": "-", "date": "2012-10-22", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "125 rue Blomet", "prix_salle": "1", "geoloc": [48.839743, 2.296898], "nom_du_cafe": "Le caf\\u00e9 des amis", "prix_terasse": "-", "date": "2012-10-22", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "21 rue Copreaux", "prix_salle": "1", "geoloc": [48.841494, 2.307117], "nom_du_cafe": "Le drapeau de la fidelit\\u00e9", "prix_terasse": "-", "date": "2012-10-22", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "125 rue Blomet", "prix_salle": "1", "geoloc": [48.839743, 2.296898], "nom_du_cafe": "Le caf\\u00e9 des amis", "prix_terasse": "-", "date": "2012-10-22", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "344Vrue Vaugirard", "prix_salle": "-", "geoloc": [48.839471, 2.30286], "nom_du_cafe": "Coffee Chope", "prix_terasse": "-", "date": "2014-02-01", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "80 Rue Saint-Charles", "prix_salle": "-", "geoloc": [48.847344, 2.286078], "nom_du_cafe": "Le Pareloup", "prix_terasse": "-", "date": "2013-08-22", "prix_comptoir": 0},
                {"arrondissement": 75015, "adresse": "172 rue de vaugirard", "prix_salle": "-", "geoloc": [48.842462, 2.310919], "nom_du_cafe": "le 1 cinq", "prix_terasse": "-", "date": "2012-06-27", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "106 rue Lecourbe", "prix_salle": "-", "geoloc": [48.842868, 2.303173], "nom_du_cafe": "Les Artisans", "prix_terasse": "-", "date": "2012-06-27", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "380 rue de vaugirard", "prix_salle": "-", "geoloc": [48.833146, 2.288834], "nom_du_cafe": "le lutece", "prix_terasse": "-", "date": "2014-02-01", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "48 avenue de la Motte Picquet", "prix_salle": "-", "geoloc": [48.851, 2.300378], "nom_du_cafe": "Le Piquet", "prix_terasse": "-", "date": "2012-06-27", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "95 avenue Emile Zola", "prix_salle": "-", "geoloc": [48.846814, 2.289312], "nom_du_cafe": "Le Germinal", "prix_terasse": "-", "date": "2012-06-27", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "61 avenue de la Motte Picquet", "prix_salle": "-", "geoloc": [48.849497, 2.298855], "nom_du_cafe": "Le Zinc", "prix_terasse": "-", "date": "2012-03-07", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "358 rue de Vaugirard", "prix_salle": "-", "geoloc": [48.835451, 2.292515], "nom_du_cafe": "Le Parc Vaugirard", "prix_terasse": "-", "date": "2012-06-27", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "354 bis rue Vaugirard", "prix_salle": "-", "geoloc": [48.8357, 2.292961], "nom_du_cafe": "Le Comptoir", "prix_terasse": "-", "date": "2012-06-27", "prix_comptoir": 1},
                {"arrondissement": 75015, "adresse": "198 rue de la Convention", "prix_salle": "-", "geoloc": [48.837212, 2.296046], "nom_du_cafe": "Caf\\u00e9 Dupont", "prix_terasse": "-", "date": "2012-05-11", "prix_comptoir": 0},
                {"arrondissement": 75015, "adresse": "10 boulevard Victor", "prix_salle": "-", "geoloc": [48.835843, 2.278501], "nom_du_cafe": "Caf\\u00e9 Victor", "prix_terasse": "-", "date": "2012-03-07", "prix_comptoir": 1}
            ]
        """
        return new JSONArray(data)
    }
}
