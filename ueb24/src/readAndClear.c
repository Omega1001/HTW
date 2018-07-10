/*
 * readAndClear.c
 *
 *  Created on: 10.07.2018
 *      Author: Dominik
 */

#include <readAndClear.h>
#include <stdio.h>
#include <stdlib.h>

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
	char * string = malloc(sizeof(char) * 21);

	while (readChars != 1) {
		fputs(message, stdout);
		fputs(" > ", stdout);
		readChars = scanf("%s", string);
		clearChar();
	}
	string[20] = 0;
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
