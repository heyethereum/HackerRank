/*
https://www.hackerrank.com/challenges/one-week-preparation-kit-recursive-digit-sum/problem
Recursive Digit Sum
*/

let currentLine = 0;

const inputString = ["9875 4"];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'superDigit' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. STRING n
 *  2. INTEGER k
 */

function superDigit(n, k) {
  // Write your code here
  n =
    [...n]
      .map((digit) => parseInt(digit, 10)) // convert string to numbers array
      .reduce((partialSum, a) => partialSum + a) * // sum of digits
      k +
    ""; // convert back to string

  // base case: if string is single digit exit recursive function
  return n.length > 1 ? superDigit(n, 1) : n;
}

function main() {
  console.log("inputString ", inputString);

  const firstMultipleInput = readLine().replace(/\s+$/g, "").split(" ");

  const n = firstMultipleInput[0];

  const k = parseInt(firstMultipleInput[1], 10);

  const result = superDigit(n, k);

  console.log("result:", result);
}

main();
