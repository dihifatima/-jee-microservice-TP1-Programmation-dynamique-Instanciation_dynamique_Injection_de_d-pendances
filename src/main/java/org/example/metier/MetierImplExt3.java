package org.example.metier;

import org.example.dao.IDao;

/**
 * Extension 3 : une autre implémentation de IMetier (valeur + 10 %).
 */
public class MetierImplExt3 implements IMetier {
    private IDao dao;

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("Le DAO n'a pas été injecté.");
        }
        double resultat = dao.getValue() * 1.10;
        System.out.println("[METIER-EXT3] Calcul effectué (valeur + 10%). Résultat : " + resultat);
        return resultat;
    }
}
