package fr.eni.demospringweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

    /**
     * en GetMapping : j'affiche le template avec le formulaire
     */
    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    /**
     * en PostMapping : j'affiche le resultat du traitement des données (ici le template avec le message "Bonjour")
     */
    @PostMapping("/hello")
    // il y a une injection automatique d'une instance de Model qui définit le contexte partagé entre le controller et mon template
    public String postHello(String nom, Model model) {
        System.out.println("nom:"+nom);
        // j'ajoute un attribut "nom" à mon model qui va être accessible dans mon template
        model.addAttribute("nom",nom);
        // je délègue le rendu de la réponse au template "hello.html"
        // si conflit, possibilité de préciser l'extension : return "hello.htm";
        return "hello";
    }
}
