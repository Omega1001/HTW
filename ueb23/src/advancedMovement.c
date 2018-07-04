/*
 * advancedMovement.c
 *
 *  Created on: 02.07.2018
 *      Author: jannik
 *      Author: Dominik
 */
#include "../include/advancedMovement.h"
#include "../include/PhysicTypes.h"

Weg wgeradlinigGleichfoermig(Geschwindigkeit v, Zeit t, Zeit t0){
	return v*(t-t0);
}
Weg wgeradlinigGleichmaessigBeschleunigt(Geschwindigkeit a, Beschleunigung v, Beschleunigung v0){
	return (v*v-v0*v0)/(2*a);
}
Geschwindigkeit vgeradlinigGleichmaessigBeschleunigt(Beschleunigung a, Zeit t, Zeit t0, Geschwindigkeit v0){
	return a * (t-t0) + v0;
}


