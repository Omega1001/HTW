/*
 * UniformMovement.c
 *
 *  Created on: 01.07.2018
 *      Author: Dominik
 *      Author: Adam
 */

#include <stdio.h>
#include "UniformMovement.h"

Weg s(Geschwindigkeit v, Zeit t, Zeit t0) {

	return v * (t - t0);
}

Weg uaS(Geschwindigkeit v, Geschwindigkeit v0, Beschleunigung a) {

	return (v * v - v0 * v0) / 2 * a;
}

Geschwindigkeit uaV(Beschleunigung a, Zeit t, Zeit t0, Geschwindigkeit v0) {

	return a * (t - t0) + v0;
}
