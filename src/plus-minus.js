/*
https://www.hackerrank.com/challenges/plus-minus/problem
Plus Minus
*/

/* eslint-disable no-plusplus */

let currentLine = 0;
const inputString1 = ["6", "-4 3 -9 0 4 1"];

const inputString = inputString1;

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'plusMinus' function below.
 *
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

function plusMinus(arr) {
  // Write your code here
  let positive = 0;
  let negative = 0;
  let zero = 0;

  arr.forEach((element) => {
    element > 0 ? positive++ : element < 0 ? negative++ : zero++;
  });
  console.log(`${(positive / arr.length).toFixed(6)}`);
  console.log(`${(negative / arr.length).toFixed(6)}`);
  console.log(`${(zero / arr.length).toFixed(6)}`);
}

function main() {
  const n = parseInt(readLine(), 10);
  console.log("n ", n);

  const arr = readLine()
    .split(" ")
    .map((arrTemp) => parseInt(arrTemp, 10));

  console.error("arr ", arr);
  console.error("length ", arr.length);

  plusMinus(arr);
}

main();
