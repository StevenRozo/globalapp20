
/*clase para la implementacion de los contructores del login de la aplicación*/
//by Steven Guantiva Rozo 5/10/2018

package com.example.pantallas.globalapp20;

public class User {





    private String usuario, pass, id_persona, docu_persona, usercotejamiento,nomb_usu,ape_usu,usu_privatekey,usu_publickey;
    private int DatoDocuInt;

    public String getUsu_privatekey() {
        return usu_privatekey;
    }

    public void setUsu_privatekey(String usu_privatekey) {
        this.usu_privatekey = usu_privatekey;
    }

    public String getUsu_publickey() {
        return usu_publickey;
    }

    public void setUsu_publickey(String usu_publickey) {
        this.usu_publickey = usu_publickey;
    }

    public String getNomb_usu() {
        return nomb_usu;
    }

    public void setNomb_usu(String nomb_usu) {
        this.nomb_usu = nomb_usu;
    }

    public String getApe_usu() {
        return ape_usu;
    }

    public void setApe_usu(String ape_usu) {
        this.ape_usu = ape_usu;
    }

    public int getDatoDocuInt() {
        return DatoDocuInt;
    }

    public void setDatoDocuInt(int datoDocuInt) {
        DatoDocuInt = 23;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getDocu_persona() {
        return docu_persona;
    }

    public void setDocu_persona(String docu_persona) {
        this.docu_persona = docu_persona;
    }


    public String getUsercotejamiento() {
        return usercotejamiento;
    }

    public void setUsercotejamiento(String usercotejamiento) {
        this.usercotejamiento = usercotejamiento;
    }
}
