/*
https://www.hackerrank.com/challenges/queens-attack-2/problem
Queen's Attack ii
*/

let currentLine = 0;

const inputString0 = ["4 0", "4 4"]; // output: 9
const inputString1 = ["5 3", "4 3", "5 5", "4 2", "2 3"]; // output: 10
const inputString6 = [
  "100 100",
  "48 81",
  "54 87",
  "64 97",
  "42 75",
  "32 65",
  "42 87",
  "32 97",
  "54 75",
  "64 65",
  "48 87",
  "48 75",
  "54 81",
  "42 81",
  "45 17",
  "14 24",
  "35 15",
  "95 64",
  "63 87",
  "25 72",
  "71 38",
  "96 97",
  "16 30",
  "60 34",
  "31 67",
  "26 82",
  "20 93",
  "81 38",
  "51 94",
  "75 41",
  "79 84",
  "79 65",
  "76 80",
  "52 87",
  "81 54",
  "89 52",
  "20 31",
  "10 41",
  "32 73",
  "83 98",
  "87 61",
  "82 52",
  "80 64",
  "82 46",
  "49 21",
  "73 86",
  "37 70",
  "43 12",
  "94 28",
  "10 93",
  "52 25",
  "50 61",
  "52 68",
  "52 23",
  "60 91",
  "79 17",
  "93 82",
  "12 18",
  "75 64",
  "69 69",
  "94 74",
  "61 61",
  "46 57",
  "67 45",
  "96 64",
  "83 89",
  "58 87",
  "76 53",
  "79 21",
  "94 70",
  "16 10",
  "50 82",
  "92 20",
  "40 51",
  "49 28",
  "51 82",
  "35 16",
  "15 86",
  "78 89",
  "41 98",
  "70 46",
  "79 79",
  "24 40",
  "91 13",
  "59 73",
  "35 32",
  "40 31",
  "14 31",
  "71 35",
  "96 18",
  "27 39",
  "28 38",
  "41 36",
  "31 63",
  "52 48",
  "81 25",
  "49 90",
  "32 65",
  "25 45",
  "63 94",
  "89 50",
  "43 41",
]; // output: 40

function readLine() {
  return inputString6[currentLine++];
}

/*
 * Complete the 'queensAttack' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER n
 *  2. INTEGER k
 *  3. INTEGER r_q
 *  4. INTEGER c_q
 *  5. 2D_INTEGER_ARRAY obstacles
 */

function queensAttack(n, _k, qx, qy, obstacles) {
  // Write your code here
  let top = qy - 1;
  let right = n - qx;
  let bottom = n - qy;
  let left = qx - 1;

  let topLeft = Math.min(top, left);
  let topRight = Math.min(top, right);
  let bottomRight = Math.min(bottom, right);
  let bottomLeft = Math.min(bottom, left);

  obstacles.forEach(([x, y]) => {
    // top
    if (x === qx && y < qy) {
      top = Math.min(top, qy - y - 1);
    }
    // right
    if (y === qy && x > qx) {
      right = Math.min(right, x - qx - 1);
    }
    // bottom
    if (x === qx && y > qy) {
      bottom = Math.min(bottom, y - qy - 1);
    }
    // left
    if (y === qy && x < qx) {
      left = Math.min(left, qx - x - 1);
    }
    // topleft
    if (x < qx && y < qy && qx - x === qy - y) {
      topLeft = Math.min(topLeft, qx - x - 1);
    }
    // topRight
    if (x > qx && y < qy && x - qx === qy - y) {
      topRight = Math.min(topRight, x - qx - 1);
    }
    // bottomRight
    if (x > qx && y > qy && x - qx === y - qy) {
      bottomRight = Math.min(bottomRight, x - qx - 1);
    }
    // bottomLeft
    if (x < qx && y > qy && qx - x === y - qy) {
      bottomLeft = Math.min(bottomLeft, qx - x - 1);
    }
  });
  return (
    top + right + bottom + left + topRight + topLeft + bottomRight + bottomLeft
  );
}

function main() {
  console.log("inputString ", inputString6);

  const firstMultipleInput = readLine().replace(/\s+$/g, "").split(" ");

  const n = parseInt(firstMultipleInput[0], 10);

  const k = parseInt(firstMultipleInput[1], 10);

  const secondMultipleInput = readLine().replace(/\s+$/g, "").split(" ");

  const r_q = parseInt(secondMultipleInput[0], 10);

  const c_q = parseInt(secondMultipleInput[1], 10);

  let obstacles = Array(k);

  for (let i = 0; i < k; i++) {
    obstacles[i] = readLine()
      .replace(/\s+$/g, "")
      .split(" ")
      .map((obstaclesTemp) => parseInt(obstaclesTemp, 10));
  }

  const result = queensAttack(n, k, r_q, c_q, obstacles);

  console.log("result:", result);
}

main();
