/*
 * lagerInteractive.c
 *
 *  Created on: 10.07.2018
 *      Author: jannik
 */
#include <stdio.h>
#include <stdlib.h>
#include <lager.h>
#include <auto.h>
#include <readAndClear.h>

#define EXIT 0
#define ADD 1
#define VIWE 2
#define REMOVE 3
#define VIEW_ALL 4
#define VALUE 5

bool scannBool(char * message) {
	printf("%s", message);
	return scanInt("1 = true, any = false") == 1;
}

int constructEigenschaften(Eigenschaften ** e) {
	generateEigenschaften(e);
	bool aboard = false;
	while ((*e)->count < 10 && !aboard) {
		char * str = scanString(
				"Enter a property, or line beginning with '.' to quit");
		if (str[0] == '.') {
			aboard = true;
		} else {
			putEigenschaft(*e, str, 20);
		}
		free(str);
	}
	return 0;
}

int constrctAuto(Auto ** res) {
	Eigenschaften * e = NULL;
	Motor * m = NULL;
	char * marke;
	int success = 0;
	//Motor
	success = convertMotor(&m, scanInt("Ps Zahl Eingeben"),
			scanInt("Zylinderzahl eingeben"), scanInt("Hubraum eingeben"));
	if (success != 0) {
		return 100 + success;
	}
	success = constructEigenschaften(&e);
	if (success != 0) {
		return 200 + success;
	}
	marke = scanString("Die Marke eingeben");
	success = convertAuto(res, marke, scanInt("speed eingeben"),
			scanInt("Tuerenzahl eingeben"), scannBool("ABS vorhanden"), e, m);
	if (success != 0) {
		return 300 + success;
	}
	return 0;
}

int run() {
	Auto * themp = NULL;
	AutoLager * lager = NULL;
	createLager(&lager);
	int success = 0;

	bool run = true;
	while (run) {
		printf(
				"Exit : %d\nAdd : %d\nView : %d\nRemove : %d\nView All : %d\nCalculate Value : %d\n",
				EXIT, ADD, VIWE,
				REMOVE, VIEW_ALL, VALUE);
		success = 0;
		int select = scanInt("Select an option");
		switch (select) {
		case EXIT:
			run = false;
			break;
		case ADD:
			themp = NULL;
			success = constrctAuto(&themp);
			if (success != 0) {
				break;
			}
			success = putAuto(lager, themp);
			break;
		case VIWE:
			success = peekAuto(&themp, lager, scanInt("select an index"));
			if (success != 0) {
				break;
			}
			printAuto(themp);
			break;
		case REMOVE:
			success = removeAuto(&themp, lager, scanInt("select an index"));
			if (success != 0) {
				break;
			}
			freeCar(themp);
			break;
		case VIEW_ALL:
			printLager(lager);
			break;
		case VALUE:
			success = peekAuto(&themp, lager, scanInt("select an index"));
			if (success != 0) {
				break;
			}
			int wert = 0;
			autoWert(&wert,themp);
			printf("Value of car is %d\n",wert);
			break;
		default:
			success = -1;
			printf("Unknown selection\n");
		}
		printf("Exitcode is %d\n", success);
	}
	return 0;
}
