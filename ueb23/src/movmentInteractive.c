/*
 * movmentInteractive.c
 *
 *  Created on: 02.07.2018
 *      Author: jannik
 */
#include "../include/movement.h"
#include "../include/advancedMovement.h"
#include "../include/PhysicTypes.h"
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "../include/movmentInteractive.h"

#define EXIT 0
#define SPEED 1
#define ACCELERATE 2
#define IMPULS 3
#define GLEICH_BEWEGUNG 4
#define GLEICH_BESCHLEUNIGUNG 5
#define SPEED_GLEICH_BESCHLEUNIGT 6

void clearChar() {
	char c;
	while ((c = getchar()) != '\n' && c != EOF) {
	}
}

float scanFloat(char* message) {
	float read =0;
	int readChars = 0;
	while (readChars != 1) {
		fputs(message,stdout);
		fputs(" > ",stdout);
		readChars = scanf("%f", &read);
		clearChar();
	}
	return read;
}

int scanInt(char* message) {
	int read = -1;
	int readChars = 0;
	while (readChars != 1) {
		fputs(message,stdout);
		fputs(" > ",stdout);
		readChars = scanf("%d", &read);
		clearChar();
	}
	return read;
}

int runMovementInteractive() {
	bool exit = false;
	int select = -1;
	float w = 0;
	float t =0;
	float thempResult = 0;


	while (!exit) {
		thempResult = 0;
		printf("Exit : %d\r\n", EXIT);
		printf("Geschwindigkeit : %d\r\n", SPEED);
		printf("Beschleunigung : %d\r\n", ACCELERATE);
		printf("Impuls : %d\r\n", IMPULS);
		printf("Weg -> Gleichfoermig geradlinige Bewegung : %d\r\n",
		GLEICH_BEWEGUNG);
		printf(
				"Weg -> Gleichmaessig beschleunigte geradlinige Bewegung : %d\r\n",
				GLEICH_BESCHLEUNIGUNG);
		printf(
				"Geschwindigkeit -> Gleichmaessig beschleunigte geradlinige Bewegung : %d\r\n",
				SPEED_GLEICH_BESCHLEUNIGT);
		select = scanInt("Enter a option:");

		switch (select) {
		case EXIT:
			exit = true;
			break;
		case SPEED:
			w = scanFloat("Den Weg eingeben");
			t = scanFloat("Die Zeit eingeben");
			thempResult = v(w,t);
			break;
		case ACCELERATE:
			thempResult = a(scanFloat("Die Geschwindigkeit eingeben"),
					scanFloat("Die Zeit eingeben"));
			break;
		case IMPULS:
			thempResult = p(scanFloat("Die Masse eingeben"),
					scanFloat("Die Geschwindigkeit eingeben"));
			break;
		case GLEICH_BEWEGUNG:
			thempResult = wgeradlinigGleichfoermig(
					scanFloat("Die Geschwindigkeit eingeben"),
					scanFloat("Die Zeit eingeben eingeben"),
					scanFloat("Die Startzeit eingeben"));
			break;
		case GLEICH_BESCHLEUNIGUNG:
			thempResult = wgeradlinigGleichmaessigBeschleunigt(
					scanFloat("Die Geschwindigkeit eingeben"),
					scanFloat("Die Beschleunigung eingeben eingeben"),
					scanFloat("Die grund-Beschleunigung eingeben eingeben"));
			break;
		case SPEED_GLEICH_BESCHLEUNIGT:
			thempResult = vgeradlinigGleichmaessigBeschleunigt(
					scanFloat("Die Beschleunigung eingeben"),
					scanFloat("Die Zeit eingeben"),
					scanFloat("Die Startzeit eingeben"),
					scanFloat("Die Grundgeschwindigkeit eingeben"));
			break;
		default:
			puts("Not a valid function");
		}
		if (thempResult == 0) {
			puts("No calculated Result");
		} else {
			printf("Das Ergebniss der Berechnung ist : %f\r\n\r\n", thempResult);
		}


	}
	return 0;
}

