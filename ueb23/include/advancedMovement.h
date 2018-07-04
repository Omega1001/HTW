/*
 * advancedMovement.h
 *
 *  Created on: 02.07.2018
 *      Author: jannik
 *      Author: Dominik
 */
#pragma once
#include "PhysicTypes.h"

Weg wgeradlinigGleichfoermig(Geschwindigkeit v, Zeit t, Zeit t0);
Weg wgeradlinigGleichmaessigBeschleunigt(Geschwindigkeit a, Beschleunigung v, Beschleunigung v0);
Geschwindigkeit vgeradlinigGleichmaessigBeschleunigt(Beschleunigung a, Zeit t, Zeit t0, Geschwindigkeit v0);
