/*
 * Math.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */

#include <stdlib.h>
#include "math2.h"

//=== 1. Aufgabe ===

int * multiply(int *a, int *b) {

	int *res = malloc (sizeof(&res));

	*res = *a * *b;

	return res;
}

//=== 3. Aufgabe ===

int add(int *a, int *b, int *res) {

	*res = *a + *b;

	return 0;
}
