#include "structs.h"
#include "fstream"
#include <sstream>
#include <algorithm>

void writer(std::string lines, std::string fileName){
    std::ofstream file_to_write;
    file_to_write.open(fileName, std::ios_base::app);
    if (!file_to_write) {
        std::cout<< "Error no such file "<<std::endl;
    }
    else{
        file_to_write<< lines;
    }
    file_to_write.close();

}

void deleteStack(std::stack<std::string> &s){
    while (!s.empty()){
        s.pop();
    }
}

std::string printStack(std::stack<std::string> S){
    std::string string = "";
    while (!S.empty()){
        string = S.top() + ' ' + string;
        S.pop();
    }
    return string;
}

std::string getTransition(transition t, std::stack<std::string> s){
    return t.currentState + ','+ t.inputState + ','+ t.stackTop + " => "+ t.nextState + ','+ t.stackPush+ ' ' + "[STACK]: " +
            printStack(s)+"\n";
}

void emptyDeterministicStep(std::string output){
    transition temp;
    for (int i = 0; i < transitionList.size(); i++)
    {
        temp = transitionList.at(i);
        if (temp.currentState == currentState) {
            if ((temp.inputState == "e") && (temp.stackTop == "e")) {
                currentState = temp.nextState;

                if (temp.stackPush != "e") {
                    stack.push(temp.stackPush);
                }
                writer(getTransition(temp, stack), output);
                return;
            } else if ((temp.inputState == "e") && (temp.stackTop == stack.top())) {

                currentState = temp.nextState;

                stack.pop();
                if (temp.stackPush != "e") {
                    stack.push(temp.stackPush);
                }
                writer(getTransition(temp, stack), output);
                return;
            }

        }
    }
}

bool contains(std::string s, char c){
    if (s.find(c) != std::string::npos)
        return true; // found
    else
        return false; // not found

}


std::string reader(std::string file_name){
    std::string line;
    std::string *lines;
    lines = new std::string();
    std::fstream new_file;
    new_file.open(file_name,std::ios::in);
    if (!new_file) {
        std::cout<< "Error no such file"<<std::endl;
    } else
    {
        while (getline(new_file, line)) {
            *lines += line + "\n";
        }
    }
    new_file.close();
    return *lines;
}

std::string reader1(std::string file_name){
    std::string line;
    std::string *lines;
    lines = new std::string();
    std::fstream new_file;
    new_file.open(file_name,std::ios::in);
    if (!new_file) {
        std::cout<< "Error no such file"<<std::endl;
    } else
    {
        while (getline(new_file, line)) {
            *lines += line;
        }
    }
    new_file.close();
    return *lines;
}

bool isValidState(std::string state){
    std::string temp;
    for (int i = 0; i < states.size(); i++)
    {
        temp = states.at(i);
        if (temp == state)
            return true;
    }
    return false;
}

bool isValidStack(std::string element){
    std::string temp;
    if (element == "e"){
        return true;
    }
    for (int i = 0; i < stackAlphabet.size(); i++)
    {
        temp = stackAlphabet.at(i);
        if (temp == element)
            return true;
    }
    return false;
}

bool isValidAlphabet(std::string element){
    std::string temp;
    for (int i = 0; i < inputAlphabet.size(); i++)
    {
        temp = inputAlphabet.at(i);
        if (temp == element)
            return true;
    }
    return false;
}

int deterministicStep(std::string input, int index, std::string output){
    if (!isValidAlphabet(input)){
        return -1;
    }
    transition temp;
    for (int i = 0; i < transitionList.size(); i++)
    {
        temp = transitionList.at(i);
        if (temp.currentState == currentState)
        {
            if ((temp.inputState == "e") && (temp.stackTop == "e"))
            {
                if (!isValidState(temp.nextState)){
                    return -1;
                }

                currentState = temp.nextState;


                if (!isValidStack(temp.stackPush)){
                    return -1;
                }


                if (temp.stackPush != "e")
                {
                    stack.push(temp.stackPush);
                }
                writer(getTransition(temp, stack), output);
                return index;
            }

            else if ((temp.inputState == "e") && (temp.stackTop == stack.top()))
            {
                if (!isValidState(temp.nextState)){
                    return -1;
                }

                currentState = temp.nextState;


                if (!isValidStack(temp.stackPush)){
                    return -1;
                }


                //belki bastırılır stack.pop()
                stack.pop();
                if (temp.stackPush != "e")
                {
                    stack.push(temp.stackPush);
                }
                writer(getTransition(temp, stack), output);
                return index;
            }

            else if ((temp.inputState == input) && (temp.stackTop == "e"))
            {
                if (!isValidState(temp.nextState)){
                    return -1;
                }

                currentState = temp.nextState;


                if (!isValidStack(temp.stackPush)){
                    return -1;
                }

                if (temp.stackPush != "e")
                {
                    stack.push(temp.stackPush);
                }
                writer(getTransition(temp, stack), output);

                return index + 1;
            }

            else if ((temp.inputState == input) && (temp.stackTop == stack.top()))
            {
                if (!isValidState(temp.nextState)){
                    return -1;
                }

                currentState = temp.nextState;


                if (!isValidStack(temp.stackPush)){
                    return -1;
                }


                //belki bastırılır stack.pop()
                stack.pop();
                if (temp.stackPush != "e")
                {
                    stack.push(temp.stackPush);
                }
                writer(getTransition(temp, stack), output);
                return index + 1;
            }
        }
    }
    return -1;

};

