/*
 * Prog4_2.cpp
 *
 *  Created on: 03.06.2019
 *      Author: jannik
 */
#include <strlib.hpp>
#include <iostream>
#include <iomanip>
#include <ios>
#include <stdlib.h>
#include <fstream>

#define OPTION_WC 1
#define OPTION_EQW 2

using namespace std;

void launchWC() {
	char * buffer = (char *) malloc(sizeof (char) * 256);
	int res = 0;
	while (cin >> setw(255)>> buffer) {

		res += wc(buffer);
	}
	cout << "Der Text hat " << res << " Wörter"<< endl;
	free(buffer);
}

void launchWC(char * text) {
	cout << "Der Text hat " << wc(text) << " Wörter"<< endl;
}

void launchCmp(char * c1, char * c2) {
	int res = compairIgnoresWhithspaces(c1,c2);
	if(res == 0){
		cout << "Die Zeichenketten sing gleich"<<endl;
	}else if (res < 0){
		cout << "Die erste Zeichenkette ist kleiner"<<endl;
	}else{
		cout << "Die erste Zeichenkette ist größer"<<endl;
	}
}

