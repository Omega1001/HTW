/*
 * distanceTest.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include <scanAndClear.h>
#include <distanceTest.h>
#include <distance.h>

int distanceTest() {

	double a[3],b[3];

	printf("Werte des Punktes A?\n");

	a[0] = scanDouble("A1 =");
	a[1] = scanDouble("A2 =");
	a[2] = scanDouble("A3 =");

	printf("Werte des Punktes B?\n");

	b[0] = scanDouble("B1 =");
	b[1] = scanDouble("B2 =");
	b[2] = scanDouble("B3 =");

	double dis = distance(a, b);

	printf("Distanz = %f\n Vektor = (%f,%f,%f)\n", dis, a[0], a[1], a[2]);

	return 0;
}
