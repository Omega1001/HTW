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

#define EXIT 			0
#define TEST			1

int distanceTest() {

	double a[3],b[3];
	int select = -1;

	while (select != 0) {

	printf("%d : Exit\n%d : Test\n\n", EXIT, TEST);

	select = scanInt("Select an option");

	switch (select) {

		case EXIT:
			break;

		case TEST:

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

			break;

		default:
			select = -1;
			printf("Unknown selection\n");
		}
	}

	return 0;
}
