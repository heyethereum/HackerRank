/*
https://www.hackerrank.com/challenges/flipping-the-matrix/problem
Flipping the Matrix
*/

let currentLine = 0;

const inputString = [
  "1",
  "2",
  "112 42 83 119",
  "56, 125, 56, 49",
  "15, 78, 101, 43",
  "62, 98, 114, 108",
];

function readLine() {
  return inputString[currentLine++];
}
/*
 * Complete the 'flippingMatrix' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
 */

function flippingMatrix(matrix) {
  // Write your code here
  let matrixLength = matrix.length;
  let sum = 0;
  for (let row = 0; row < matrixLength / 2; row++) {
    for (let col = 0; col < matrixLength / 2; col++) {
      sum += Math.max(
        matrix[row][col],
        matrix[matrixLength - row - 1][col],
        matrix[row][matrixLength - col - 1],
        matrix[matrixLength - row - 1][matrixLength - col - 1]
      );
    }
  }
  return sum;
}

function main() {
  console.log("inputString ", inputString);

  const q = parseInt(readLine().trim(), 10);

  for (let qItr = 0; qItr < q; qItr++) {
    const n = parseInt(readLine().trim(), 10);
    let matrix = Array(2 * n);
    for (let i = 0; i < 2 * n; i++) {
      matrix[i] = readLine()
        .replace(/\s+$/g, "")
        .split(" ")
        .map((matrixTemp) => parseInt(matrixTemp, 10));
    }

    const result = flippingMatrix(matrix);
    console.error("result ", result);
  }
}

main();
