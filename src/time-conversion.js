/*
https://www.hackerrank.com/challenges/time-conversion/problem
Time Conversion
*/

/*
 * Complete the 'timeConversion' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING s as parameter.
 */

function timeConversion(s) {
  // Write your code here
  let hour = s.slice(0, 2);

  if (s.slice(8) === "AM") {
    return hour === "12" ? "00" + s.slice(2, 8) : s.slice(0, 8);
  }
  return hour === "12"
    ? s.slice(0, 8)
    : parseInt(s.slice(0, 2)) + 12 + s.slice(2, 8);
}

function main() {
  const inputString = "01:05:45AM";
  console.log("inputString ", inputString);

  const result = timeConversion(inputString);

  console.log("Military Time: ", result);
}

main();
