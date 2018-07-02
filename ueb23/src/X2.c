///*
// * X.c
// *
// *  Created on: 27.06.2018
// *      Author: Dominik
// *      Author: Adam
// */
//
//#include <stdio.h>
//
//int app2 (float a, float b, float del) {
//
//	float dis = a - b;
//
//	if(dis < 0) {
//
//		dis = dis * (-1);
//	}
//
//	if(dis > del) {
//
//		return 1;
//	}
//
//	return 0;
//}
//
//int x2 (int rei, int zei) {
//
//	int col, row = 0;
//
//	row = rei;
//	col = zei;
//
//	if (row < 1) {
//
//		printf("Anzahl der Reihen muss größer 0 sein!\n\n");
//
//		return 1;
//	}
//
//	if (col < 1) {
//
//		printf("Anzahl der Zeilen muss größer 0 sein.\n\n");
//
//		return 1;
//	}
//
//	char x[row][col];
//
//	float slope = ((float)col - 1) / ((float)row - 1);
//
//	for(int r = 0; r < row; r++) {
//
//		float s1 = slope * (float)r;
//		float s2 = (float)(col - 1) - s1;
//
//		for(int c = 0; c < col; c++) {
//
//			x[r][c]='-';
//
//			if(app2(s1, c, 0.2) == 0 || app2(s2, c, 0.2) == 0) {
//
//				x[r][c] = '*';
//			}
//		}
//	}
//
//	for(int r = 0; r < row; r++) {
//
//		for(int c = 0; c < col; c++) {
//
//				printf("%c", x[r][c]);
//
//				if(c == (col - 1)) {
//					printf("\n");
//				}
//			}
//		}
//
//	return 0;
//}
