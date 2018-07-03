/*
 * movement.c
 *
 *  Created on: 02.07.2018
 *      Author: jannik
 *      Author: Dominik
 */
#include "../include/movement.h"
#include <stdio.h>

Geschwindigkeit v (Weg w, Zeit t){
	return w/t;
}

Beschleunigung a (Geschwindigkeit v, Zeit t){
	return v/t;
}

Impuls p (Masse m, Geschwindigkeit v){
	return m*v;
}


