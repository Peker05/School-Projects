#pragma once
#include "fstream"
#include "sstream"
#include "vector"

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

std::vector<std::string> inputTaker(std::string inputFile)
{
    std::string whole;
    whole = reader(inputFile);

    std::vector<std::string> lines;
    std::istringstream f(whole);
    std::string s;

    while (getline(f, s, '\n')) {
        lines.push_back(s);
    }
    return lines;

}