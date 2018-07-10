/*
 * lager.h
 *
 *  Created on: 10.07.2018
 *      Author: jannik
 */

#pragma once

#include <auto.h>
#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 10

struct _AutoLager{
	Auto ** entries;
	int maxSize;
	int size;
};
typedef struct _AutoLager AutoLager;

int createLager(AutoLager ** res);

void freeLager(AutoLager * lager);

int putAuto(AutoLager * lager, Auto * a);

int removeAuto(Auto ** res, AutoLager * lager, int index);

int peekAuto(Auto ** res,AutoLager * lager, int index);

