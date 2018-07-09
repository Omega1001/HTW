/*
 * matrix.h
 *
 *  Created on: 09.07.2018
 *      Author: jannik
 */

#pragma once

void print_matrix(int * arr, int rowCount,int colCount);

int find_max_in_col(int * arr,int col, int rowCount,int colCount);

int find_max_in_row(int * arr,int row, int rowCount,int colCount);

void swap_rows(int * arr, int rowA, int rowB, int rowCount,int colCount);
