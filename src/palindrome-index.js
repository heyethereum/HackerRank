/*
https://www.hackerrank.com/challenges/palindrome-index/problem
Palindrome Index
*/

let currentLine = 0;

const inputString = ["3", "aaab", "baa", "aaa"];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'palindromeIndex' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts STRING s as parameter.
 */
function is_palind(s) {
  return s === [...s].reverse().join("");
}
function palindromeIndex(s) {
  // Write your code here
  let stringLength = s.length;
  if (stringLength <= 1) return -1;

  let mid = Math.floor(stringLength / 2);

  for (let i = 0; i < mid; i++) {
    // pointer at both ends string, if same, continue
    if (s.charAt(i) == s.charAt(stringLength - 1 - i)) continue;

    // remove character at start of sub-string and check is_palindrome, if true, return index of start character
    if (is_palind(s.substring(i + 1, stringLength - i))) {
      return i;
    }
    // remove character at end of sub-string annd check is_palindrome, if true, return index of ending character of substring
    if (is_palind(s.substring(i, stringLength - i - 1))) {
      return stringLength - i - 1;
    }
  }
  return -1;
}

function main() {
  console.log("inputString ", inputString);

  const q = parseInt(readLine().trim(), 10);

  for (let qItr = 0; qItr < q; qItr++) {
    const s = readLine();

    const result = palindromeIndex(s);

    console.log("result:", result);
  }
}

main();
