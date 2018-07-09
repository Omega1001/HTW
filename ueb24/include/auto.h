/*
 * auto.h
 *
 *  Created on: 09.07.2018
 *      Author: jannik
 */

#include <stdbool.h>

#pragma once
struct _Eigenschaften{
	char * eigenschaften;
	unsigned char count;
};
typedef struct _Eigenschaften Eigenschaften;

struct _Motor {
	unsigned int ps;
	unsigned char zylinder;
	unsigned int hubraum;
};
typedef struct _Motor Motor;

struct _Auto {
	char * marke;
	unsigned int speed;
	unsigned char tueren;
	bool abs;
	Eigenschaften * eigenschaften;
	Motor * motor;
};
typedef struct _Auto Auto;

int convertAuto(Auto ** result,char * marke, unsigned int speed, unsigned char tueren, bool abs, Eigenschaften * eigenschaften, Motor * motor);

int convertMotor(Motor ** result, unsigned int ps, unsigned char zylinder,unsigned int hubraum);

int generateEigenschaften(Eigenschaften ** result);

int putEigenschaft(Eigenschaften * eigenschaften, char * toPut, int length);

int autoWert(int * result,Auto * a);

void printAuto(Auto * a);

int freeCar(Auto * a);
int freeEigenschaften(Eigenschaften * e);
