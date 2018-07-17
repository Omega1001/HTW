/*
 * palindromeTest.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */
#include <stdio.h>
#include <palindromeTest.h>
#include <palindrome.h>
#include <scanAndClear.h>

#define EXIT 			0
#define TEST			1

int palindromeTest() {

	int select = -1;
	char *s;

	while (select != 0) {

		printf("%d : Exit\n%d : Test\n\n", EXIT, TEST);

		select = scanInt("Select an option");

		switch (select) {

			case EXIT:
				break;

			case TEST:

				s = scanString("String = ");

				if (palindrome(s) == 0) {

					printf("%s ist ein Palindrom.\n", s);
				}

				else {

					printf("%s ist kein Palindrom.\n", s);
				}
				break;

			default:
				select = -1;
				printf("Unknown selection\n");
		}
	}

	return 0;
}
