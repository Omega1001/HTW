/*
 * stringInteractive.c
 *
 *  Created on: 10.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include <stdlib.h>
#include "string2.h"
#include "stringInteractive.h"
#include "readAndClear.h"

#define EXIT 			0
#define STRCMP			1
#define STRSPN			2
#define STRLEN			3
#define FIRSTINDEXOF	4
#define LASTINDEXOF		5
#define REPLACE			6
#define TOUPPERCASE		7
#define SWAP			8


int runString() {

	int select = -1;

	char * s1;
	char * s2;
	char c1;
	char c2;

	while (select != 0) {
		printf(
				"Exit : %d\nstrcmp : %d\nstrspn : %d\nstrlen : %d\nfirstIndexOf "
				": %d\nlastIndexOf : %d\nreplace : %d\ntoUpperCase : %d\nswap : %d\n",
				EXIT, STRCMP, STRSPN, STRLEN, FIRSTINDEXOF, LASTINDEXOF, REPLACE, TOUPPERCASE, SWAP);

		select = scanInt("Select an option");
		switch (select) {

		case EXIT:
			break;

		case STRCMP:

			s1 = scanString("String 1 eingeben");
			s2 = scanString("String 2 eingeben");

			printf("strcmp return value: %d\n",strcmp(s1, s2));

			break;

		case STRSPN:

			s1 = scanString("String 1 eingeben");
			s2 = scanString("String 2 eingeben");

			printf("strspn return value: %d\n",strspn(s1, s2));

			break;

		case STRLEN:

			s1 = scanString("String eingeben");

			printf("strlen return value: %d\n",strlen(s1));

			break;

		case FIRSTINDEXOF:

			s1 = scanString("String eingeben");
			c1 = scanChar("Char eingeben");

			printf("firstIndexOf return value: %d\n",firstIndexOf(s1, c1));

			break;


		case LASTINDEXOF:

			s1 = scanString("String eingeben");
			c1 = scanChar("Char eingeben");

			printf("firstIndexOf return value: %d\n",firstIndexOf(s1, c1));

			break;

		case REPLACE:

				s1 = scanString("String eingeben");
				c1 = scanChar("Char 1 eingeben");
				c2 = scanChar("Char 2 eingeben");

				printf("replace return value: %d\n",replace(s1, c1, c2));

				printf("String 1 ist nun: %s\n",s1);

				break;

		case TOUPPERCASE:

				s1 = scanString("String eingeben");

				printf("toUpperCase return value: %d\n",toUpperCase(s1));
				printf("String 1 ist nun: %s\n",s1);

				break;

		case SWAP:

				s1 = scanString("String 1 eingeben");
				s2 = scanString("String 2 eingeben");

				printf("String 1 ist: %s\n",s1);
				printf("String 2 ist: %s\n",s2);

				printf("swap return value: %d\n",swap(&s1, &s2));

				printf("String 1 ist nun: %s\n",s1);
				printf("String 2 ist nun: %s\n",s2);

				break;

		default:
			select = -1;
			printf("Unknown selection\n");
		}
	}

	return 0;
}