bool isAccept(){
    std::string temp;
    for (int i = 0; i < acceptStates.size(); i++)
    {
        temp = acceptStates.at(i);
        if (temp == currentState){
            return true;
        }

    }
    return false;
}

void inputTaker(std::string inputFile, std::string output){
    std::string whole;
    whole = reader1(inputFile);

    std::vector<std::string> lines;
    std::istringstream f(whole);
    std::string s;

    while (getline(f, s, '\r')) {
        lines.push_back(s);
    }

    std::string line;

    for (int i = 0; i < lines.size() ; i++)
    {
        int index = 0;

        currentState = startingState;

        line = lines.at(i);

        if (line.empty()){

            std::string prevState = currentState;
            do {
                prevState = currentState;
                emptyDeterministicStep(output);
            } while (prevState != currentState);

            if (isAccept()){
                //print accept
                deleteStack(stack);
                writer("\n ACCEPT \n\n", output);
                continue;
            }

            bool find = false;
            for (int j = 0; j < acceptStates.size(); j++) {
                if (acceptStates.at(j) == currentState)
                    find = true;
            }
            if (find){
                writer("\n ACCEPT \n", output);
                continue;
            }
            writer("\n REJECT \n", output);
            continue;
        }

        std::vector<std::string> lineArray;
        std::istringstream m(line);
        std::string k;

        while (getline(m,k,',')){
            lineArray.push_back(k);
        }


        while (true) {


            index = deterministicStep(lineArray.at(index), index, output);


            if (index == -1)
            {
                // print reject
                deleteStack(stack);
                writer("\n REJECT \n\n", output);
                break;
            }

            if (index == lineArray.size()){
                std::string prevState = currentState;
                do {
                    prevState = currentState;
                    emptyDeterministicStep(output);
                } while (prevState != currentState);
                /*int x = index - 1;
                deterministicStep("e", x);*/

                if (isAccept()){
                    //print accept
                    deleteStack(stack);
                    writer("\n ACCEPT \n\n", output);
                    break;
                }

                //print reject
                deleteStack(stack);
                writer("\n REJECT \n\n", output);
                break;
            }

        }
    }
}

void createDpda(std::string dpdaFile){
    std::string string;
    string = reader(dpdaFile);

    std::vector<std::string> lines;
    std::istringstream f(string);
    std::string s;
    while (getline(f, s, '\n')) {
        lines.push_back(s);
    }

    for (int i = 0; i < lines.size(); i++) {
        std::string stringLine;
        stringLine = lines.at(i);

        if (stringLine.at(0) == 'Q'){

            std::string temp;
            temp = stringLine.substr(stringLine.find(':') + 1, stringLine.find(' ')-2);
            std::istringstream n(temp);
            std::string k;
            while (getline(n, k, ',')) {
                states.push_back(k);
            }


            std::string temp1;
            temp1 = stringLine.substr(stringLine.find('(') + 1, stringLine.find(')') - stringLine.find('(') -1);
            startingState = temp1;

            std::string temp2;

            temp2 = stringLine.substr(stringLine.find(')') + 2);


            //temp.erase(std::remove(temp.begin(), temp.end(), '('), temp.end());
            //temp.erase(std::remove(temp.begin(), temp.end(), ')'), temp.end());
            temp2.erase(std::remove(temp2.begin(), temp2.end(), '['), temp2.end());
            temp2.erase(std::remove(temp2.begin(), temp2.end(), ']'), temp2.end());

            std::istringstream u(temp2);
            std::string r;

            while (getline(u, r, ',')) {
                acceptStates.push_back(r);
            }

        }
        else if (stringLine.at(0) == 'A'){
            std::string temp;
            temp = stringLine.substr(stringLine.find(':') + 1);
            std::istringstream m(temp);
            std::string n;
            while (getline(m, n, ',')){
                inputAlphabet.push_back(n);
            }
        }
        else if (stringLine.at(0) == 'Z'){
            std::string temp;
            temp = stringLine.substr(stringLine.find(':') + 1);

            std::istringstream m(temp);
            std::string n;
            while (getline(m, n , ',')){
                if (!contains(temp,',')){
                    stackAlphabet.push_back(temp);
                    break;
                }
                stackAlphabet.push_back(n);
            }
        }

        if (stringLine.at(0) == 'T'){
            std::string temp;
            temp = stringLine.substr(stringLine.find(':') + 1);

            std::vector<std::string> elements;
            std::istringstream m(temp);
            std::string n;
            while (getline(m, n, ',')){
                elements.push_back(n);
            }
            transition trans;
            trans.currentState = elements.at(0);
            trans.inputState = elements.at(1);
            trans.stackTop = elements.at(2);
            trans.nextState = elements.at(3);
            trans.stackPush = elements.at(4);
            transitionList.push_back(trans);
        }
    }

}



