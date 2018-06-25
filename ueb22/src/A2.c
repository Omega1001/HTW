/*
 * A2.c
 *
 *  Created on: 24.06.2018
 *      Author: Dominik
 *      Author: ADAM
 */

#include <stdio.h>

int uebA2() {

	int n = -1;
	int fib = 0;
	int fib2 = 1;

	printf("Naturliche Zahl n eingeben!\n");

	scanf("%i", &n);
	if (n < 0) {
		return 1;
	}
	for (int i = 1; i != n + 1; i++) {

		fib += fib2;
		fib2 = fib - fib2;

		printf("%6i : %i\n", i, fib);
	}

	return 0;
}
