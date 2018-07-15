/*
 * distance.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */

#include "distance.h"
#include <math.h>

double distance(double *a, double *b) {

	double res = 0;

	a[0] -= b[0];
	a[1] -= b[1];
	a[2] -= b[2];

	res = sqrt(pow(a[0], 2) + pow(a[1], 2) + pow(a[2], 2));

	return res;
}
