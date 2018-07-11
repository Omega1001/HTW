/*
 * matrix.c
 *
 *  Created on: 09.07.2018
 *      Author: jannik
 */
#include "matrix.h"
#include <stdio.h>
#include <limits.h>

#define get2d(arr,row,col,colcount) arr[(row * colCount) + col]

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
	int res = INT_MIN;
	for (int i = 0; i < rowCount; i++) {
		if (res < get2d(arr, i, col, colCount)) {
			res = get2d(arr, i, col, colCount);
		}
	}
	return res;
}

int find_max_in_row(int * arr, int row, int rowCount, int colCount) {
	int res = INT_MIN;
	for (int i = 0; i < colCount; i++) {
		if (res < get2d(arr, row, i, colCount)) {
			res = get2d(arr, row, i, colCount);
		}
	}
	return res;
}

void swap_rows(int * arr, int rowA, int rowB, int rowCount, int colCount) {
	int themp = 0;
	for (int i = 0; i < colCount; i++) {
		themp = get2d(arr, rowA, i, colCount);
		get2d(arr,rowA,i,colCount)= get2d(arr,rowB,i,colCount);
		get2d(arr,rowB,i,colCount)= themp;
	}
}


//===== 5. Aufgabe ===============================================
// Expect both functions to be passed proper matrices!
void sum (int m1 [], int m2 [] , int res [], int rows, int cols) {

	for (int i = 0; i < rows * cols; i++) {

		res[i] = m1[i] + m2[i];
	}
}

void mult (int m1 [], int m2 [], int res [], int m1_rows, int m1_cols, int m2_rows, int m2_cols) {

	for(int i = 0; i < m1_cols * m2_rows; i++) {

		res[i] = 0;
	}

	for(int i = 0; i < m1_rows; i++){

		for(int j = 0; j < m2_cols; j++){

			for(int k = 0; k < m1_cols; k++){

				res[i * m2_cols + j] += m1[i * m1_cols + k] * m2[k * m2_cols + j];

			}
		}
	}
}
