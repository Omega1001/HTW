/*
 * main.cpp
 *
 *  Created on: 03.06.2019
 *      Author: jannik
 */

#include <Prog4_2.hpp>

int main (int argc, char ** argv){
	if(argc == 1){
		launchWC();
	}else if (argc == 2){
		launchWC(*(argv+1));
	}else{
		launchCmp(*(argv+1),*(argv+2));
	}
	return 0;
}



