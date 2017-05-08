/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

/**
 *
 * @author Joseph
 */
public class BLLogueo {
    private static int idUsuario;
    private static boolean admin;

    public BLLogueo() {
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int idUsuario) {
        BLLogueo.idUsuario = idUsuario;
    }

    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        BLLogueo.admin = admin;
    }
}
