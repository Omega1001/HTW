/*
 * Movement.c
 *
 *  Created on: 01.07.2018
 *      Author: Dominik
 *      Author: Adam
 */

#include <stdio.h>
#include "Movement.h"

Geschwindigkeit v(Weg s, Zeit t) {

	return s / t;
}

Beschleunigung a(Geschwindigkeit v, Zeit t) {

	return v / t;
}

Impuls p(Masse m, Geschwindigkeit v) {

	return m * v;
}

