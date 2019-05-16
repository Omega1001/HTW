/*
 * bubblesort.c
 *
 *  Created on: 17.07.2018
 *      Author: Dominik
 */

#include <bubblesort.h>

int compare(int a, int b) {

	return a - b;
}

int swapb(int *a, int *b) {

	int themp = *a;
	*a = *b;
	*b = themp;

	return 0;
}

int sort(int *arr, int siz) {

	for(int i = 0; i < siz; i++) {

		for(int j = 0; j < siz; j++) {

			if(compare(arr[i], arr[j]) > 0){

				swapb(&arr[i], &arr[j]);
			}
		}
	}
	return 0;
}
