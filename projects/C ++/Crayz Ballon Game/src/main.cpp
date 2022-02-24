//
//  Main.cpp
//  Assignment1
//
//  Created by Mustafa Emir Peker on 28.10.2021.
//

//
//  main.cpp
//  Assignment1
//
//  Created by Mustafa Emir Peker on 22.10.2021.
//

#include <iostream>
#include <fstream>
#include <stdio.h>
#include <string>
#include <vector>
#include <stdlib.h>
#include <sstream>



#define MAX_SIZE 100

class Grid{
public:
    std::vector< std::vector<int>> mainGrid;

    void emptyGridCreator( int x){
        for (int i = 0; i < x ; i++)
        {
            std::vector<int> lstTableRow;
            for (int j = 0; j < x ; j++)
            {
                lstTableRow.push_back(0);
            }
            mainGrid.push_back(lstTableRow);
        }
    }

};


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
            *lines += line;
        }
    }
    new_file.close();
    return *lines;
}


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
void newCheckNeighbours(Grid& grid, int& id, int& x, int& y){
    bool hasLinked;
    hasLinked = false;
    std::vector<int> xCoordinate;
    std::vector<int> yCoordinate;

    // out of bounds check
    if ((grid.mainGrid.size()) != x + 1)

        // 1 birim aşşağı
        if (grid.mainGrid[x+1][y] == id) {
            xCoordinate.push_back(x+1);
            yCoordinate.push_back(y);

            // out of bounds check
            if ((grid.mainGrid.size() ) != x + 2)

                // 2 birim aşşağı
                if (grid.mainGrid[x+2][y] == id){
                    xCoordinate.push_back(x+2);
                    yCoordinate.push_back(y);
                    hasLinked = true;

                }

            // out of bounds check
            if ((grid.mainGrid.size()  != y+1))

                // 1 birim aşşağı 1 birim sağa
                if (grid.mainGrid[x+1][y+1] == id){
                    xCoordinate.push_back(x+1);
                    yCoordinate.push_back(y+1);
                    hasLinked = true;

                }

            // out of bounds check
            if (y - 1 != -1)

                //1 birim aşşağı 1 birim sola
                if (grid.mainGrid[x+1][y-1] == id){
                    xCoordinate.push_back(x+1);
                    yCoordinate.push_back(y-1);
                    hasLinked = true;

                }
        }

    // out of bounds check
    if ((grid.mainGrid.size()) != y + 1)

        // 1 birim sağa
        if (grid.mainGrid[x][y+1] == id){
            xCoordinate.push_back(x);
            yCoordinate.push_back(y+1);

            // out of bounds check
            if ((grid.mainGrid.size()) != y + 2)

                // 2 birim sağa
                if (grid.mainGrid[x][y+2] == id){
                    xCoordinate.push_back(x);
                    yCoordinate.push_back(y+2);
                    hasLinked = true;
                }

            // out of bounds check
            if ((grid.mainGrid.size()) != x + 1)

                // 1 birim sağa 1 birim aşşağı
                if (grid.mainGrid[x+1][y+1] == id){
                    xCoordinate.push_back(x+1);
                    yCoordinate.push_back(y+1);
                    hasLinked = true;
                }

            // out of bounds check
            if ((- 1) != x -1)

                // 1 birim yukarı 1 birim sağa
                if (grid.mainGrid[x-1][y+1] == id){
                    xCoordinate.push_back(x-1);
                    yCoordinate.push_back(y+1);
                    hasLinked = true;
                }
        }

    // out of bounds check
    if (x -1 != -1)

        // 1 birim yukarı
        if (grid.mainGrid[x-1][y] == id){
            xCoordinate.push_back(x-1);
            yCoordinate.push_back(y);

            // out of bounds check
            if (x-2 != -1)

                // 2 birim yukarı
                if (grid.mainGrid[x-2][y] == id){
                    xCoordinate.push_back(x-2);
                    yCoordinate.push_back(y);
                    hasLinked = true;
                }

            // out of bounds check
            if (y + 1 != grid.mainGrid.size())

                // 1 birim yukarı 1 birim sağa
                if (grid.mainGrid[x-1][y+1] == id){
                    xCoordinate.push_back(x-1);
                    yCoordinate.push_back(y+1);
                    hasLinked = true;
                }
            // out of bounds check
            if (y-1 != -1)

                // 1 birim yukarı bir birim sola
                if (grid.mainGrid[x-1][y-1] == id){
                    xCoordinate.push_back(x-1);
                    yCoordinate.push_back(y-1);
                    hasLinked = true;
                }
        }
    // out of bounds check
    if (y - 1 != -1)

        // 1 birim sola
        if (grid.mainGrid[x][y-1] == id){
            xCoordinate.push_back(x);
            yCoordinate.push_back(y-1);

            // out of bounds check
            if (y - 2 != -1)

                // 2 birim sola
                if (grid.mainGrid[x][y-2] == id){
                    xCoordinate.push_back(x);
                    yCoordinate.push_back(y-2);
                    hasLinked = true;
                }

            // out of bounds check
            if (x + 1 != grid.mainGrid.size())

                // 1 birim sola 1 birim aşşağı
                if (grid.mainGrid[x+1][y-1] == id){
                    xCoordinate.push_back(x+1);
                    yCoordinate.push_back(y-1);
                    hasLinked = true;
                }

            // out of bounds check
            if (x - 1 != -1)

                // 1 birim sola 1 birim yukarı
                if (grid.mainGrid[x-1][y-1] == id){
                    xCoordinate.push_back(x-1);
                    yCoordinate.push_back(y-1);
                    hasLinked = true;
                }
        }

    if (xCoordinate.size() >= 2){

        for(int i = 0; i < xCoordinate.size(); i++){
            grid.mainGrid[xCoordinate[i]][yCoordinate[i]] = 0;
        }

        int temp = id;
        temp++;
        grid.mainGrid[x][y] = int(id) +1 ;

        newCheckNeighbours(grid, temp , x, y);

    }
}


