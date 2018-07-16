/*
 * mathTest.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include <math2.h>
#include <mathTest.h>
#include <scanAndClear.h>

#define EXIT 			0
#define MULTIPLY		1
#define ADD				2

int mathTest() {

	int select = -1;
	int a ,b, res2;
	int *res;

	while (select != 0) {

		printf("%d : Exit\n%d : multiply\n%d : add\n\n", EXIT, MULTIPLY , ADD);

			select = scanInt("Select an option");

		switch (select) {

			case EXIT:
				break;

			case MULTIPLY:

				a = scanInt("a = ");
				b = scanInt("b = ");

				res = multiply(&a, &b);

				printf("%d * %d = %d\n", a, b, *res);

				break;

			case ADD:

				a = scanInt("a = ");
				b = scanInt("b = ");

				add(&a, &b, &res2);

				printf("%d + %d = %d\n", a, b, res2);


				break;

			default:
				select = -1;
				printf("Unknown selection\n");
		}
	}

	return 0;
}
