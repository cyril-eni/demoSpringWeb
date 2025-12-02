package fr.eni.demospringweb.controller;

import fr.eni.demospringweb.bo.Truc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

@Controller
public class RequestApiController {

    /**
     * Cette méthode va appeler une API, et afficher son résultat dans la vue : responseAPI
     */
    @GetMapping("/requApi")
    public String requApi(Model model){
        RestClient restClient = RestClient.create("https://api.restful-api.dev");


        Truc trucRecupereDepuisAPI =
                // j'effectue requête GET sur https://api.restful-api.dev/objects/7
                restClient.get().uri("/objects/7")
                // je transforme le resultat JSON en objet qui correspond à la structure de ma classe Truc
                .retrieve().body(Truc.class);
        model.addAttribute("trucRecupereDepuisAPI", trucRecupereDepuisAPI);

        return "responseAPI";
    }
}
