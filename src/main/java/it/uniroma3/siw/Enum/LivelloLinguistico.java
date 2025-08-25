package it.uniroma3.siw.Enum;

public enum LivelloLinguistico {
    PRINCIPIANTE(1, "Principiante"),
    INTERMEDIO(2, "Intermedio"),
    AVANZATO(3, "Avanzato");

    private final int ordine;
    private final String descrizione;

    LivelloLinguistico(int ordine, String descrizione) {
        this.ordine = ordine;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}

