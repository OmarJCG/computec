package com.computec.computec.model;

public class Contra {

    private String oldContra;

    private String newContra;

    public Contra(String oldContra, String newContra) {
        this.oldContra = oldContra;
        this.newContra = newContra;
    }

    public Contra() {
    }

    public String getOldContra() {
        return oldContra;
    }

    public void setOldContra(String oldConstra) {
        this.oldContra = oldConstra;
    }

    public String getNewContra() {
        return newContra;
    }

    public void setNewContra(String newContra) {
        this.newContra = newContra;
    }

    @Override
    public String toString() {
        return "Contraseña{" +
                "oldConstra='" + oldContra + '\'' +
                ", newContra='" + newContra + '\'' +
                '}';
    }
}
