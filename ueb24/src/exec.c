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

int main(){
	int success = 0;
//	int arr[3][5] = {{1,2,3,4,5},{10,20,30,40,50},{9,8,7,6,0}};
//	print_matrix(arr,3,5);
//	printf("max_col = %d\n",find_max_in_col(arr,2,3,5));
//	printf("max_row = %d\n",find_max_in_row(arr,2,3,5));
//	swap_rows(arr,0,2,3,5);
//	puts("swap");
//	print_matrix(arr,3,5);

	Eigenschaften * e = NULL;
	success = generateEigenschaften(&e);
	putEigenschaft(e,"Test1",5);
	Motor * m = NULL;
	convertMotor(&m,12,4,200);
	Auto * a = NULL;
	convertAuto(&a,"1234567890123456789012",200,4,true,e,m);
	printAuto(a);
//	freeCar(a);
	puts("Begin 2");

	AutoLager * l = NULL;
	puts("Create Lager");
	success = createLager(&l);
	puts("put Lager");
	putAuto(l,a);
	Auto * a2 = NULL;
	puts("peek Lager");
	peekAuto(&a2,l,0);
	puts("remove Lager");
	removeAuto(&a2,l,0);
	puts("free Lager");
	printAuto(a2);
	freeCar(a2);
	freeLager(l);

}