void placeBalloons(Grid& grid,int& id, int& x, int& y){
    grid.mainGrid[x][y] = id;
}

void placeGivenGrid(Grid& grid, std::string& lines){
    void placeBalloons(Grid& grid,int& id, int& x, int& y);
    void newCheckNeighbours(Grid& grid, int& id, int& x, int& y);

    std::vector<std::string> strings;
    std::istringstream f(lines);
    std::string s;
    while (getline(f, s, '\r')) {
        strings.push_back(s);
    }

    for (int i = 1; i < strings.size(); i++){
        std::vector<std::string> lineString;
        std::istringstream fl(strings[i]);
        std::string k;
        while (getline(fl, k, ' ')) {
            lineString.push_back(k);
        }
        int x = std::stoi(lineString[0]);
        int y = std::stoi(lineString[1]);
        int z = std::stoi(lineString[2]);
        placeBalloons(grid,x,y,z);
        newCheckNeighbours(grid,x,y,z);

    }
}

void part2GridCreator(Grid& grid, std::string& lines) {
    std::vector<std::string> strings;
    std::istringstream f(lines);
    std::string s;
    while (getline(f, s, '\r')) {
        strings.push_back(s);
    }

    for (int i = 1; i < std::stoi(strings[0]) + 1; i++) {
        std::vector<std::string> lineString;
        std::istringstream fl(strings[i]);
        std::string k;
        while (getline(fl, k, ' ')) {
            lineString.push_back(k);
        }

        // putting the grid elements as integers
        for (int j = 0; j < lineString.size(); ++j) {
            grid.mainGrid[i-1][j] = std::stoi(lineString[j]);
        }



    }
}


