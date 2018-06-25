/*
 * exec.c
 *
 *  Created on: 25.06.2018
 *      Author: Jannik
 *      Author: Dominik
 */
#include <stdio.h>

int main() {
	printf("====================================\n");
	printf("Begin of Subprogram 1\n");
	int ret = uebA1();
	printf("====================================\n");
	printf("Begin of Subprogram 2\n");
	int ret2 = uebA2();
	printf("\n====================================\n");
	printf("End of Execution\n");
	printf("Subprogram 1 returned with %i\n", ret);
	printf("Subprogram 2 returned with %i\n", ret2);
	printf("Subprogram 2 returned with %i\n", test());
}

