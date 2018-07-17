/*
 * matrix.c
 *
 *  Created on: 09.07.2018
 *      Author: jannik
 */
#include <matrix.h>
#include <stdio.h>
#include <limits.h>

void print_matrix(int * arr, int rowCount, int colCount) {

	int sizeInt = sizeof(int);
	int *add = arr;

	for (int i = 0; i < rowCount; i++) {
		printf("| ");
		for (int i2 = 0; i2 < colCount; i2++) {
			printf("%.2d ", *add);
			add += sizeInt;
		}
		printf("|\n");
	}
}

int find_max_in_col(int * arr, int col, int rowCount, int colCount) {

	int res = INT_MIN;
	int sizeInt = sizeof(int);
	int *add = arr;

	for (int i = 0; i < rowCount; i++) {
		if (res < *add) {
			res = *add;
			*add = col * sizeInt;
		}
	}
	return res;
}

int find_max_in_row(int * arr, int row, int rowCount, int colCount) {

	int res = INT_MIN;
	int sizeInt = sizeof(int);
	int *add = arr + row * sizeInt;

	for (int i = 0; i < colCount; i++) {
		if (res < *add) {
			res = *add;
			add += sizeInt;
		}
	}
	return res;
}

void swap_rows(int * arr, int rowA, int rowB, int rowCount, int colCount) {

	int *themp = 0;
	int sizeInt = sizeof(int);
	int *addA = arr + rowA * sizeInt;
	int *addB = arr + rowB * sizeInt;

	for (int i = 0; i < colCount; i++) {
		themp = addA;
		addA = addB;
		addB = themp;
	}
}


int find_minmax_in_col(int * mat, int col, int colCount, int *min, int *max) {
	//TODO
	return 0;
}

int find_minmax_in_row(int * mat, int row, int rowCount, int *min, int *max) {
	//TODO
	return 0;
}
