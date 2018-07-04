/*
 * MinMax.c
 *
 *  Created on: 24.06.2018
 *      Author: Dominik
 *      Author: Adam
 */
 
#include <stdio.h>
#include <limits.h>
#include "../include/other.h"

int minMax(void) {

	char			cha 	= 8;
	short			sho 	= 0;
	int				inte	= 0;
	
	unsigned char	unsCha 	= 0;
	unsigned short	unsSho 	= 0;
	unsigned int	unsInt 	= 0;
	
	int 			tem		= 0;
	
	printf("Calculation:\n\n");
	
	while(cha > tem) {
		
		tem = cha;
		cha *= 2;
		
		if (cha < tem){
		
			char chaMax = cha -1;
		
			printf("char Maximum = %d\n", chaMax);
			printf("char Minimum = %d\n\n", cha);
			
			sho = tem * 2;
			
			break;
		}
	}
	
	while(sho > tem) {
		
		tem = sho;
		sho *= 2;
		
		if (sho < tem){
		
			short shoMax = sho -1;
		
			printf("short Maximum = %d\n", shoMax);
			printf("short Minimum = %d\n\n", sho);
			
			inte = tem * 2;
		}
	}
	
	while(inte > tem) {
		
		tem = inte;
		inte *= 2;
		
		if (inte < tem){
			
			int intMax = inte -1;
		
			printf("int Maximum = %d\n", intMax);
			printf("int Minimum = %d\n\n", inte);
		}
	}
	
	unsigned char unsChaMax = unsCha -1;
	printf("unsigned char Maximum = %u\n", unsChaMax);
	printf("unsigned char Minimum = %u\n\n", unsCha);
	
	unsigned short unsShoMax = unsSho -1;
	printf("unsigned short Maximum = %u\n", unsShoMax);
	printf("unsigned short Minimum = %u\n\n", unsSho);
	
	unsigned int unsIntMax = unsInt -1;
	printf("unsigned int Maximum = %u\n", unsIntMax);
	printf("unsigned int Minimum = %u\n\n", unsInt);
	
	printf("limits.h:\n\n");
	
	printf("char Maximum = %d\n", CHAR_MAX);
	printf("cahr Minimum = %d\n\n", CHAR_MIN);
	
	printf("short Maximum = %d\n", SHRT_MAX);
	printf("short Minimum = %d\n\n", SHRT_MIN);
	
	printf("int Maximum = %d\n", INT_MAX);
	printf("int Minimum = %d\n\n", INT_MIN);
	
	printf("unsigned char Maximum = %u\n", UCHAR_MAX);
	printf("unsigned char Minimum doesn't exist in  limits.h!\n\n");
	
	printf("unsigned short Maximum = %u\n", USHRT_MAX);
	printf("unsigned short Minimum doesn't exist in  limits.h\n\n");
	
	printf("unsigned int Maximum = %u\n", UINT_MAX);
	printf("unsigned int Minimum doesn't exist in  limits.h\n\n");
	
	return 0;
}
