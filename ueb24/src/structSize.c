/*
 * structSize.c
 *
 *  Created on: 09.07.2018
 *      Author: Dominik
 */

#include <structSize.h>
#include <auto.h>
#include <stdio.h>
#include <stdbool.h>

void printStructSize() {
	Eigenschaften * e = NULL;
	generateEigenschaften(&e);
	Motor * m = NULL;
	Auto * subject = NULL;
	convertMotor(&m, 12, 8, 200);
	convertAuto(&subject, "BMW", 180, 4, true, e, m);

	printf("Size of Auto: %lu\n\n", sizeof(*subject));
	printf("Size of marke: %lu\n", sizeof(subject->marke));
//	printf("marke address: %p, offset: %d\n\n", &(subject->marke), (int)&(subject.->marke) - (int)&(subject->marke));
//
//	printf("Size of tueren: %lu\n", sizeof(struct Auto.tueren));
//	printf("tueren address: %p, offset: %d\n\n", &_Auto.tueren,
//			(int) &_Auto.tueren - (int) &_Auto.tueren);
//
//	printf("Size of abs: %lu\n", sizeof(struct Auto.abs));
//	printf("abs address: %p, offset: %d\n\n", &_Auto.abs,
//			(int) &_Auto.abs - (int) &_Auto.abs);
//
//	printf("Size of Eigenschaften: %lu\n", sizeof(struct Auto.Eigenschaften));
//	printf("Eigenschaften address: %p, offset: %d\n\n", &_Auto.Eigenschaften,
//			(int) &_Auto.Eigenschaften - (int) &_Auto.Eigenschaften);
//
//	printf("Size of Motor: %lu\n", sizeof(struct Auto.Motor));
//	printf("Motor address: %p, offset: %d\n\n", &_Auto.Motor,
//			(int) &_Auto.Motor - (int) &_Auto.Motor);
//
//	printf("Size of Motor.ps: %lu\n", sizeof(struct Auto.Motor.ps));
//	printf("Motor.ps address: %p, offset: %d\n\n", &_Auto.Motor.ps,
//			(int) &_Auto.Motor.ps - (int) &_Auto.Motor.ps);
//
//	printf("Size of Motor.zylinder: %lu\n", sizeof(struct Auto.Motor.zylinder));
//	printf("Motor.zylinder address: %p, offset: %d\n\n", &_Auto.Motor.zylinder,
//			(int) &_Auto.Motor.zylinder - (int) &_Auto.Motor.zylinder);
//
//	printf("Size of Motor.hubraum: %lu\n", sizeof(struct Auto.Motor.hubraum));
//	printf("Motor.hubraum address: %p, offset: %d\n\n", &_Auto.Motor.hubraum,
//			(int) &_Auto.Motor.hubraum - (int) &_Auto.Motor.hubraum);

}
