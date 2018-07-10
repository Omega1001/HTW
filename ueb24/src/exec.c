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

#define a2p(arr) &arr[0][0]

int main(){
	int success = 0;
	int arr[3][5] = {{1,2,3,4,5},{10,20,30,40,50},{9,8,7,6,0}};
	print_matrix(a2p(arr),3,5);
	printf("max_col = %d\n",find_max_in_col(a2p(arr),2,3,5));
	printf("max_row = %d\n",find_max_in_row(a2p(arr),2,3,5));
	swap_rows(a2p(arr),0,2,3,5);
	puts("swap rows 0 and 2");
	print_matrix(a2p(arr),3,5);

	//uebung 2
	success = run();
	printf("Interactive test program exited with code %d\n",success);
}
