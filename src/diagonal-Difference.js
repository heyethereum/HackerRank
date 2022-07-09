/*
https://www.hackerrank.com/challenges/diagonal-difference/problem
Diagonal Difference
*/

let currentLine = 0;
const inputString2 = ["2", "11 2", "4 5"];

const inputString3 = ["3", "11 2 4", "4 5 6", "10 8 -12"];

const inputString4 = ["4", "11 2 4 5", "4 5 6 7", "10 8 -12 -5", "4 8 3 9"];

const inputString = inputString4;

function readLine() {
  return inputString[currentLine++];
}

function diagonalDifference(arr) {
  console.error("arr ", arr);
  console.error("length ", arr.length);
  // enter your code here
  let primary = 0,
    secondary = 0;

  arr.forEach((element, index) => {
    primary += element[index];
    secondary += element[arr.length - index - 1];
  });

  return Math.abs(primary - secondary);
}

function main() {
  console.log("inputString ", inputString);

  const n = parseInt(readLine(), 10);
  console.log("n ", n);

  const arr = Array(n);

  for (let i = 0; i < n; i++) {
    // prettier-ignore
    arr[i] = readLine().split(' ').map(arrTemp => parseInt(arrTemp, 10));
  }

  const result = diagonalDifference(arr);
  console.error("result ", result);
}

main();
