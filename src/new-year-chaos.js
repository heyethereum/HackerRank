/*
https://www.hackerrank.com/challenges/new-year-chaos/problem
New Year Chaos
*/

let currentLine = 0;

const inputString = ["2", "8", "5 1 2 3 7 8 6 4", "8", "1 2 5 3 7 8 6 4"];

function readLine() {
  return inputString[currentLine++];
}

/*
 * Complete the 'minimumBribes' function below.
 *
 * The function accepts INTEGER_ARRAY q as parameter.
 */

function minimumBribes(q) {
  // Write your code here
  let totalBribes = 0;
  for (let person = q.length - 1; person >= 0; person--) {
    if (q[person] != person + 1) {
      if (q[person - 1] == person + 1) {
        q[person - 1] = q[person];
        q[person] = person + 1;
        totalBribes++;
      } else if (q[person - 2] == person + 1) {
        q[person - 2] = q[person - 1];
        q[person - 1] = q[person];
        q[person] = person + 1;
        totalBribes += 2;
      } else {
        console.log("Too chaotic");
        return;
      }
    }
  }
  console.log(totalBribes);
}

function main() {
  console.log("inputString ", inputString);

  const t = parseInt(readLine().trim(), 10);

  for (let tItr = 0; tItr < t; tItr++) {
    const n = parseInt(readLine().trim(), 10);

    const q = readLine()
      .replace(/\s+$/g, "")
      .split(" ")
      .map((qTemp) => parseInt(qTemp, 10));

    console.log("q: ", q);
    minimumBribes(q);
  }
}

main();
