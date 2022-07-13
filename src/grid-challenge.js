/*
https://www.hackerrank.com/challenges/grid-challenge/problem
Grid Challenge
*/

let currentLine = 0;

const inputString = [
  "3",
  "3",
  "abc",
  "lmp",
  "qrt",
  "3",
  "mpxz",
  "abcd",
  "wlmf",
  "4",
  "abc",
  "hjk",
  "mpq",
  "rtv",
];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'gridChallenge' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING_ARRAY grid as parameter.
 */

function gridChallenge(grid) {
  // Write your code here
  const gridArray = grid.map((element) => element.split("").sort());
  //console.log("gridArray:", gridArray);

  for (let row = 0; row < gridArray.length - 1; row++) {
    for (let col = 0; col < gridArray[row].length; col++) {
      if (gridArray[row][col] > gridArray[row + 1][col]) return "NO";
    }
  }
  return "YES";
}

function main() {
  console.log("inputString ", inputString);

  const t = parseInt(readLine().trim(), 10);

  for (let tItr = 0; tItr < t; tItr++) {
    const n = parseInt(readLine().trim(), 10);

    let grid = [];

    for (let i = 0; i < n; i++) {
      const gridItem = readLine();
      grid.push(gridItem);
    }

    const result = gridChallenge(grid);

    console.log("Result:", result);
  }
}

main();
