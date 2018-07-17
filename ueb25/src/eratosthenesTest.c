/*
 * eratosthenesTest.c
 *
 *  Created on: 17.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include <scanAndClear.h>
#include "eratosthenesTest.h"
#include "eratosthenesArray.h"
#include "eratosthenesPointer.h"

int test_eratosthenes() {

	int tar = scanInt("Bis zu welcher Zahl sollen die Primzahlen bestimmt werden?\n");

	printf("Array:\n");
	sift_e_a(tar);

	printf("Pointer:\n");
	sift_e_p(tar);

	return 0;
}
