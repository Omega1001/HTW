/*
 * UniformMovement.h
 *
 *  Created on: 01.07.2018
 *      Author: Dominik
 *       Author: Adam
 */

#include "PhysicalQuantities.h"

Weg s(Geschwindigkeit, Zeit, Zeit);

Weg uaS(Geschwindigkeit, Geschwindigkeit, Beschleunigung);

Geschwindigkeit uaV(Beschleunigung, Zeit, Zeit, Geschwindigkeit);
