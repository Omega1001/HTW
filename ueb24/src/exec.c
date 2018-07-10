/*
 * exec.c
 *
 *  Created on: 09.07.2018
 *      Author: jannik
 */

#include <matrix.h>
#include <auto.h>
#include <stdio.h>
#include <lager.h>
#include <lagerInteracktive.h>
#include <structSize.h>
#include <stringInteractive.h>
#include <matrixInteractive.h>

#define a2p(arr) &arr[0][0]

int main(){

//	setvbuf(stdout, NULL, _IONBF, 0);

//==Aufgabe 1 und 5===========================================
	int success = 0;
	int arr[3][5] = {{1,2,3,4,5},{10,20,30,40,50},{9,8,7,6,0}};
	print_matrix(a2p(arr),3,5);
	printf("max_col = %d\n",find_max_in_col(a2p(arr),2,3,5));
	printf("max_row = %d\n",find_max_in_row(a2p(arr),2,3,5));
	swap_rows(a2p(arr),0,2,3,5);
	puts("swap rows 0 and 2");
	print_matrix(a2p(arr),3,5);

	printf("Aufgabe1 und 5:\n\n");
	success = runMatrix();
	printf("Interactive test program 1 exited with code %d\n\n",success);

//	uebung 2
	success = run();
	printf("Interactive test program exited with code %d\n\n",success);

//==Aufgabe 3=================================================

	printf("Aufgabe3:\n\n");
	success = printStructSize();
	printf("Interactive test program 3 exited with code %d\n\n",success);

//==Aufgabe 4=================================================

	printf("Aufgabe4:\n\n");
	success = runString();
	printf("Interactive test program 4 exited with code %d\n\n",success);
}
