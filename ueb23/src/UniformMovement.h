/*
 * UniformMovement.h
 *
 *  Created on: 01.07.2018
 *      Author: Dominik
 *       Author: Adam
 */

#include "PhysicalQuantities.h"

Weg s(Geschwindigkeit, Zeit, Zeit);

Weg s(Geschwindigkeit, Geschwindigkeit, Beschleunigung);

Geschwindigkeit v(Beschleunigung, Zeit, Zeit, Geschwindigkeit);
