/*
 * palindrome.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */

# include "palindrome.h"

int palindrome(char *s) {

	int sizeS = sizeof(s) / sizeof(char) - 1;

	for(int i = 0; i < sizeS/2; i++) {

		if(s[i] != s[sizeS - i]) {

			return 1;
		}
	}

	return 0;
}
