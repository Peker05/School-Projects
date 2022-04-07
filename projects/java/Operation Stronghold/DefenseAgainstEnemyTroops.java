import java.util.ArrayList;

/**
 * This class accomplishes Mission Nuke'm
 */
public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalEnemyDefenseSolution
     */
    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP() {
        // TODO: YOUR CODE HERE
        //we will attack no matter what happens at the last hour
        //we will calculate the optimal solution for each hour
        //we will store the optimal solution for each hour in an array
        //and we will combine each hour's optimal solution with shooting at final hour and we will choose which combination is optimal and return it
        //we will use memoization to store the optimal solution for each hour in an array
        int[] optimalSolution = new int[numberOfEnemiesArrivingPerHour.size() + 1];
        ArrayList<ArrayList<Integer>> hours = new ArrayList<>();
        //at the beggining of the numberOfEnemiesArrivingPerHour add 0 element
        hours.add(new ArrayList<>());
        optimalSolution[0] = 0;

        for (int i = 1; i < numberOfEnemiesArrivingPerHour.size() + 1; i++) {
            int max = 0;
            ArrayList<Integer> maxHours = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int temp = optimalSolution[j] + Math.min(numberOfEnemiesArrivingPerHour.get(i - 1), getRechargedWeaponPower(i - j));
                if (temp > max) {
                    max = temp;
                    maxHours = new ArrayList<>(hours.get(j));
                    maxHours.add(i);
                }
            }
            optimalSolution[i] = max;
            hours.add(maxHours);
        }

        return new OptimalEnemyDefenseSolution(optimalSolution[numberOfEnemiesArrivingPerHour.size()], hours.get(numberOfEnemiesArrivingPerHour.size()));


    }
}

