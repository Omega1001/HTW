/*
 * main.c
 *
 *  Created on: 15.07.2018
 *      Author: Dominik
 */

#include <stdio.h>
#include <mathTest.h>
#include <palindromeTest.h>
#include <distanceTest.h>

int main(){

	setvbuf(stdout, NULL, _IONBF, 0);

//=== 1. und 3. Aufgabe ===

	mathTest();

//=== 2. Aufgabe ===

	palindromeTest();

//=== 5. Aufgabe ===

	distanceTest();

	return 0;
}
