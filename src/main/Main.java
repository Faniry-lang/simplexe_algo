package main;

import simplex.LP;

public class Main {
    public static void main(String[] args) {
        try {
            LP lp = new LP();

            // Variables de décision : x, y
            // Variables de base (slack) : s1, s2
            lp.variables = new String[]{"x", "y", "s1", "s2"};

            // Matrice des contraintes (coefficients à gauche)
            lp.constraints = new double[][]{
                {10, 20, 1, 0}, // 2x + 3y + s1 = 8
                {8, 8, 0, 1}  // 2x + y  + s2 = 6
            };

            // Second membre des contraintes
            lp.rhs = new double[]{120, 80};

            // Fonction objectif à maximiser : Z = 3x + 5y (forme canonique pour simplex)
            lp.objective = new double[]{12, 16, 0, 0};
            lp.objectiveRhs = new double[1];
            lp.objectiveRhs[0] = 0;

            // Variables de base au départ (s1 et s2)
            lp.basis = new String[]{"s1", "s2"};

            // Lancer le solveur
            lp.SimplexSolver();

            // Affichage du résultat
            System.out.println("✅ Solution optimale trouvée !");
            System.out.println("Z max = " + lp.objectiveRhs[0]);
            for (int i = 0; i < lp.basis.length; i++) {
                System.out.println(lp.basis[i] + " = " + lp.rhs[i]);
            }

        } catch (Exception e) {
            System.err.println("❌ Erreur : " + e.getMessage());
        }
    }
}
