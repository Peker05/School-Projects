#pragma once
#include "Methods.h"

int main(int argc, char *argv[]) {
    createDpda(argv[1]);
    /*for (int i = 0; i < transitionList.size(); ++i) {
        std::cout<<getTransition(transitionList.at(i));
    }*/

    /*for (int i = 0; i < acceptStates.size(); ++i) {
        std::cout<<acceptStates.at(i)<<std::endl;
    }*/
    inputTaker(argv[2], argv[3]);

}
