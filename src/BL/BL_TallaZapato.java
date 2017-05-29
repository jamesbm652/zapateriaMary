/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

/**
 *
 * @author joseph
 */
public class BL_TallaZapato {

    private String generoZapato;
    private String categoriaZapato;
    private double talla;

    public BL_TallaZapato(String generoZapato, String categoriaZapato, double talla) {
        this.generoZapato = generoZapato;
        this.categoriaZapato = categoriaZapato;
        this.talla = talla;
    }

    
    
    public BL_TallaZapato() {
    }

    public String getGeneroZapato() {
        return generoZapato;
    }

    public void setGeneroZapato(String generoZapato) {
        this.generoZapato = generoZapato;
    }

    public String getCategoriaZapato() {
        return categoriaZapato;
    }

    public void setCategoriaZapato(String categoriaZapato) {
        this.categoriaZapato = categoriaZapato;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

}
