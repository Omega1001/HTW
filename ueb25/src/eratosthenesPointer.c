/*
 * eratosthenesPointer.c
 *
 *  Created on: 16.07.2018
 *      Author: Dominik
 */


#include <eratosthenesPointer.h>
#include <stdio.h>
#include <stdlib.h>

int sift_e_p (int tar) {

	if(tar < 2) {

		printf("Es gibt kein Primzahl in dieser Menge.\n");

		return 0;
	}

	int siz = tar +1;
	int sizInt = sizeof(int);

	int *arr = malloc (siz * sizInt);

	*arr = 1;
	*(arr + sizInt) = 1;

	//TODO this loop RANDOMLY causes issues, more likely with bigger numbers
	for(int i = 2; i < siz; i++) {

		*(arr + sizInt * i) = 0;
	}

	for(int i = 2; i < siz; i++) {

		for(int j = 2; i * j < siz; j++){

			*(arr + sizInt * i * j) = 1;

		}
	}

	for(int i = 2; i < siz; i++) {

		if(*(arr + sizInt * i) == 0) {

			printf("%d ist eine Primzahl.\n", i);
		}
	}

	return 0;
}
