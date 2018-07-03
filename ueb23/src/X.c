/*
 * X.c
 *
 *  Created on: 27.06.2018
 *      Author: Dominik
 *      Author: Adam
 */

#include <stdio.h>
#include "../include/other.h"

int app (float a, float b, float del) {

	float dis = a - b;

	if(dis < 0) {

		dis = dis * (-1);
	}

	if(dis > del) {

		return 1;
	}

	return 0;
}

int x (void) {

	int col, row = 0;
	char c;

	printf("Bitte Anzahl der Reihen bestimmen\n\n");
	scanf("%d", &row);
	while ((c = getchar()) != '\n' && c != EOF) { }

	if (row < 1) {

		printf("Anzahl der Reihen muss groesser 0 sein.\n\n");
		scanf("%d", &row);
		while ((c = getchar()) != '\n' && c != EOF) { }
	}

	printf("Bitte Anzahl der Spalten bestimmen\n\n");
	scanf("%d", &col);
	while ((c = getchar()) != '\n' && c != EOF) { }

	if (col < 1) {

		printf("Anzahl der Zeilen muss groesser 0 sein.\n\n");
		scanf("%d", &col);
		while ((c = getchar()) != '\n' && c != EOF) { }
	}

	char x[row][col];

	float slope = ((float)col - 1) / ((float)row - 1);

	for(int r = 0; r < row; r++) {

		float s1 = slope * (float)r;
		float s2 = (float)(col - 1) - s1;

		for(int c = 0; c < col; c++) {

			x[r][c]='-';

			if(app(s1, c, 0.2) == 0 || app(s2, c, 0.2) == 0) {

				x[r][c] = '*';
			}
		}
	}

	for(int r = 0; r < row; r++) {

		for(int c = 0; c < col; c++) {

				printf("%c", x[r][c]);

				if(c == (col - 1)) {
					printf("\n");
				}
			}
		}

	return 0;
}
