/*
 * CallByValue.c
 *
 *  	Created on: 24.06.2018
 *      Author: Dominik
 *      Author: Adam
 */

#include <stdio.h>

void doCall(int i){
	i++;
}

int callByValueProof(void) {

	int i =0;
	doCall(i);
	if(i == 0){
		puts("Proof");
		return 0;
	}else{
		puts("Busted");
		return 1;
	}
}

