package fr.eni.demospringweb.bo;

import lombok.Data;

/**
 * Classe qui va me servir à récupérer les retours de mon appel d'API
 */
@Data
public class Truc {
    private Long id;
    private String name;
}
