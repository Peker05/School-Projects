import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Exterminate
 */
public class OptimalFinalDefenseGP
{
    private ArrayList<Integer> bombWeights;

    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }

    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }

    /**
     *
     * @param maxNumberOfAvailableAUAVs the maximum number of available AUAVs to be loaded with bombs
     * @param maxAUAVCapacity the maximum capacity of an AUAV
     * @return the minimum number of AUAVs required using first fit approach over reversely sorted items.
     * Must return -1 if all bombs can't be loaded onto the available AUAVs
     */
    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity)
    {
        /*procedure getMinNumberOfAUAVsToDeploy(maxNumOfAvailableAUAVs: int, AUAV_CAPACITY: int)
        First sort all bombs by their weights in decreasing order ◃ So that we can load the largest bombs first. Create an array to store remaining space in all available AUAVs
        for i← 1,..., numBombs do
        Find the first AUAV that can accommodate bomb i ◃ When processing the next bomb, scan the previous AUAVs in order, and
         load the bomb onto the first AUAV it fits in.
        Get a new AUAV only if the bomb does not fit in any of the used AUAVs
        available AUAV and if the bomb weight doesn’t exceed AUAV capacity.
        end for
        return minNumberOfAUAVsToDEploy or -1 if all bombs could not be loaded. end procedure
        */
        // First sort all weights in decreasing order
        Collections.sort(this.bombWeights, Collections.reverseOrder());
        // Create an array to store remaining space in all available AUAVs
        int[] remainingSpaceInAUAVs = new int[maxNumberOfAvailableAUAVs];
        ArrayList<Integer> AUAVsUsed = new ArrayList<>();
        AUAVsUsed.add(0);

        ArrayList<Integer> removedBombs = new ArrayList<>();
        int numberOfAUAVsToDeploy = 0;
        int n = 0;
        for (int i = 0; i < this.bombWeights.size(); i++) {
            // Find the first AUAV that can accommodate bomb i
            /*for (int j = 0; j < maxNumberOfAvailableAUAVs; j++) {
                if (remainingSpaceInAUAVs[j] + this.bombWeights.get(i) <= maxAUAVCapacity) {
                    remainingSpaceInAUAVs[j] += this.bombWeights.get(i);
                    removedBombs.add(this.bombWeights.get(i));
                    if (i != this.bombWeights.size() - 1) {
                        break;
                    }
                }
                if (i == this.bombWeights.size() -1 && remainingSpaceInAUAVs[j] > 0) {
                    numberOfAUAVsToDeploy++;
                }
            }*/
            if (this.bombWeights.get(i) > maxAUAVCapacity) {
                return -1;
            }
            for (int j = 0; j < AUAVsUsed.size(); j++) {
                if (AUAVsUsed.get(j) + this.bombWeights.get(i) <= maxAUAVCapacity) {
                    AUAVsUsed.set(j, AUAVsUsed.get(j) + this.bombWeights.get(i));
                    n++;
                    break;

                }
                else if (j == AUAVsUsed.size() - 1 && AUAVsUsed.size() < maxNumberOfAvailableAUAVs) {
                    AUAVsUsed.add(this.bombWeights.get(i));
                    n++;
                    break;

                }
                else if ((j == AUAVsUsed.size() - 1 && AUAVsUsed.size() == maxNumberOfAvailableAUAVs)) {
                    return -1;
                }
            }


            // Get a new AUAV only if the bomb does not fit in any of the used AUAVs
        }
        if (this.bombWeights.size() == n) {
            return AUAVsUsed.size();
        }
        return -1;
    }



    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);
        }
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");
        }
    }
}
