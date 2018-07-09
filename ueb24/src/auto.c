/*
 * auto.c
 *
 *  Created on: 09.07.2018
 *      Author: jannik
 */
#include <auto.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int convertAuto(Auto ** result, char * marke, unsigned int speed,
		unsigned char tueren, bool abs, Eigenschaften * eigenschaften,
		Motor * motor) {
	puts("Converting Car");
	if (result == NULL) {
		// Invalid return field
		return 1;
	}
	if (marke == NULL
			|| speed
					== 0|| tueren == 0 || eigenschaften == NULL || motor == NULL) {
		// Invalid Arguments
		return 2;
	}
	if (*result == NULL) {
		*result = malloc(sizeof(Auto));
		if (*result == NULL) {
			//unable to allocate memory
			return 3;
		}
	}
	(*result)->marke = marke;
	(*result)->speed = speed;
	(*result)->tueren = tueren;
	(*result)->abs = abs;
	(*result)->eigenschaften = eigenschaften;
	(*result)->motor = motor;
	return 0;
}

int convertMotor(Motor ** result, unsigned int ps, unsigned char zylinder,
		unsigned int hubraum) {
	puts("Converting motor");
	if (result == NULL) {
		// Invalid return field
		return 1;
	}
	if (ps == 0 || zylinder == 0 || hubraum == 0) {
		// Invalid Arguments
		return 2;
	}
	if (*result == NULL) {
		*result = malloc(sizeof(Motor));
		if (*result == NULL) {
			//unable to allocate memory
			return 3;
		}
	}
	(*result)->ps = ps;
	(*result)->zylinder = zylinder;
	(*result)->hubraum = hubraum;
	return 0;
}

bool strEquals(char * a, char * b) {
	bool done = false;
	int index = 0;
	if (a == NULL || b == NULL) {
		if (a == NULL && b == NULL) {
			return true;
		} else {
			return false;
		}
	}
	while (!done) {
		if (a[index] != b[index]) {
			return false;
		}
		if (a[index] == 0) {
			return true;
		}
		index++;
	}
	return false;
}

int autoWert(int * result, Auto * a) {
	puts("calc wert");
	if (result == NULL) {
		//Invalid result pointer
		return 1;
	}
	if (a == NULL) {
		//Invalid parameter
		return 2;
	}
	*result = 1 * a->speed * a->tueren;
	if (a->abs) {
		*result += 5000;
	}
	*result = 3000 * (a->eigenschaften)->count;
	if (strEquals((a->marke), "Porsche")) {
		*result *= 2;
	}
	return 0;
}
int generateEigenschaften(Eigenschaften ** result) {
	puts("Generate props");
	if (result == NULL) {
		//Invalid result pointer
		return 1;
	}
	if (*result == NULL) {
		*result = malloc(sizeof(Motor));
		if (*result == NULL) {
			//unable to allocate memory
			return 3;
		}
		(*result)->eigenschaften = NULL;
	}
	(*result)->count = 0;
	if ((*result)->eigenschaften == NULL) {
		puts("Locking memory");
		(*result)->eigenschaften = malloc(sizeof(char) * 1001);
		if ((*result)->eigenschaften == NULL) {
			return 3;
		}
	}
	puts("Begin init props");
	for (int i = 0; i < 100; i++) {
		(*result)->eigenschaften[i] = 0;
	}
	(*result)->count = 0;
	puts("Finish Generate props");
	return 0;
}

int putEigenschaft(Eigenschaften * eigenschaften, char * toPut, int length) {
	puts("setting prop");
	if (eigenschaften == NULL) {
		//Invalid source/result pointer
		return 1;
	}
	if (toPut == NULL) {
		//Invalid parameter
		return 2;
	}
	if (eigenschaften->count >= 10) {
		//Invalid state
		return 3;
	}
	int offset = eigenschaften->count * 100;
	for (int i = 0; i < 100 && i < length; i++) {
		eigenschaften->eigenschaften[offset + i] = toPut[i];
	}
	eigenschaften->count++;
	return 0;
}

int freeCar(Auto * a) {
	if (a == NULL) {
		//Nothing referenced here, nothing to do
		return 0;
	}
	freeEigenschaften(a->eigenschaften);
	free(a->motor);
	free(a);
	a = NULL;
	return 0;
}

int freeEigenschaften(Eigenschaften * e) {
	if (e == NULL) {
		//Nothing referenced here, nothing to do
		return 0;
	}
	free(e->eigenschaften);
	free(e);
	e = NULL;
	return 0;
}

void printEigenschaften(Eigenschaften * e, char * linePrefix) {
	for (int i = 0; i < e->count; i++) {
		printf("%s",linePrefix);
		for (int i2 = i * 1000; i2 < i * 1000 + 1000; i2++) {
			if (e->eigenschaften[i2] == 0) {
				break;
			}
			printf("%c", e->eigenschaften[i2]);
		}
		printf("\n");
	}
}

void printAuto(Auto * a) {
	printf("Auto:\n\tMarke : %s\n\tTopspeed : %d\n\tTuerenzahl : %d\n\tABS : ",
			a->marke, a->speed, a->tueren);
	if (a->abs) {
		printf("yes\n\t");
	} else {
		printf("no\n\t");
	}
	printf("Sonderausstattung :\n");
	printEigenschaften(a->eigenschaften, "\t\t");
	printf("\tMotor : \n\t\tPs : %d\n\t\tZylinder : %d\n\t\tHubraum %d\n",
			a->motor->ps, a->motor->zylinder, a->motor->hubraum);
}

