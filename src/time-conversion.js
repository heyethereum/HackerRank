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
  /* let militaryHour = "";
  let amPm = s.charAt(8);

  if (amPm == "A") {
    if (s.substring(0, 2) == "12") {
      militaryHour = "00";
    } else {
      militaryHour = s.substring(0, 2);
    }
  } else {
    // else if amPm == "P"
    if (s.substring(0, 2) == "12") {
      militaryHour = s.substring(0, 2);
    } else {
      militaryHour = parseInt(s.substring(0, 2), 10) + 12;
    }
  }
  return militaryHour + s.substring(2, 8); */
  const hour = s.slice(0, 2);
  if (s.slice(8) === "PM") {
    if (hour === "12") {
      return `12${s.slice(2, 8)}`;
    }
    return 1 * hour + 12 + s.slice(2, 8);
  }
  if (hour === "12") {
    return `00${s.slice(2, 8)}`;
  }
  return s.slice(0, 8);
}

function main() {
  const inputString = "01:05:45AM";
  console.log("inputString ", inputString);

  const result = timeConversion(inputString);

  console.log("Military Time: ", result);
}

main();
