/*
 * matrixInteractive.c
 *
 *  Created on: 10.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include <stdlib.h>
#include "matrix.h"
#include "matrixInteractive.h"
#include "scanAndClear.h"

#define EXIT 			0
#define SCAN_MATRICES	1
#define PRINT_MATRIX	2
#define FIND_MAX_IN_COL	3
#define FIND_MAX_IN_ROW	4
#define SWAP_ROWS		5
#define MIN_MAX_ROW				6
#define MIN_MAX_COL			7
#define CHANGE_M1		8
#define CHANGE_M2		9

int initCheck(int * a, int * b) {

	if (a == NULL && b == NULL) {

		printf("Matrizen mussen zuerst initialisiert werden!\n\n");
		return 1;
	}

	if (a == NULL) {

		printf("Matrix 1 muss zuerst initialisiert werden!\n\n");
		return 2;
	}

	if (b == NULL) {

		printf("Matrix 2 muss zuerst initialisiert werden!\n\n");
		return 3;
	}

	return 0;
}

int runMatrix() {

	int select = -1;

	int * m1 = NULL;
	int * m2 = NULL;
	int m1_rows = 0;
	int m1_cols = 0;
	int m2_rows = 0;
	int m2_cols = 0;
	int a = 0;
	int b = 0;
	int min = 0;
	int max = 0;

	while (select != 0) {
		printf(
				"Exit : %d\nre_scan_Matrices : %d\nprint_matrix : %d\nfind_max_in_col : %d\n"
						"find_max_in_row : %d\nswap_rows : %d\nmin max row : %d\nmin max col : %d\nchange_m1 : %d\nchange_m2 : %d\n",
				EXIT, SCAN_MATRICES, PRINT_MATRIX, FIND_MAX_IN_COL,
				FIND_MAX_IN_ROW, SWAP_ROWS, MIN_MAX_ROW, MIN_MAX_COL, CHANGE_M1,
				CHANGE_M2);

		select = scanInt("Select an option");
		switch (select) {

		case EXIT:
			break;

		case SCAN_MATRICES:

			m1_rows = scanInt("Reihenanzahl m1 : ");
			m1_cols = scanInt("Spaltenanzahl m1 : ");

			free(m1);
			m1 = malloc(m1_rows * m1_cols * sizeof(int));
			//m1 = (int*) realloc(m1, m1_rows * m1_cols * sizeof(int));

			for (int i = 0; i < m1_rows * m1_cols; i++) {
				int themp = scanInt("Nachster Wert der matrix : ");
				printf("put at %d : %d", i, themp);
				m1[i] = themp;
			}

			m2_rows = scanInt("Reihenanzahl m2 : ");
			m2_cols = scanInt("Spaltenanzahl m2 : ");

			m2 = (int*) realloc(m2, m2_rows * m2_cols * sizeof(int));

			for (int i = 0; i < m2_rows * m2_cols; i++) {

				m2[i] = scanInt("Nachster Wert der matrix : ");
			}

			break;

		case PRINT_MATRIX:

			if (initCheck(m1, m2) != 0) {

				break;
			}

			printf("m1:\n");
			print_matrix(m1, m1_rows, m1_cols);

			printf("m2:\n");
			print_matrix(m2, m2_rows, m2_cols);

			break;

		case FIND_MAX_IN_COL:

			if (initCheck(m1, m2) != 0) {

				break;
			}

			a = scanInt("Maximum welcher Spalte von m1 : ");
			printf("Maximum = %d\n", find_max_in_col(m1, a, m1_rows, m1_cols));

			b = scanInt("Maximum welcher Spalte von m2 : ");
			printf("Maximum = %d\n", find_max_in_col(m2, b, m2_rows, m2_cols));

			break;

		case FIND_MAX_IN_ROW:

			if (initCheck(m1, m2) != 0) {

				break;
			}

			a = scanInt("Maximum welcher Reihe von m1 : ");
			printf("Maximum = %d\n", find_max_in_row(m1, a, m1_rows, m1_cols));

			a = scanInt("Maximum welcher Reihe von m2 : ");
			printf("Maximum = %d\n", find_max_in_row(m2, a, m2_rows, m2_cols));

			break;

		case SWAP_ROWS:

			if (initCheck(m1, m2) != 0) {

				break;
			}

			a = scanInt("1. Reihe zum tauschen von m1 : ");
			b = scanInt("2. Reihe zum tauschen von m1 : ");
			swap_rows(m1, a, b, m1_rows, m1_cols);
			printf("Getauschtes m1:\n");
			print_matrix(m1, m1_rows, m1_cols);

			a = scanInt("1. Reihe zum tauschen von m2 : ");
			b = scanInt("2. Reihe zum tauschen von m2 : ");
			swap_rows(m2, a, b, m2_rows, m2_cols);
			printf("Getauschtes m2:\n");
			print_matrix(m2, m2_rows, m2_cols);

			break;

		case MIN_MAX_COL:

			a = scanInt("Maximum welcher Spalte von m1 : ");
			if (find_minmax_in_col(m1, a, m1_cols, &min, &max) == 0) {
				printf("Maximum = %d, Minimum = %d\n", max, min);
			} else {
				printf("Illegal argument");
			}

			a = scanInt("Maximum welcher Spalte von m2 : ");
			if (find_minmax_in_col(m2, a, m2_cols, &min, &max)) {
				printf("Maximum = %d, Minimum = %d\n", max, min);
			} else {
				printf("Illegal argument");
			}
			break;
		case MIN_MAX_ROW:

			a = scanInt("Maximum welcher Reihe von m1 : ");
			if (find_minmax_in_col(m1, a, m1_cols, &min, &max)) {
				printf("Maximum = %d, Minimum = %d\n", max, min);
			} else {
				printf("Illegal argument");
			}

			a = scanInt("Maximum welcher Reihe von m2 : ");
			if (find_minmax_in_col(m2, a, m2_cols, &min, &max)) {
				printf("Maximum = %d, Minimum = %d\n", max, min);
			} else {
				printf("Illegal argument");
			}
			break;

		case CHANGE_M1:

			m1_rows = scanInt("Reihenanzahl m1 : ");
			m1_cols = scanInt("Spaltenanzahl m1 : ");

			m1 = (int*) realloc(m1, m1_rows * m1_cols * sizeof(int));

			for (int i = 0; i < m1_rows * m1_cols; i++) {

				m1[i] = scanInt("Nachster Wert der matrix : ");
			}

			break;

		case CHANGE_M2:

			m2_rows = scanInt("Reihenanzahl m2 : ");
			m2_cols = scanInt("Spaltenanzahl m2 : ");

			m2 = (int*) realloc(m2, m2_rows * m2_cols * sizeof(int));

			for (int i = 0; i < m2_rows * m2_cols; i++) {

				m2[i] = scanInt("Nachster Wert der matrix : ");
			}

			break;

		default:
			select = -1;
			printf("Unknown selection\n");
		}
	}

	return 0;
}
