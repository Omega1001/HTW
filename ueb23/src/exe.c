/*
 * exe.c
 *
 *  Created on: 27.06.2018
 *      Author: Dominik
 *      Author: Adam
 */

#include <stdio.h>
#include "../include/PhysicTypes.h"
#include "../include/movmentInteractive.h"
#include "../include/other.h"

int main() {

	printf("Begin of Subprogram 1\n\n");
	int ret1 = minMax();

	printf("\n====================================\n\n");
	printf("Begin of Subprogram 2\n\n");
	int ret2 = callByValueProof();

	printf("\n====================================\n\n");
	printf("Begin of Subprogram 3\n\n");
	int ret3 = runMovementInteractive();

	printf("\n====================================\n\n");
	printf("Begin of Subprogram 4\n\n");
	int ret4 = x();

	printf("\n====================================\n\n");
	printf("Begin of Subprogram 5\n\n");
	int ret5 = minMaxBit();

	printf("\n====================================\n\n");
	printf("End of Execution\n");
	printf("Subprogram 1 returned with %i\n", ret1);
	printf("Subprogram 2 returned with %i\n", ret2);
	printf("Subprogram 3 returned with %i\n", ret3);
	printf("Subprogram 4 returned with %i\n", ret4);
	printf("Subprogram 5 returned with %i\n", ret5);
}
