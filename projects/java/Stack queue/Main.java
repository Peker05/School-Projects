import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static ArrayList<String> commandList;
    public static Stack stack;
    public static Queue queue;
    public static int last;

    public static void main(String[] args) {

        commandList = new ArrayList<>();
        stack = new Stack();
        queue= new Queue();
        last = -1;


        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("stackOut.txt"));
            br.close();
            BufferedWriter br2 = new BufferedWriter(new FileWriter("queueOut.txt"));
            br2.close();
        } catch (Exception ex) {
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String s;
            while ((s = br.readLine()) != null) {
                commandList.add(s);
            }
            br.close();
        } catch (Exception ex) {
            return;
        }

        for (String s: commandList){
            last++;
            String[] temp_array = s.split(" ");
            Commands(temp_array);
        }
    }
    // determining the right commands
    public static void Commands(String[] s){
        if (s[1].equals("insertNumbers")){
            insertNumbers(s);
        }
        else if (s[1].equals("clear")) {
            clear(s);
        }
        else if (s[1].equals("checkNumber")) {
            checkNumber(s);
        }
        else if (s[1].equals("calculateFib")) {
            calculateFib(s);
        }
        else if (s[1].equals("reverse")) {
            reverse(s);
        }
        else if (s[1].equals("isExist")) {
            isExist(s);
        }
        else if (s[1].equals("sumDistinctElements")) {
            sumDistinctElements(s);
        }
        else if (s[1].equals("evenReverse")) {
            evenReverse(s);
        }
    }
    // insert numbers method as mentioned in pdf
    public static void insertNumbers(String[] s){
        for (int i = 2; i < s.length; i++){
            if (s[0].equals("S")){
                stack.push(Integer.parseInt(s[i]));
            }
            else if (s[0].equals("Q")){
                queue.enqueue(Integer.parseInt(s[i]));
            }
        }
        if (s[0].equals("S")){
            printStack(s);
        }
        else if (s[0].equals("Q")){
            printQueue(s);
        }
    }
    // clear method as mentioned in pdf
    public static void clear(String[] s){

        if (s[0].equals("S")){
            stack.clearStack();
            printStack(s);
        }
        else if (s[0].equals("Q")){
            queue.clearQueue();
            printQueue(s);
        }
    }
    // checkNumber method as mentioned in pdf
    public static void checkNumber(String[] s){
        ArrayList<Integer> temp_array = new ArrayList<>();

        if (s[0].equals("S")){
            while (!stack.isEmpty()){
                temp_array.add(stack.peek());
                stack.pop();
            }
            if (Integer.parseInt(s[2]) % 2 == 0){
                temp_array.remove(Collections.max(temp_array));
            }
            else {
                temp_array.remove(Collections.min(temp_array));
            }
            for (int i = temp_array.size()-1; i >= 0; i--){
                stack.push(temp_array.get(i));
            }
            stack.push(Integer.parseInt(s[2]));
            printStack(s);
        }

        else if (s[0].equals("Q")){
            while (!queue.isEmpty()){
                temp_array.add(queue.peek());
                queue.dequeue();
            }
            if (Integer.parseInt(s[2]) % 2 == 0){
                temp_array.remove(Collections.max(temp_array));
            }
            else {
                temp_array.remove(Collections.min(temp_array));
            }

            queue.enqueue(Integer.parseInt(s[2]));

            for (int i = 0; i < temp_array.size(); i++){
                queue.enqueue(temp_array.get(i));
            }

            printQueue(s);
        }

        temp_array.clear();
    }
    // calculateFib method as mentioned in pdf
    public static void calculateFib(String[] s){
        ArrayList<Integer> fib_array = new ArrayList<>();
        ArrayList<Integer> temp_array = new ArrayList<>();
        if (s[2].equals("1")){
            fib_array.add(1);
        }
        else if (s[2].equals("2")){
            fib_array.add(1);
            fib_array.add(1);
        }
        else {
            fib_array.add(1);
            fib_array.add(1);
            for (int i = 2; i < Integer.parseInt(s[2]); i++){
                fib_array.add(fib_array.get(i-2)+fib_array.get(i-1));
            }
        }

        if (s[0].equals("S")){
            while (!stack.isEmpty()){
                temp_array.add(stack.peek());
                stack.pop();
            }

            for (int i = fib_array.size()-1; i > -1; i--){
                stack.push(fib_array.get(i));
            }

            for (int i = 0; i < temp_array.size(); i++){
                stack.push(temp_array.get(i));
            }

            printStack(s);
        }
        else if (s[0].equals("Q")){
            while (!queue.isEmpty()){
                temp_array.add(queue.peek());
                queue.dequeue();
            }

            for (int i = fib_array.size()-1; i > -1; i--){
                queue.enqueue(fib_array.get(i));
            }

            for (int i = temp_array.size()-1; i >= 0; i--){
                queue.enqueue(temp_array.get(i));
            }
            printQueue(s);
        }
    }
    // reverse method as mentioned in pdf
    public static void reverse(String[] s){
        ArrayList<Integer> temp_array = new ArrayList<>();
        ArrayList<Integer> reverse_array = new ArrayList<>();
        int counter = 0;

        if (s[0].equals("S")){
            while (!stack.isEmpty()){
                temp_array.add(stack.peek());
                stack.pop();
            }

            if (Integer.parseInt(s[2]) < 0){
                for (int i = -Integer.parseInt(s[2]) - 1; i > -1; i--){
                    reverse_array.add(temp_array.get(i));
                }
                for (int i = 0; i < -Integer.parseInt(s[2]); i++){
                    temp_array.set(i, reverse_array.get(i));
                }
            }
            else {
                for (int i = temp_array.size() - 1; i > Integer.parseInt(s[2]); i--){
                    reverse_array.add(temp_array.get(i));
                }
                for (int i = temp_array.size() - Integer.parseInt(s[2]); i < temp_array.size(); i++){
                    temp_array.set(i, reverse_array.get(counter));
                    counter++;
                }
            }

            for (int i = temp_array.size()-1; i >= 0; i--){
                stack.push(temp_array.get(i));
            }
            printStack(s);

        }

        else if (s[0].equals("Q")){
            while (!queue.isEmpty()){
                temp_array.add(queue.peek());
                queue.dequeue();
            }

            if (Integer.parseInt(s[2]) > 0){
                for (int i = Integer.parseInt(s[2]) - 1; i > -1; i--){
                    reverse_array.add(temp_array.get(i));
                }
                for (int i = 0; i < Integer.parseInt(s[2]); i++){
                    temp_array.set(i, reverse_array.get(i));
                }
            }
            else {
                for (int i = temp_array.size() - 1; i > -Integer.parseInt(s[2]); i--){
                    reverse_array.add(temp_array.get(i));
                }
                for (int i = temp_array.size() + Integer.parseInt(s[2]); i < temp_array.size(); i++){
                    temp_array.set(i, reverse_array.get(counter));
                    counter++;
                }
            }

            for (int i = 0; i < temp_array.size(); i++){
                queue.enqueue(temp_array.get(i));
            }
            printQueue(s);
        }
    }
    // isExist method as mentioned in pdf
    public static void isExist(String[] s){
        ArrayList<Integer> temp_array = new ArrayList<>();

        if (s[0].startsWith("S")){

            while (Integer.parseInt(s[2]) != (stack.peek())) {
                temp_array.add(stack.peek());
                stack.pop();
                if (stack.isEmpty()){
                    break;
                }
            }

            try {
                BufferedWriter br = new BufferedWriter(new FileWriter("stackOut.txt",true));
                br.write("After ");
                for (int j = 1; j < s.length; j++){
                    if (j != s.length-1){
                        br.write(s[j]+ " ");
                    }
                    else {
                        br.write(s[j] + ":\n");
                    }
                }
                if (!stack.isEmpty()){
                    br.write("1\n");
                }
                else {
                    br.write("0\n");
                }
                br.close();
            } catch (Exception ex) {
                return;
            }

            for (int i = temp_array.size()-1; i >= 0; i--){
                stack.push(temp_array.get(i));
            }

        }
        else if (s[0].startsWith("Q")) {

            while (Integer.parseInt(s[2]) != (queue.peek())) {
                temp_array.add(queue.peek());
                queue.dequeue();
                if (queue.isEmpty()){
                    break;
                }
            }

            try {
                BufferedWriter br = new BufferedWriter(new FileWriter("queueOut.txt",true));
                br.write("After ");
                for (int j = 1; j < s.length; j++){
                    if (j != s.length-1){
                        br.write(s[j]+ " ");
                    }
                    else {
                        br.write(s[j] + ":\n");
                    }
                }

                if (!queue.isEmpty()){
                    br.write("1\n");
                }
                else {
                    br.write("0\n");
                }
                br.close();
            } catch (Exception ex) {
                return;
            }

            for (int i = 0; i < temp_array.size(); i++){
                queue.enqueue(temp_array.get(i));
            }
        }
        temp_array.clear();
    }
    // sumDistinctElements method as mentioned in pdf
    public static void sumDistinctElements(String[] s){
        ArrayList<Integer> temp_array = new ArrayList<>();
        ArrayList<Integer> sum_array = new ArrayList<>();
        int sum = 0;

        if (s[0].equals("S")){

            while (!stack.isEmpty()){
                temp_array.add(stack.peek());
                stack.pop();
            }

            for (int i: temp_array){
                if (!sum_array.contains(i)){
                    sum_array.add(i);
                }
            }

            for (int i: sum_array){
                sum += i;
            }

            for (int i = temp_array.size()-1; i >= 0; i--){
                stack.push(temp_array.get(i));
            }

            try {
                BufferedWriter br = new BufferedWriter(new FileWriter("stackOut.txt",true));
                br.write("After ");
                for (int j = 1; j < s.length; j++){
                    if (j != s.length-1){
                        br.write(s[j]+ " ");
                    }
                    else {
                        br.write(s[j] + ":\n");
                    }
                }

                br.write(sum + "\n");
                br.close();
            } catch (Exception ex) {
                return;
            }

        }

        else if (s[0].equals("Q")){
            while (!queue.isEmpty()){
                temp_array.add(queue.peek());
                queue.dequeue();
            }

            for (int i: temp_array){
                if (!sum_array.contains(i)){
                    sum_array.add(i);
                }
            }

            for (int i: sum_array){
                sum += i;
            }

            for (int i = 0; i < temp_array.size(); i++){
                queue.enqueue(temp_array.get(i));
            }

            try {
                BufferedWriter br = new BufferedWriter(new FileWriter("queueOut.txt",true));
                br.write("After ");
                for (int j = 1; j < s.length; j++){
                    if (j != s.length-1){
                        br.write(s[j]+ " ");
                    }
                    else {
                        br.write(s[j] + ":\n");
                    }
                }

                br.write(sum + "\n");
                br.close();
            } catch (Exception ex) {
                return;
            }
        }
    }
    // evenReverse method as mentioned in pdf
    public static void evenReverse(String[] s){
        ArrayList<Integer> temp_array = new ArrayList<>();
        ArrayList<Integer> even_array = new ArrayList<>();
        ArrayList<Integer> reverse_array = new ArrayList<>();

        for (int i = 2; i < s.length; i++){
            if (s[0].equals("S")){
                stack.push(Integer.parseInt(s[i]));
            }
            else if (s[0].equals("Q")){
                queue.enqueue(Integer.parseInt(s[i]));
            }
        }

        if (s[0].equals("S")){
            while (!stack.isEmpty()){
                temp_array.add(stack.peek());
                stack.pop();
            }

            for (int i = 0; i < temp_array.size(); i++){
                if (temp_array.get(i)%2 == 0){
                    even_array.add(i);
                }
            }

            for (int i = even_array.size()-1; i > -1; i--){
                reverse_array.add(i);
            }

            for (int i = 0; i < even_array.size(); i++){
                even_array.set(i, temp_array.get(even_array.get(i)));
            }

            for (int i = 0; i < reverse_array.size(); i++){
                temp_array.set(reverse_array.get(i), even_array.get(i));
            }

            for (int i = temp_array.size()-1; i >= 0; i--){
                stack.push(temp_array.get(i));
            }
            printStack(s);

        }

        else if (s[0].equals("Q")) {
            while (!queue.isEmpty()) {
                temp_array.add(queue.peek());
                queue.dequeue();
            }

            for (int i = 0; i < temp_array.size(); i++){
                if (temp_array.get(i)%2 == 0){
                    even_array.add(i);
                }
            }

            for (int i = even_array.size()-1; i > -1; i--){
                reverse_array.add(even_array.get(i));
            }

            for (int i = 0; i < even_array.size(); i++){
                even_array.set(i, temp_array.get(even_array.get(i)));
            }


            for (int i = 0; i < reverse_array.size(); i++){
                temp_array.set(reverse_array.get(i), even_array.get(i));
            }

            for (int i = 0; i < temp_array.size(); i++) {
                queue.enqueue(temp_array.get(i));
            }
            printQueue(s);
        }

    }
    // print method as mentioned in pdf
    public static void printStack(String[] s){
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("stackOut.txt",true));
            br.write("After ");
            for (int j = 1; j < s.length; j++){
                if (j != s.length-1){
                    br.write(s[j]+ " ");
                }
                else {
                    br.write(s[j] + ":\n");
                }
            }
            br.close();
        } catch (Exception ex) {
            return;
        }
        stack.print();
    }
    // print method as mentioned in pdf
    public static void printQueue(String[] s){
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("queueOut.txt",true));
            br.write("After ");
            for (int j = 1; j < s.length; j++){
                if (j != s.length-1){
                    br.write(s[j]+ " ");
                }
                else {
                    br.write(s[j] + ":\n");
                }
            }
            br.close();
        } catch (Exception ex) {
            return;
        }
        queue.print();
    }

}
