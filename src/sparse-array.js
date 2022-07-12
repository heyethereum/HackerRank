/*
https://www.hackerrank.com/challenges/sparse-arrays/problem
Sparse Arrays
*/

let currentLine = 0;

const inputString = [
  "4",
  "aba",
  "baba",
  "aba",
  "xzxb",
  "3",
  "aba",
  "xzxb",
  "ab",
];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'matchingStrings' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. STRING_ARRAY strings
 *  2. STRING_ARRAY queries
 */

function matchingStrings(strings, queries) {
  // Write your code here
  let countingArray = Array(queries.length).fill(0);
  strings.forEach((string) => {
    queries.forEach((query, index) => {
      if (string === query) countingArray[index]++;
    });
  });

  return countingArray;
}

function main() {
  console.log("inputString ", inputString);

  const stringsCount = parseInt(readLine().trim(), 10);

  let strings = [];

  for (let i = 0; i < stringsCount; i++) {
    const stringsItem = readLine();
    strings.push(stringsItem);
  }

  const queriesCount = parseInt(readLine().trim(), 10);

  let queries = [];

  for (let i = 0; i < queriesCount; i++) {
    const queriesItem = readLine();
    queries.push(queriesItem);
  }

  const res = matchingStrings(strings, queries);

  console.log(`result:\n${res.join("\n")}`);
}

main();
