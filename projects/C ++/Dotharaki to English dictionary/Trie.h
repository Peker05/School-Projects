#pragma once
#include "iostream"
#include "FileIO.h"

std::string reader(std::string file_name);
void writer(std::string lines, std::string fileName);
std::vector<std::string> inputTaker(std::string inputFile);

class Node {
public:
    Node* parent;
    std::string letter;
    bool isEndOfWord;
    std::string value;
    Node* next[26];

};

class Trie : public Node {
public:
    Node* root;
    Node* currentNode;

    // after insertion this method will assign inserted node's parent variable to its parent in the tree
    void connectParent(Node* &parent) {
        for (int i = 0; i < 26; i++){
            if (parent->next[i] != NULL){
                parent->next[i]->parent = parent;
                connectParent(parent->next[i]);
            }
        }
    }

    // this method will create a heap allocated node
    static Node* getNode(){
        Node* pNode = nullptr;
        pNode = new Node;
        pNode->parent = NULL;
        pNode->isEndOfWord = false;
        pNode->letter = "";
        if (pNode){
            for (int i = 0; i < 26 ; ++i) {
                pNode->next[i] = nullptr;
            }
        }
        return pNode;
    }

    //this method will check if given node has children or not
    bool hasChildren(Node* node){
        for (int i = 0; i < 26; i++){
            if (node->next[i] != NULL){
                return true;
            }
        }
        return false;
    }

    //this method will check if given node has more than 1 children or not
    bool hasChildrenMoreThanOne(Node* node){
        int counter = 0;
        for (int i = 0; i < 26; i++){
            if (node->next[i] != NULL){
                counter++;
            }
        }
        if (counter > 1)
            return true;
        return false;
    }

    //this method will check if given node has sibling or not
    bool hasSibling(Node* node){
        Node* p ;
        p = node->parent;
        for (int i = 0; i < 26; i++){
            if (p->next[i] != NULL && p->next[i] != node){
                return true;
            }
        }
        return false;
    }

    //this method will check if given key is exists via traversing through trie
    bool isExists(Node* header, std::string key){
        root = header;
        for (int i = 0; i < key.length(); ++i) {
            int c = key.at(i) - '0' - 49;
            if (header->next[c] != NULL) {
                header = header->next[c];
                if (i == key.length() - 1 && header->value != "") {
                    currentNode = header;
                    return true;
                }
                if (i == key.length() - 1 && header->value == "") {
                    return false;
                }
            }
             else
                return false;
        }

    }

    //this method will return true if given key's characters exist and the last character of key doesn't hold any value.
    bool doesCharactersExist(Node* header, std::string key){
        root = header;
        for (int i = 0; i < key.length(); ++i) {
            int c = key.at(i) - '0' - 49;
            if (header->next[c] != NULL) {
                header = header->next[c];
                if (i == key.length() - 1 && header->value == "") {
                    return true;
                }
            }
            else
                return false;
        }
    }

    //this method will return true if given key's characters exist and the last character of key has a value different from the given value.
    bool doesValueExist(Node* header, std::string key, std::string value){
        root = header;
        for (int i = 0; i < key.length(); ++i) {
            int c = key.at(i) - '0' - 49;
            if (header->next[c] != NULL) {
                header = header->next[c];
                if (i == key.length() - 1 && header->value != value && header->value != "") {
                    currentNode = header;
                    return true;
                }
            }
            else
                return false;
        }
    }

    //insert method
    void put(Node* &header, std::string key, std::string val, int index, Node* copy, std::string output){
        if (header == nullptr)
            header = getNode();

        // changing already existing key's value
        if (doesValueExist(header,key, val)){
            writer(key + " was updated \n", output);
            currentNode->value = val;
            currentNode = root;
            return;
        }

        if (isExists(header, key)){
            currentNode = copy;
            writer(key +" already exists \n", output);
            return;
        }

        while (true){
            int c = key.at(index) - '0' - 49;
            //kelimenin sonu
            if (index == key.length() -1){
                //kelimenin son harfi trie de varsa

                if (header->next[c] == NULL)
                {
                    header->next[c] = getNode();
                    header->next[c]->letter = key.at(index);
                    header = header->next[c];
                    header->value = val;
                    writer(key + " was added \n", output);
                    header->isEndOfWord = true;
                    header = copy;
                    connectParent(header);
                    header = copy;
                    return;
                }
                header = header->next[c];
                header->value = val;
                header->isEndOfWord = true;
                writer(key + " was added \n", output);
                header = copy;
                connectParent(header);
                header = copy;
                return;
            }
            // kelimenin sıradaki harfi trie de yoksa
            if (header->next[c] == NULL){
                header->next[c] = getNode();
                header->next[c]->letter = key.at(index);
                header = header->next[c];
                index++;
                continue;
            }

            // kelimenin sıradaki harfi trie de varsa
            else{
                header = header->next[c];
                index++;
                continue;
            }

        }
    }

