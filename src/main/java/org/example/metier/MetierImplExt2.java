package org.example.metier;

import org.example.dao.IDao;

/**
 * Extension 2 : une autre implémentation de IMetier (valeur x 3).
 */
public class MetierImplExt2 implements IMetier {
    private IDao dao;

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("Le DAO n'a pas été injecté.");
        }
        double resultat = dao.getValue() * 3;
        System.out.println("[METIER-EXT2] Calcul effectué (valeur x 3). Résultat : " + resultat);
        return resultat;
    }
}
