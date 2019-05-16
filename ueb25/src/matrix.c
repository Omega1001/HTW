/*
 * matrix.c
 *
 *  Created on: 09.07.2018
 *      Author: jannik
 */
#include <matrix.h>
#include <stdio.h>
#include <limits.h>

#define get2d(arr,row,col,colcount) *(arr+ ( ((row * colCount) + col)))

void print_matrix(int * arr, int rowCount, int colCount) {

	for (int i = 0; i < rowCount; i++) {
		printf("| ");
		for (int i2 = 0; i2 < colCount; i2++) {
			printf("%.2d ", get2d(arr, i, i2, colCount));
		}
		printf("|\n");
	}
}

int find_max_in_col(int * arr, int col, int rowCount, int colCount) {
	if (col >= colCount) {
		//Illegal Argument
		return 0;
	}

	int res = INT_MIN;
	int *add = arr;
	add += col;

	for (int i = 0; i < rowCount; i++) {
		if (res < *add) {
			res = *add;
			add += colCount;
		}
	}
	return res;
}

int find_max_in_row(int * arr, int row, int rowCount, int colCount) {
	if (row >= rowCount) {
		//Illegal Argument
		return 0;
	}
	int res = INT_MIN;
	int *add = arr;
	add += row;

	for (int i = 0; i < colCount; i++) {
		if (res < *add) {
			res = *add;
			add++;
		}
	}
	return res;
}

void swap_rows(int * arr, int rowA, int rowB, int rowCount, int colCount) {

	int themp = 0;
	int *addA = arr + rowA * colCount;
	int *addB = arr + rowB * colCount;

	for (int i = 0; i < colCount; i++) {
		themp = *addA;
		*addA = *addB;
		*addB = themp;

		addA++;
		addB++;
	}
}

int find_minmax_in_col(int * mat, int col, int colCount, int *min, int *max) {

	if (min == NULL || max == NULL) {
		//Invalid arguments
		return 1;
	}
	if (col >= colCount) {
		//Illegal Argument
		return 2;
	}

	*min = INT_MAX;
	*max = INT_MIN;

	for (int i = 0; i < colCount; i++) {
		if ( get2d(mat,i,col,colCount) < *min) {
			*min = get2d(mat, i, col, colCount);
		}
		if (get2d(mat,i,col,colCount) > *max) {
			*max = get2d(mat, i, col, colCount);
		}
	}
	return 0;
}

int find_minmax_in_row(int * mat, int row, int rowCount, int colCount, int *min,
		int *max) {
	if (min == NULL || max == NULL) {
		//Invalid arguments
		return 1;
	}
	if (row >= rowCount) {
		//Illegal Argument
		return 2;
	}

	*min = INT_MAX;
	*max = INT_MIN;

	for (int i = 0; i < colCount; i++) {
		if ( get2d(mat,row,i,colCount) < *min) {
			*min = get2d(mat, row, i, colCount);
		}
		if (get2d(mat,row,i,colCount) > *max) {
			*max = get2d(mat, row, i, colCount);
		}
	}
	return 0;
}
