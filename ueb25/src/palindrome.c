/*
 * palindrome.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include <palindrome.h>

int palindrome(char *s) {

	int sizeS = 0;

	while (s[sizeS] != 0) {

		sizeS++;
	}

	sizeS--;

	for(int i = 0; i < (sizeS+1)/2; i++) {

		if(s[i] != s[sizeS - i]) {

			return 1;
		}
	}

	return 0;
}
