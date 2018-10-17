package com.example.haoran_wang.matrix

import java.util.concurrent.Executors

object MatrixModel {

    private val matrix = arrayOf(
            arrayOf(1, 2, 3, 4, 5, 6),
            arrayOf(2, 3, 4, 5, 6, 7),
            arrayOf(3, 4, 5, 6, 7, 8),
            arrayOf(4, 5, 6, 7, 8, 9),
            arrayOf(5, 6, 7, 8, 9, 0))

    private val newMatrix = arrayOf(
            arrayOf(3, 4, 5),
            arrayOf(4, 5, 6),
            arrayOf(5, 6, 7))

    private val executors = Executors.newFixedThreadPool(5)

    fun calculationContain(onCalculated: (Int) -> Unit) {
        executors.execute {
            var count = 0
            var exCount = 0
            var matrixCount = 0
            val row = matrix.size
            for (i in 0 until matrix.size) {
                val array = matrix[i]
                val column = array.size
                for (j in 0 until column) {
                    if (calculationRowColumnLawful(i, j, row, column) &&
                            array[j] == newMatrix[0][0]) {
                        count++
                        executors.execute {
                            if (matrixContain(i, j, row, column)) {
                                matrixCount++
                            }
                            exCount++
                            if (count == exCount) {
                                onCalculated(matrixCount)
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 矩形对比
     */
    private fun matrixContain(row: Int, column: Int, totalRow: Int, totalColumn: Int): Boolean {
        var newMatrixRow = 0
        var containCount = 0
        for (i in row until totalRow) {
            var newMatrixColumn = 0
            for (j in column until totalColumn) {
                //逐个判断
                if (matrix[i][j] == newMatrix[newMatrixRow][newMatrixColumn]) {
                    containCount++
                    if (containCount == newMatrix.size * newMatrix[0].size) {
                        return true
                    }
                } else {
                    return false
                }
                newMatrixColumn++
                if (newMatrixColumn >= newMatrix[newMatrixRow].size) {
                    break
                }
            }
            newMatrixRow++
            if (newMatrixRow >= newMatrix.size) {
                break
            }
        }
        return false
    }

    /**
     * 计算当前行列数量是否可能产生所需矩阵
     */
    private fun calculationRowColumnLawful(row: Int, column: Int, totalRow: Int, totalColumn: Int) = !(rowLawful(row, totalRow)
            || columnLawful(column, totalColumn))

    /**
     * 计算当前行数是否可能产生所需矩阵
     */
    private fun rowLawful(row: Int, totalRow: Int) = (totalRow - row < newMatrix.size)

    /**
     * 计算当前列数是否可能产生所需矩阵
     */
    private fun columnLawful(column: Int, totalColumn: Int) = (totalColumn - column < newMatrix[0].size)
}

