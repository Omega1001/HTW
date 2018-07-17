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
#include "eratosthenesTest.h"
#include <stringTest.h>

int main(){

	setvbuf(stdout, NULL, _IONBF, 0);

////=== 1. und 3. Aufgabe ===
//
//	printf("Beginn Aufgabe 1 und 3\n\n");
//	mathTest();
//
////=== 2. Aufgabe ===
//
//	printf("\n\nBeginn Aufgabe 2\n\n");
//	palindromeTest();
//
////=== 4. Aufgabe ===
//
//	printf("\n\nBeginn Aufgabe 4\n\n");
//	//TODO
//
////=== 5. Aufgabe ===
//
//	printf("\n\nBeginn Aufgabe 5\n\n");
//	distanceTest();
//
////=== 6. Aufgabe ===
//
//	printf("\n\nBeginn Aufgabe 6\n\n");
//	test_eratosthenes();

//=== 7. Aufgabe ===

	printf("\n\nBeginn Aufgabe 7\n\n");
	testString();

//=== 8. Aufgabe ===

	printf("\n\nBeginn Aufgabe 8\n\n");
	//TODO

	return 0;
}
