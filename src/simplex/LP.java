package simplex;

import utils.Helper;

public class LP {
    public String[] variables;
    public double[][] constraints;
    public double[] rhs;
    public double[] objective;
    public double[] objectiveRhs;
    public String[] basis;

    private void printTableau() {
        // En-tête
        System.out.print("Base\t| ");
        for (String var : variables) {
            System.out.printf("%6s ", var);
        }
        System.out.println("|   RHS");
    
        System.out.println("---------" + "-".repeat(variables.length * 7));
    
        // Contraintes
        for (int i = 0; i < constraints.length; i++) {
            System.out.printf("%4s\t| ", basis[i]);
            for (int j = 0; j < constraints[i].length; j++) {
                System.out.printf("%6.2f ", constraints[i][j]);
            }
            System.out.printf("| %6.2f\n", rhs[i]);
        }
    
        // Fonction objectif
        System.out.println("---------" + "-".repeat(variables.length * 7));
        System.out.print("   Z\t| ");
        for (double coef : objective) {
            System.out.printf("%6.2f ", coef);
        }
        System.out.printf("| %6.2f\n", objectiveRhs[0]);
    }

    public void SimplexSolver() throws Exception {

        while(Helper.GetSignOfFunction(objective) >= 0) {
            // on prend le max des coefficient des variables de la fonction objective
            int pivotColIndex = Helper.Max(this.objective);
            // on construit un vecteur avec les coefficients des fonctions constraintes de la variable du max
            double[] pivotCol = Helper.GetVectorAtCol(pivotColIndex, constraints);
    
            // on crée une variable pour stocker les coordonnées du pivot dans la matrice, on sait qu'elle est sur la colonne du max des coefficients de la fonction objective
            int[] pivot = new int[2];
            // ligne
            pivot[0] = -1;
            // colonne
            pivot[1] = pivotColIndex;
            // on initialise le ratio
            double minRatio = Double.POSITIVE_INFINITY;
    
            // on crée une boucle pour cherche le minimum des ratios entre le rhs et la colonne du pivot
            for(int i = 0 ; i < pivotCol.length; i++) {
                double currentRatio = rhs[i]/pivotCol[i];
                if(currentRatio < minRatio && pivotCol[i] > 0) {
                    minRatio = currentRatio;
                    pivot[0] = i;
                }
            }
    
            // on check si on a bien trouver un pivot positif
            if (pivot[0] == -1) {
                throw new Exception("Problem has no bounded solutions");
            }
    
            // on sait maintenant que la variable à faire sortir de la base est la variable sur la même ligne que la ligne du pivot
            // on utilisera cette variable dans basis[]
            int outVariableIndex = pivot[0];
    
            // on remplacera la variable de sortie par la variable avec l'index suivant dans variables[]
            int inVariableIndex = pivotColIndex;
    
            if(this.basis[outVariableIndex].equals(variables[inVariableIndex])) {
                throw new Exception("Outgoing variable is equal to incoming variable");
            }
    
            // on fait sortir et on fait entrer une nouvelle variable
            this.basis[outVariableIndex] = variables[inVariableIndex];

            // on divise la ligne du pivot par la valeur du pivot
            double pivotValue = constraints[pivot[0]][pivot[1]];
            Helper.Scale(constraints[pivot[0]], 1/pivotValue);

            // on divise aussi le second membre
            this.rhs[pivot[0]] *= 1/pivotValue; 

            // on fait en sorte que la colonne du pivot soit nulle
            for(int i = 0 ; i < this.constraints.length; i++) {
                if(i == pivot[0]) {
                    continue;
                }

                double scalar = -1 * this.constraints[i][pivot[1]];

                Helper.AddScale(this.constraints[i], this.constraints[pivot[0]], scalar);
            
                this.rhs[i] += this.rhs[pivot[0]]*scalar;
            }

            // on update également la fonction objective
            // pareil pour le second membre de la fonction objective
            this.objectiveRhs[0] += this.rhs[pivot[0]]*-1 * this.objective[pivot[1]];
            Helper.AddScale(this.objective, this.constraints[pivot[0]], -1 * this.objective[pivot[1]]);
        }

        this.objectiveRhs[0] = Math.abs(this.objectiveRhs[0]);
    }
}
