/*
 * lager.c
 *
 *  Created on: 10.07.2018
 *      Author: jannik
 */
#include <lager.h>
#include <stdlib.h>
#include <stdio.h>

int createLager(AutoLager ** res) {
	if (res == NULL) {
		//Invalid return pointer
		return 1;
	}
	if (*res == NULL) {
		*res = malloc(sizeof(AutoLager));
		if (*res == NULL) {
			//Error allocating memory
			return 2;
		}
		(*res)->entries = NULL;
		(*res)->size = 0;
		(*res)->maxSize = MAX_SIZE;
	}
	if((*res)->entries == NULL){
		(*res)->entries = malloc(sizeof(Auto **)*MAX_SIZE);
				if (*res == NULL) {
					//Error allocating memory
					return 2;
				}
	}
	for (int i = 0; i < (*res)->maxSize; i++) {
		(*res)->entries[i] = NULL;
	}
	return 0;
}

void freeLager(AutoLager * lager) {
	if (lager == NULL) {
		// Nothing to do
		return;
	}
	free(lager->entries);
	free(lager);
}

int putAuto(AutoLager * lager, Auto * a) {
	if (lager == NULL) {
		//invalid data
		return 1;
	}
	if (a == NULL) {
		// Invalid Argument
		return 2;
	}
	if (lager->size < lager->maxSize) {
		lager->entries[lager->size] = a;
		lager->size++;
		return 0;
	} else {
		// no more room
		return 3;
	}

}

int removeAuto(Auto ** res, AutoLager * lager, int index) {
	if (lager == NULL) {
		//invalid data
		return 1;
	}
	if (index < 0 || index >= lager->size) {
		// Invalid Argument
		return 2;
	}
	*res = lager->entries[index];
	lager->entries[index] = NULL;
	index++;
	while (index < lager->maxSize && lager->entries[index] != NULL) {
		lager->entries[index - 1] = lager->entries[index];
		lager->entries[index] = NULL;
		index++;
	}
	lager->size--;
	return 0;
}

int peekAuto(Auto ** res, AutoLager * lager, int index) {
	if (lager == NULL) {
		//invalid data
		return 1;
	}
	if (index < 0 || index >= lager->size) {
		// Invalid Argument
		return 2;
	}
	*res = lager->entries[index];
	return 0;
}

void printLager(AutoLager * lager){
	for (int i = 0;i<lager->size;i++){
		printAuto(lager->entries[i]);
	}
}
