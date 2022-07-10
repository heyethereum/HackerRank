/*
https://www.hackerrank.com/challenges/tower-breakers-1/problem
Tower Breakers 1
*/

let currentLine = 0;

const inputString = ["2", "2 2", "1 4"];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'towerBreakers' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER n
 *  2. INTEGER m
 */

function towerBreakers(n, m) {
  // Write your code here
  let winner = 0;
  m == 1 || n % 2 == 0 ? (winner = 2) : (winner = 1);

  return winner;
}

function main() {
  console.log("inputString ", inputString);

  const t = parseInt(readLine().trim(), 10);

  for (let tItr = 0; tItr < t; tItr++) {
    const firstMultipleInput = readLine().replace(/\s+$/g, "").split(" ");

    console.log("firstMultipleInput:", firstMultipleInput);

    const n = parseInt(firstMultipleInput[0], 10);

    const m = parseInt(firstMultipleInput[1], 10);

    const result = towerBreakers(n, m);
    console.error("result ", result);
  }
}

main();