    std::string get(Node* x, std::string key, std::string output){
        if (x == nullptr)
        {
            return "There is no trie in there mate!!! \n";
        }
        if (x->next[key.at(0)- 'a'] == NULL){
            return "no record \n" ;
        }

        for (int i = 0; i < key.length(); ++i) {
            int c = key.at(i) - '0' - 49;
            if(!x->next[c] && key.length() -1 != i){
                return "incorrect Dothraki word \n";
            }
            if (i != key.length() -1 || x->next[c] !=NULL)
                x = x->next[c];
        }
        if (x->isEndOfWord)
            return  "The English equivalent is "+ x->value + "\n" ;
        else
            return "not enough Dothraki word \n";
    }

    void garbageCollect(Node* &node){
        int index;
        for (int i = 0; i < 26; i++){
            if(node->next[i] !=NULL){
                index = i;
            }
        }
        if (node->next[index] ==NULL){
            return;
        }
        Node* temp = node;
        node = node->next[index];
        delete temp;
        garbageCollect(node);
    }


    void deleteWord( Node* &header ,std::string deletedWord, std::string val, std::string output){
        if(header->next[deletedWord.at(0) - 'a'] == NULL){
            writer("no record \n", output);
            return;
        }
        if (doesCharactersExist(header,deletedWord)){
            writer("not enough Dothraki \n", output);
            return;
        }
        if (header->next[deletedWord.at(0) - 'a'] != NULL && !isExists(header,deletedWord)){
            currentNode = root;
            writer("incorrect Dothraki word \n", output);
            return;
        }



        if (isExists(header,deletedWord) && hasChildren(currentNode)){
            currentNode->value = "";
            currentNode->isEndOfWord = false;
            writer(deletedWord, output);
            writer( "deletion is successful \n", output);
            currentNode = root;
            return;
        }

        if (isExists(header,deletedWord)){
            Node* temp;
            while (!hasSibling(currentNode)){
                currentNode = currentNode->parent;
            }
            int c = currentNode->letter.at(0) - 'a';
            currentNode->parent->next[c] = NULL;
            currentNode->parent = NULL;
            garbageCollect(currentNode);
            temp = currentNode;
            temp->isEndOfWord = false;
            currentNode = root;
            delete temp;
            writer(deletedWord, output);
            writer(" deletion is successful \n", output);
            return;
        }
    }

    void list(Node* node,std::string lines, std::string output){
        if (!hasChildren(node)){
            lines += node->letter;
            std::string temp;
            temp += lines + '(' + node->value + ')' + '\n';
            writer(temp, output);
            return;
        }

        else if (hasChildrenMoreThanOne(node) && node->letter == ""){
            lines += node->letter;
        }

        else if (hasChildrenMoreThanOne(node) && node->value != ""){
            lines += node->letter;
            std::string temp;
            temp += lines + '(' + node->value + ')';
            writer(temp, output);
            writer("\n", output);
            lines = "\t" + lines;
        }

        else if (hasChildrenMoreThanOne(node)){
            lines += node->letter;
            writer(lines, output);
            writer("\n", output);
            lines = "\t" + lines;
        }


        else if (node->isEndOfWord){
            lines += node->letter;
            std::string temp;
            temp += lines + '(' + node->value + ')';
            writer(temp, output);
            writer("\n", output);
        }
        else{
            lines += node->letter;
        }

        for (int i = 0; i < 26; i++){
            if (node->next[i] != NULL){
                list(node->next[i], lines, output);
            }
        }
    }

    void commandsReader(std::string inputFile, Node* &root, Node* copy, std::string output){
        std::vector<std::string> lines = inputTaker(inputFile);
        std::string line;

        for (int i = 0; i < lines.size(); ++i) {
            line = lines.at(i);

            if (line.at(0) == 'i'){
                std::string temp;
                std::string temp1;

                temp = line.substr(line.find('(') + 1, line.find(',') - line.find('(') -1);
                temp1 = line.substr(line.find(',') + 1, line.find(')') - line.find(',') -1);

                put(root, temp, temp1, 0, copy, output);

            }

            else if (line.at(0) == 's'){
                std::string temp;
                temp = line.substr(line.find('(') + 1, line.find(')') - line.find('(') -1);
                writer(get(root, temp,output), output);
            }

            else if (line.at(0) == 'd'){
                std::string temp;
                temp = line.substr(line.find('(') + 1, line.find(')') - line.find('(') -1);

                deleteWord(root, temp, get(root, temp, output), output);
            }

            else if (line.at(0) == 'l'){
                list(root, "-", output);
            }
        }
    }
};