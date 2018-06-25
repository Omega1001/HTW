/*
 * A2.c
 *
 *  Created on: 24.06.2018
 *      Author: Dominik
 *      Author: ADAM
 */
 
#include <stdio.h>

int uebA1() {

	float netto = -1;
	float mwSt, brutto, skonto, betrag;
	printf("Nettopreis eingeben!\n");
	scanf("%f", &netto);
	
	while(netto < 0) {
			printf("POSITIVEN Preis eingeben!\n");
			printf("Nettopreis eingeben!\n");
			scanf("%f", &netto);
			char c;
			while ((c = getchar()) != '\n' && c != EOF) { }
		}

	mwSt = netto * 0.2f;
	brutto = netto + mwSt;
	skonto = brutto * 0.02f;
	betrag = brutto - skonto;
	
	printf("%-20s Euro %7.2f\n", "Nettopreis", netto);
	printf("%-20s Euro %7.2f\n", "+ 20% MwSt", mwSt);
	printf("=====================================\n");
	printf("%-20s Euro %7.2f\n", "Bruttopreis", brutto);
	printf("%-20s Euro %7.2f\n", "- 2% Skonto", skonto);
	printf("=====================================\n");
	printf("%-20s Euro %7.2f\n", "Rechnungsbetrag", betrag);
	
	return 0;
}
