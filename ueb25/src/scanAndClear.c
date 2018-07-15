/*
 * scanAndClear.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include <stdlib.h>
#include "scanAndClear.h"

void clearChar() {
	char c;
	while ((c = getchar()) != '\n' && c != EOF) {
	}
}

int scanInt(char* message) {
	int read = -1;
	int readChars = 0;
	while (readChars != 1) {
		fputs(message, stdout);
		fputs(" > ", stdout);
		readChars = scanf("%d", &read);
		clearChar();
	}
	return read;
}

char * scanString(char* message) {
	int readChars = 0;
	char * string = malloc(sizeof(char) * 100);

	while (readChars != 1) {
		fputs(message, stdout);
		fputs(" > ", stdout);
		readChars = scanf("%s", string);
		clearChar();
	}
	string[99] = 0;
	return string;
}

char scanChar(char* message) {
	int readChars = 0;
	char c = 0;

	while (readChars != 1) {
		fputs(message, stdout);
		fputs(" > ", stdout);
		readChars = scanf("%c", &c);
		clearChar();
	}

	return c;
}

double scanDouble(char* message) {

	double read = -1;
	int readChars = 0;
	while (readChars != 1) {
		fputs(message, stdout);
		fputs(" > ", stdout);
		readChars = scanf("%lf", &read);
		clearChar();
	}
	return read;
}
