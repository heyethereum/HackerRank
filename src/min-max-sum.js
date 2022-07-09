/*
https://www.hackerrank.com/challenges/mini-max-sum/problem
Mini-Max Sum
*/

let currentLine = 0;

const inputString = ["7 69 2 221 8974"];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'miniMaxSum' function below.
 *
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

function miniMaxSum(arr) {
  // Write your code here
  // sort array in accending order first
  arr.sort((a, b) => a - b);

  let minimum = 0,
    maximum = 0;

  arr.forEach((element, i) => {
    if (i != arr.length - 1) {
      minimum += element;
    }
    if (i != 0) {
      maximum += element;
    }
  });
  console.log(`${minimum} ${maximum}`);
}

function main() {
  console.log("inputString ", inputString);

  const a = readLine()
    .replace(/\s+$/g, "")
    .split(" ")
    .map((arrTemp) => parseInt(arrTemp, 10));

  console.error("arr ", a);
  console.error("length ", a.length);

  miniMaxSum(a);
}

main();
