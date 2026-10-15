package org.example.dao;


public class DaoImplExt3 implements IDao {

    @Override
    public double getValue() {
        System.out.println("[DAO-EXT3] Récupération de la valeur depuis un web service...");
        return 300.0;
    }
}
