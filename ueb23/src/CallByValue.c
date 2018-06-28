/*
 * CallByValue.c
 *
 *  	Created on: 24.06.2018
 *      Author: Dominik
 *      Author: Adam
 */

#include <stdio.h>

int callByValueProof(void) {

	int a = 1;
	int b = a;

	b *= 2;

	if(a == b) {

		printf("Busted!\n\n");
	}

	if(a != b) {

		printf("Proof!\n\n");
	}


	return 0;
}

