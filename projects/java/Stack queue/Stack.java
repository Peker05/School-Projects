import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Stack {
//main stack body
    public int pointer;
    public ArrayList<Integer> stack;

    public Stack(){
        pointer = -1;
        stack = new ArrayList<>();
    }

    public void push(int x){
        stack.add(x);
        pointer++;
    }

    public void pop(){
        stack.remove(pointer);
        pointer--;
    }
//looks for the top element
    public int peek(){
        return stack.get(pointer);
    }

    public boolean isEmpty(){
        return stack.size() == 0;
    }
//stack deleter
    public void clearStack(){
        while (pointer > -1){
            stack.remove(pointer);
            pointer--;
        }
    }
// main print method
    public void print(){
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("stackOut.txt",true));
            if (pointer == -1){
                br.write("\n");
            }
            else {
                for (int i = pointer; i > -1; i--) {
                    if (i != 0) {
                        br.write(stack.get(i) + " ");
                    } else {
                        br.write(stack.get(i) + "\n");
                    }
                }
            }
            br.close();
        } catch (Exception ex) {
            return;
        }
    }
}


