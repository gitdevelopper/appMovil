package com.example.apptec.Clases;

public class ObjetosHoy {

    private String salon;
    private String mat;
    private String horain;
    private String horafin;
    private String estud;
    private String nom;
    private String ap;
    private String am;

    public ObjetosHoy(String salon, String mat, String horain, String horafin, String estud, String nom, String ap, String am) {
        this.salon = salon;
        this.mat = mat;
        this.horain = horain;
        this.horafin = horafin;
        this.estud = estud;
        this.nom = nom;
        this.ap = ap;
        this.am = am;
    }

    public  ObjetosHoy(){

    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getHorain() {
        return horain;
    }

    public void setHorain(String horain) {
        this.horain = horain;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getEstud() {
        return estud;
    }

    public void setEstud(String estud) {
        this.estud = estud;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }
}
