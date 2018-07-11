/*
 * string.c
 *
 *  Created on: 09.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include "string2.h"

int strcmp(char *s1, char *s2) {

	for(int i = 0; s1[i] != 0 && s2[i] != 0; i++) {

		if(s1[i] > s2[i]) {

			return 1;
		}

		if(s1[i] < s2[i]) {

			return -1;
		}
	}

 return 0;
}

int strspn(char *s1, char *s2) {

	int i;

	for(i = 0; s1[i] != 0 && s2[i] != 0; i++) {

		if(s1[i] != s2[i]) {

			return i;
		}
	}

	if(s1[i] == 0) {

		return i;
	}

	return 0;
}

int strlen(char *s) {

	int i = 0;

	while(s[i] != 0) {

		i++;
	}

	return i;
}

int firstIndexOf(char *s, char c) {

	for(int i = 0; s[i] != 0; i++) {

		if(s[i] == c) {

			return i;
		}
	}

	return -1;
}

int lastIndexOf(char *s, char c){

	int i = -1;

	for(int j = 0; s[j] != 0; j++) {

		if(s[j] == c) {

			i = j;
		}
	}

	return i;
}


int replace(char *s, char c1, char c2) {

	for(int i = 0; s[i] != 0; i++) {

		if(s[i] == c1) {

			s[i] = c2;
		}
	}

	return 0;
}

int toUpperCase(char *s) {

	for(int i = 0; s[i] != 0; i++) {

		if(s[i] > 96 && s[i] < 123) {

			s[i] -= 32;
		}
	}

	return 0;
}

int swap(char **s1, char **s2) {

	char *themp;

	themp = *s1;
	*s1= *s2;
	*s2 = themp;
	return 0;
}