int placingBomb(Grid& grid, int& x, int& y){
    std::vector<int> xCoordinates;
    std::vector<int> yCoordinates;
    int tempX;
    int tempY;
    // yukarı doğru bakma
    tempX = x;
    while (tempX - 1 != -1){
        tempX--;
        if(grid.mainGrid[tempX][y] == grid.mainGrid[x][y]){
            xCoordinates.push_back(tempX);
            yCoordinates.push_back(y);
        }
    }
    // aşşağı doğru bakma
    tempX = x;
    while (tempX + 1 != grid.mainGrid.size()){
        tempX++;
        if(grid.mainGrid[tempX][y] == grid.mainGrid[x][y]){
            xCoordinates.push_back(tempX);
            yCoordinates.push_back(y);
        }
    }
    // sağa doğru bakma
    tempY = y;
    while (tempY + 1 != grid.mainGrid.size()){
        tempY++;
        if (grid.mainGrid[x][tempY] == grid.mainGrid[x][y]){
            xCoordinates.push_back(x);
            yCoordinates.push_back(tempY);
        }
    }

    // sola doğru bakma
    tempY = y;
    while (tempY - 1 != -1){
        tempY--;
        if (grid.mainGrid[x][tempY] == grid.mainGrid[x][y]){
            xCoordinates.push_back(x);
            yCoordinates.push_back(tempY);
        }
    }

    // yukarı sağ bakma
    tempX = x;
    tempY = y;
    while (tempX - 1 != -1 && tempY + 1 != grid.mainGrid.size()){
        tempX--;
        tempY++;
        if (grid.mainGrid[tempX][tempY] == grid.mainGrid[x][y]){
            xCoordinates.push_back(tempX);
            yCoordinates.push_back(tempY);
        }
    }

    // yukarı sola bakma
    tempX = x;
    tempY = y;
    while (tempX - 1 != -1 && tempY - 1 != -1){
        tempX--;
        tempY--;
        if (grid.mainGrid[tempX][tempY] == grid.mainGrid[x][y]){
            xCoordinates.push_back(tempX);
            yCoordinates.push_back(tempY);
        }
    }

    // aşşağı sağa bakma
    tempX = x;
    tempY = y;
    while (tempX + 1 != grid.mainGrid.size() && tempY + 1 != grid.mainGrid.size()){
        tempX++;
        tempY++;
        if (grid.mainGrid[tempX][tempY] == grid.mainGrid[x][y]){
            xCoordinates.push_back(tempX);
            yCoordinates.push_back(tempY);
        }
    }

    // aşşağı sola bakma
    tempX = x;
    tempY = y;
    while (tempX + 1 != grid.mainGrid.size() && tempY - 1 != -1){
        tempX++;
        tempY--;
        if (grid.mainGrid[tempX][tempY] == grid.mainGrid[x][y]){
            xCoordinates.push_back(tempX);
            yCoordinates.push_back(tempY);
        }
    }

    if (xCoordinates.size() == 0){
        int val = grid.mainGrid[x][y];
        grid.mainGrid[x][y] = 0;
        return val;
    }

    if (xCoordinates.size() > 0){
        for (int i = 0; i < xCoordinates.size(); i++){
            grid.mainGrid[xCoordinates[i]][yCoordinates[i]] = 0;
        }
        int sum;
        sum = grid.mainGrid[x][y] * (xCoordinates.size() + 1);
        grid.mainGrid[x][y] = 0;
        return sum;
    }
}

int placeGivenGrid2(Grid& grid, std::string& lines, int& gridSize) {
    int totalPoints = 0;
    int placingBomb(Grid& grid, int& x, int& y);
    std::vector<std::string> strings;
    std::istringstream f(lines);
    std::string s;
    while (getline(f, s, '\r')) {
        strings.push_back(s);
    }

    for (int i = gridSize + 1; i < strings.size() ; i++) {
        std::vector<std::string> lineString;
        std::istringstream fl(strings[i]);
        std::string k;
        while (getline(fl, k, ' ')) {
            lineString.push_back(k);
        }

        int xCoordinate = std::stoi(lineString[0]);
        int yCoordinate = std::stoi(lineString[1]);

        totalPoints += placingBomb(grid, xCoordinate, yCoordinate);
    }
    return totalPoints;
}

int main(int argc, const char * argv[]) {
    void emptyGridCreator( int x);
    int placingBomb(Grid& grid, int& x, int& y);
    void placeBalloons(Grid& grid, int id, int x, int y);
    void writer(std::string lines, std::string fileName);
    std::string reader(std::string file_name);
    void part2GridCreator(Grid& grid, std::string& lines);
    int placeGivenGrid2(Grid& grid, std::string& lines, int& gridSize);

    std::string input1 = reader(argv[1]);
    std::string input2 = reader(argv[2]);

    int totalScore = 0;

    Grid grid1;
    Grid grid2;

    // determining size of the grid
    int pos1 = input1.find("\r");
    std::string sub1 = input1.substr(0,pos1);
    // string to int conv
    int size1 = std::stoi(sub1);
    // grid creation
    grid1.emptyGridCreator(size1);
    placeGivenGrid(grid1, input1);

    // determining size of the grid
    int pos2 = input2.find("\r");
    std::string sub2 = input2.substr(0,pos2);
    // string to int conv
    int size2 = std::stoi(sub2);
    // grid creation
    grid2.emptyGridCreator(size2);
    part2GridCreator(grid2, input2);


    totalScore = placeGivenGrid2(grid2, input2, size2);






// part 1
    writer("PART 1 :  \n", argv[3] );
    for (int i = 0; i <grid1.mainGrid.size()  ; i++)
{
    for (int j = 0; j < grid1.mainGrid[0].size() ; j++)
    {
        std::string s = std::to_string(grid1.mainGrid[i][j]);
        writer(s + " ", argv[3]);
    }
    writer("\n", argv[3]);
}
    writer("\n", argv[3]);

    // part 2
    writer("PART 2 :  \n", argv[3] );
    for (int i = 0; i < grid2.mainGrid.size(); ++i) {
        for(int j = 0; j < grid2.mainGrid[0].size(); j++){
            std::string s1 = std::to_string(grid2.mainGrid[i][j]);
            writer(s1 + " ", argv[3]);
        }
        writer("\n", argv[3]);
    }
    writer("Total score : " + std::to_string(totalScore), argv[3]);
}



