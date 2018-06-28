/*
 * X.c
 *
 *  Created on: 27.06.2018
 *      Author: Dominik
 *      Author: Adam
 */

#include <stdio.h>

int x (void) {

	int col, row = 0;
	char c;

	printf("Bitte Anzahl der Reihen bestimmen\n\n");
	scanf("%d", &row);
	while ((c = getchar()) != '\n' && c != EOF) { }
	printf("Bitte Anzahl der Spalten bestimmen\n\n");
	scanf("%d", &col);
	while ((c = getchar()) != '\n' && c != EOF) { }

	char x[row][col];


	for(int c = 0; c < col; c++) {
		for(int r = 0; r < row; r++) {

			x[c][r]='-';
			if((c - r) == 0 || (c + r) == (col - 1)) {

				x[c][r]='*';
			}
		}
	}

	for(int c = 0; c < col; c++) {

			for(int r = 0; r < row; r++) {

				printf("%c", x[c][r]);

				if(r == (row - 1)) {
					printf("\n");
				}
			}
		}

	return 0;
}
