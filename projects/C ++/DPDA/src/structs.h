#pragma once
#include "string"
#include "iostream"
#include <vector>
#include <stack>
struct transition{
    std::string currentState;
    std::string inputState;
    std::string stackTop;
    std::string nextState;
    std::string stackPush;
};

std::vector<transition> transitionList;

std::stack<std::string> stack;

std::vector<std::string> inputAlphabet;

std::vector<std::string> stackAlphabet;

std::vector<std::string> states;

std::string startingState;

std::vector<std::string> acceptStates;

std::string currentState;

/*struct Node{
    Node* next;
    std::string data;
};

struct Stack {
    Node* head;
};*/
