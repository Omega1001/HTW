/*
 * PhysicsTest.c
 *
 *  Created on: 02.07.2018
 *      Author: Dominik
 *      Author: Jannik
 */

#include <stdio.h>
#include "Movement.h"
#include "UniformMovement.h"

void clearInput(){
	char c;
	while ((c = getchar()) != '\n' && c != EOF) { }
}

int run (void) {
	clearInput();
	int sel = -1;
	float w, x, y, z;

	while(sel != 0) {

		printf("Was?\n"
				"6: Geschwindigkeit\n"
				"5: Beschleunigung\n"
				"4: Impuls\n"
				"3: Weg einer gleichformigen geradlinigen Bewegung\n"
				"2: Weg einer gleichmasig beschleunigten geradlinigen Bewegung\n"
				"1: Geschwindigkeit einer gleichmasig beschleunigten geradlinigen Bewegung\n"
				"0: ENDE!\n");
		scanf("%i\n\n", &sel);

		if(sel < 0) {

			printf("Ungueltig!");
		}

		if(sel == 1) {

			printf("s = ");
			scanf("%f\n", &w);
			printf("t = ");
			scanf("%f\n", &x);
			printf("v = %f\n\n", v(w, x));
		}

		if(sel == 2) {

			printf("v = ");
			scanf("%f\n", &w);
			printf("t = ");
			scanf("%f\n", &x);
			printf("a = %f\n\n", a(w, x));
		}

		if(sel == 3) {

			printf("m = ");
			scanf("%f\n", &w);
			printf("v = ");
			scanf("%f\n", &x);
			printf("m = %f\n\n", p(w, x));
		}

		if(sel == 4) {

				printf("v = ");
				scanf("%f\n", &w);
				printf("t = ");
				scanf("%f\n", &x);
				printf("t0 = ");
				scanf("%f\n", &y);
				printf("s = %f\n\n", s(w, x, y));
		}

		if(sel == 5) {

				printf("v = ");
				scanf("%f\n", &w);
				printf("v0 = ");
				scanf("%f\n", &x);
				printf("a = ");
				scanf("%f\n", &y);
				printf("s = %f\n\n", uaS(w, x, y));
		}

		if(sel == 6) {

				printf("a = ");
				scanf("%f\n", &w);
				printf("t = ");
				scanf("%f\n", &x);
				printf("t0 = ");
				scanf("%f\n", &y);
				printf("v0 = ");
				scanf("%f\n", &z);
				printf("v = %f\n\n", uaV(w, x, y, z));
		}
		clearInput();
	}

	return 0;
}
