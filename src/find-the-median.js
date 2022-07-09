/*
https://www.hackerrank.com/challenges/find-the-median/problem
Find the Median
*/

let currentLine = 0;

const inputString = ["7", "0 1 2 4 6 5 3"];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'findMedian' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

function findMedian(arr) {
  // Write your code here

  // sort array first
  arr.sort((a, b) => a - b);

  let midpoint = Math.floor(arr.length / 2);

  if (arr.length % 2) return arr[midpoint];

  return (arr[midpoint - 1] + arr[midpoint]) / 2;
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

  const result = findMedian(a);
  console.error("result ", result);
}

main();
