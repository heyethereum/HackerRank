/*
https://www.hackerrank.com/challenges/staircase/problem
Staircase
*/

let inputString = ["7"];
let currentLine = 0;

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'staircase' function below.
 *
 * The function accepts INTEGER n as parameter.
 */

function staircase(n) {
  // Write your code here
  for (let i = 1; i <= n; i++) {
    const space = " ";
    const hex = "#";
    console.log(space.repeat(n - i) + hex.repeat(i));
  }
}

function main() {
  const n = parseInt(readLine().trim(), 10);

  staircase(n);
}

main();
