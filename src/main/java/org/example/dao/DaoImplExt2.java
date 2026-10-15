package org.example.dao;


public class DaoImplExt2 implements IDao {

    @Override
    public double getValue() {
        System.out.println("[DAO-EXT2] Récupération de la valeur depuis un capteur...");
        return 50.0;
    }
}
