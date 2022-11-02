package com.example.projetobd.entity;

public enum Genre {
    AÇÃO, COMÉDIA, COMÉDIA_ROMÂNTICA, TERROR, AVENTURA, DRAMA, FICÇÃO_CIENTÍFICA, SUSPENSE, MUSICAL, DOCUMENTÁRIO,
    ANIMAÇÃO, INFANTIL, ROMANCE, FANTASIA, GUERRA, FAROESTE, POLICIAL, BIOGRAFIA, HISTÓRICO, ESPIONAGEM, WESTERN,
    MISTÉRIO, THRILLER, CRIME, ANIME, DESCONHECIDO;

    @Override
    public String toString() {
        //Make the first letter uppercase and substitute the underscore with a space
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase().replace("_", " ");
    }
}
