/*
https://www.hackerrank.com/challenges/tower-breakers-1/problem
Tower Breakers 1
*/

let currentLine = 0;

const inputString = ["38", "Always-Look-on-the-Bright-Side-of-Life", "5"];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'caesarCipher' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. STRING s
 *  2. INTEGER k
 */

function caesarCipher(s, k) {
  // Write your code here
  function shift(letter, shift, code) {
    return String.fromCharCode(
      ((letter.charCodeAt() - code + shift) % 26) + code
    );
  }
  return s
    .replace(/[a-z]/g, (char) => shift(char, k, 97))
    .replace(/[A-Z]/g, (char) => shift(char, k, 65));
}

function main() {
  console.log("inputString ", inputString);

  const n = parseInt(readLine().trim(), 10);

  console.log("N:", n);

  const s = readLine();

  const k = parseInt(readLine().trim(), 10);

  const result = caesarCipher(s, k);

  console.log("result:", result);
}

main();
