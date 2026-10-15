package org.example.metier;

import org.example.dao.IDao;

public class MetierImpl implements  IMetier{
    private IDao dao ;

    public void setDao(IDao dao){
        this.dao= dao;
    }


    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("Le DAO n'a pas été injecté.");
        }

        // Récupère la valeur depuis le DAO
        double valeurBase = dao.getValue();

        // Logique métier : double la valeur
        double resultat = valeurBase * 2;

        System.out.println("[METIER] Calcul effectué. Résultat : " + resultat);
        return resultat;
    }
}
