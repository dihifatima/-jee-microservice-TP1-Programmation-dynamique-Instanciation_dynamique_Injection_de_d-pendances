package org.example.presentation;

import org.example.dao.IDao;
import org.example.metier.IMetier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Presentation2 {

    public static void main(String[] args) {
        try {
            // 1. Charger les noms de classes depuis le fichier de configuration
            // Fichier de config : config.txt par défaut, ou celui passé en argument (ex. config-ext2.txt)
            String configFile = (args.length > 0) ? args[0] : "config.txt";
            String[] classNames = loadClassNames(configFile);

            // 2. Instancier le DAO
            IDao dao = (IDao) instantiateClass(classNames[0]);
            System.out.println("[CONTAINER] DAO instancié : " + dao.getClass().getName());

            // 3. Instancier le Métier
            IMetier metier = (IMetier) instantiateClass(classNames[1]);
            System.out.println("[CONTAINER] Métier instancié : " + metier.getClass().getName());

            // 4. Injecter le DAO dans le Métier via la réflexion
            injectDependency(metier, "setDao", dao);

            // 5. Exécuter le calcul
            double resultat = metier.calcul();
            System.out.println("Résultats = " + resultat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lit les noms de classes depuis un fichier de configuration.
     */
    private static String[] loadClassNames(String fileName) throws IOException {
        String[] classNames = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < 2; i++) {
                classNames[i] = reader.readLine().trim();
            }
        }
        return classNames;
    }

    /**
     * Instancie une classe à partir de son nom complet.
     */
    private static Object instantiateClass(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        return constructor.newInstance();
    }

    /**
     * Injecte une dépendance dans un objet via une méthode setter.
     */
    private static void injectDependency(Object target, String setterName, Object dependency) throws Exception {
        Class<?> targetClass = target.getClass();
        Method setterMethod = targetClass.getMethod(setterName, dependency.getClass().getInterfaces()[0]);
        setterMethod.invoke(target, dependency);
    }
}