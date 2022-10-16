/*
https://www.hackerrank.com/challenges/birthday-cake-candles/problem
Birthday cake candles
*/
let inputString = ["4 4 1 3"];
let currentLine = 0;

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'birthdayCakeCandles' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY candles as parameter.
 */

function birthdayCakeCandles(candles) {
  // Write your code here
  const number = candles.filter((candle) => candle === Math.max(...candles));
  return number.length;
}

function main() {
  const candles = readLine()
    .replace(/\s+$/g, "")
    .split(" ")
    .map((candlesTemp) => parseInt(candlesTemp, 10));

  const result = birthdayCakeCandles(candles);

  console.log(result);
}

main();
