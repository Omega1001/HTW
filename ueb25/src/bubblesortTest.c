/*
 * bubblesortTest.c
 *
 *  Created on: 17.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include <stdlib.h>
#include <scanAndClear.h>
#include <bubblesort.h>
#include <bubblesortTest.h>

#define EXIT 			0
#define TEST			1

int test_bubble_sort() {

	int select = -1;
	int *arr = NULL;
	int siz = 0;

	while (select != 0) {

		printf("%d : Exit\n%d : Test\n\n", EXIT, TEST);

		select = scanInt("Select an option");

		switch (select) {

			case EXIT:
				break;

			case TEST:

				siz = scanInt("Size = ");

				if (siz < 1) {

					printf("Size must be bigger than 1!\n");
				}

				else {

					free(arr);
					arr = malloc(siz * sizeof(int));

					for(int i = 0; i < siz; i++) {

						arr[i] = scanInt("Wert = ");
					}

					sort(arr, siz);

					printf("Sorted Array: {%d", arr[0]);

					for(int i = 1; i < siz; i++) {

						printf(", %d", arr[i]);
					}

					printf("}\n\n");

					free(arr);
				}
				break;

			default:
				select = -1;
				printf("Unknown selection\n");
		}
	}

	return 0;
}
