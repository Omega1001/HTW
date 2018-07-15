/*
 * palindromeTest.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */
#include <stdio.h>
#include "palindromeTest.h"
#include "palindrome.h"
#include "scanAndClear.h"

int palindromeTest() {

	char *s = scanString("String = ");

	if (palindrome(s) == 0) {

		printf("%s ist ein Palindrom.", s);
	}
	else {

		printf("%s ist kein Palindrom.", s);
	}

	return 0;
}
