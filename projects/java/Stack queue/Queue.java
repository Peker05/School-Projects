import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Queue {
// main queue body
    public int rearPointer;
    public ArrayList<Integer> queue;

    public Queue(){
        rearPointer = -1;
        queue = new ArrayList<>();
    }

    public void enqueue(int x){
        queue.add(x);
        rearPointer++;
    }

    public void dequeue(){
        queue.remove(0);
        rearPointer--;
    }

    public int peek(){
        return queue.get(0);
    }

    public boolean isEmpty(){
        return queue.size() == 0;
    }

    public void clearQueue(){
        while (rearPointer > -1){
            queue.remove(0);
            rearPointer--;
        }
    }
// main print method
    public void print(){

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("queueOut.txt",true));
            if (rearPointer == -1){
                br.write("\n");
            }
            else {
                for (int i = 0; i <= rearPointer; i++) {
                    if (i != rearPointer) {
                        br.write(queue.get(i) + " ");
                    } else {
                        br.write(queue.get(i) + "\n");
                    }
                }
            }
            br.close();
        } catch (Exception ex) {
            return;
        }
    }
}
