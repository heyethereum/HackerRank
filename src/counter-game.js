/*
https://www.hackerrank.com/challenges/counter-game/problem
Counter Game
*/

let inputString = ["1", "132"];
let currentLine = 0;

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'counterGame' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts LONG_INTEGER n as parameter.
 */

function counterGame(n) {
  // Write your code here
  let counter = 0;
  while (n > 1) {
    let power = parseInt(Math.log2(n));
    let max = 2 ** power;

    n = n === max ? parseInt(n / 2) : n - max;
    counter++;
  }
  return counter % 2 === 0 ? "Richard" : "Louise";
}

function main() {
  const t = parseInt(readLine().trim(), 10);

  for (let tItr = 0; tItr < t; tItr++) {
    const n = parseInt(readLine().trim(), 10);

    const result = counterGame(n);

    console.log(result);
  }
}
main();
