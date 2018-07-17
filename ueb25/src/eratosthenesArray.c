/*
 * eratosthenesArray.c
 *
 *  Created on: 16.07.2018
 *      Author: Dominik
 */


#include <eratosthenesArray.h>
#include <stdio.h>

int sift_e_a (int tar) {

	if(tar < 2) {

		printf("Es gibt kein Primzahl in dieser Menge.\n");

		return 0;
	}

	int siz = tar +1;
	int arr[siz];

	arr[0] = 1;
	arr[1] = 1;

	for(int i = 2; i < siz; i++) {

		arr[i] = 0;
	}

	for(int i = 2; i < siz; i++) {

		for(int j = 2; i * j < siz; j++){

			arr[i * j] = 1;

		}
	}

	for(int i = 2; i < siz; i++) {

		if(arr[i] == 0) {

			printf("%d ist eine Primzahl.\n", i);
		}
	}

	return 0;
}
