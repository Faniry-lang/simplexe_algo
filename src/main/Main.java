package main;

import simplex.LinearProblem;

public class Main {
    public static void main(String[] args) {
        try {
            LinearProblem lp = new LinearProblem();

            // Variables de décision : x, y
            // Variables de base (slack) : s1, s2
            lp.variables = new String[]{"x", "y", "s1", "s2", "a1", "a2"};

            // Matrice des contraintes (coefficients à gauche)
            lp.constraints = new double[][]{
                {3, 4, 1, 0, 0, 0}, // 2x + 3y + s1 = 8
                {1, 1, 0, 0, 1, 0},  // 2x + y  + s2 = 6
                {0, 1, 0, -1, 0, 1}  // 2x + y  + s2 = 6
            };

            // Second membre des contraintes
            lp.rhs = new double[]{20, 50, 8};

            // Fonction objectif à maximiser : Z = 3x + 5y (forme canonique pour simplex)
            lp.objective = new double[]{1, 2, 0, -1, 0, 0};
            lp.objectiveRhs = new double[1];
            lp.objectiveRhs[0] = 58;

            // Variables de base au départ (s1 et s2)
            lp.basis = new String[]{"s1", "a1", "a2"};

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
