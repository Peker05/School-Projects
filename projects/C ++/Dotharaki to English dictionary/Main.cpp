#include <iostream>
#include "Trie.h"

int main(int argc, char *argv[]) {


    Node* p;
    Node* s;
    Trie t;
    p = Trie::getNode();
    s = p;

    t.commandsReader(argv[1], p, s, argv[2]);

}
