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

int printStructSize() {

	struct _Auto subject;
	struct _Motor motor;
	struct _Eigenschaften eigenschaften;

	Eigenschaften * e = NULL;
	generateEigenschaften(&e);


//	Eigenschaften * e = NULL;
//	generateEigenschaften(&e);
//	Motor * m = NULL;
//	Auto * subject = NULL;
//	convertMotor(&m, 12, 8, 200);
//	convertAuto(&subject, "BMW", 180, 4, true, e, m);

	printf("Size of _Auto: %i\n\n", sizeof(struct _Auto));

	printf("Size of marke: %i\n", sizeof(subject.marke));
	printf("marke address: %p, offset: %d\n\n", &(subject.marke), (int)&(subject.marke) - (int)&subject);

	printf("Size of tueren: %i\n", sizeof(subject.tueren));
	printf("tueren address: %p, offset: %d\n\n", &(subject.tueren),
			(int)&subject.tueren - (int)&subject);

	printf("Size of abs: %i\n", sizeof(subject.abs));
	printf("abs address: %p, offset: %d\n\n", &subject.abs,
			(int)&subject.abs - (int)&subject);

	printf("Size of eigenschaften: %i\n", sizeof(subject.eigenschaften));
	printf("eigenschaften address: %p, offset: %d\n\n", &subject.eigenschaften,
			(int)&subject.eigenschaften - (int)&subject);

	printf("Size of motor: %i\n", sizeof(subject.motor));
	printf("motor address: %p, offset: %d\n\n", &subject.motor,
			(int)&subject.motor - (int)&subject);

	printf("Size of motor.ps: %i\n", sizeof(subject.motor->ps));
	printf("motor.ps address: %p, offset: %d\n\n", &subject.motor->ps,
			(int)&subject.motor->ps - (int)&subject);

	printf("Size of motor.zylinder: %i\n", sizeof(subject.motor->zylinder));
	printf("motor.zylinder address: %p, offset: %d\n\n", &subject.motor->zylinder,
			(int)&subject.motor->zylinder - (int)&subject);

	printf("Size of motor.hubraum: %i\n", sizeof(subject.motor->hubraum));
	printf("motor.hubraum address: %p, offset: %d\n\n", &subject.motor->hubraum,
			(int)&subject.motor->hubraum - (int)&subject);

	printf("Size of _Eigenschaften: %i\n", sizeof(struct _Eigenschaften));
	printf("eigenschaften.eigenschaften address: %p, offset: %d\n\n", &eigenschaften.eigenschaften,
				(int)&eigenschaften.eigenschaften - (int)&eigenschaften);

	printf("Size of e: %i\n", sizeof(e));
	printf("e.eigenschaften address: %p, offset: %d\n\n", &e->eigenschaften,
					(int)&e->eigenschaften - (int)&e);

	printf("Size of _Motor: %i\n\n", sizeof(struct _Motor));

	printf("Size of motor.ps: %i\n", sizeof(motor.ps));
	printf("motor.ps address: %p, offset: %d\n\n", &motor.ps,
			(int)&motor.ps - (int)&motor);

	printf("Size of motor.zylinder: %i\n", sizeof(subject.motor->zylinder));
	printf("motor.zylinder address: %p, offset: %d\n\n", &motor.zylinder,
			(int)&motor.zylinder - (int)&motor);

	printf("Size of motor.hubraum: %i\n", sizeof(subject.motor->hubraum));
	printf("motor.hubraum address: %p, offset: %d\n\n", &motor.hubraum,
			(int)&motor.hubraum - (int)&motor);

	return 0;
}
