/*
 * MinMaxBitOpe.c
 *
 *  Created on: 27.06.2018
 *      Author: Dominik
 *      Author: Adam
 */

#include <stdio.h>

int minMaxBit(void) {

	char			cha 	= 0;
	short			sho 	= 0;
	int				inte	= 0;

	unsigned char	unsCha 	= 0;
	unsigned short	unsSho 	= 0;
	unsigned int	unsInt 	= 0;

	cha = ~cha << ((sizeof cha)*8-1);
	printf("char Minimum = %d\n", cha);
	cha = ~cha;
	printf("char Maximum = %d\n\n", cha);

	sho = ~sho << ((sizeof sho)*8-1);
	printf("short Minimum = %d\n", sho);
	sho = ~sho;
	printf("short Maximum = %d\n\n", sho);

	inte = ~inte << ((sizeof inte)*8-1);
	printf("int Minimum = %d\n", inte);
	inte = ~inte;
	printf("int Maximum = %d\n\n", inte);


	printf("unsigned char Minimum = %u\n", unsCha);
	unsCha = ~unsCha;
	printf("unsigned char Maximum = %u\n\n", unsCha);

	printf("unsigned short Minimum = %u\n", unsSho);
	unsSho = ~unsSho;
	printf("unsigned short Maximum = %u\n\n", unsSho);

	printf("unsigned int Minimum = %u\n", unsInt);
	unsInt = ~unsInt;
	printf("unsigned int Maximum = %u\n\n", unsInt);

	return 0;
}
