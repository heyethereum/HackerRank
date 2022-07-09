/*
https://www.hackerrank.com/challenges/lonely-integer/problem
Lonely Integer
*/

let currentLine = 0;

const inputString = ["5", "0 0 1 2 1"];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'lonelyinteger' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY a as parameter.
 */

function lonelyinteger(a) {
  // Write your code here
  let lonely = a.filter(
    (element) => a.indexOf(element) === a.lastIndexOf(element)
  );

  return lonely[0];
}

function main() {
  console.log("inputString ", inputString);

  const n = parseInt(readLine().trim(), 10);
  console.log("n ", n);

  const a = readLine()
    .replace(/\s+$/g, "")
    .split(" ")
    .map((aTemp) => parseInt(aTemp, 10));

  console.error("arr ", a);
  console.error("length ", a.length);

  const result = lonelyinteger(a);
  console.error("result ", result);
}

main();
