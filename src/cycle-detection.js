/*
https://www.hackerrank.com/challenges/detect-whether-a-linked-list-contains-a-cycle/problem
Cycle Detection
*/

let currentLine = 0;

const inputString = ["1", "1", "3", "1", "2", "3"];

function readLine() {
  return inputString[currentLine++];
}

/*
 * For your reference:
 * Self implemented Linked List since there is an error in Hackerrank
 *
 * SinglyLinkedListNode {
 *     int data;
 *     SinglyLinkedListNode next;
 * }
 *
 */

class SinglyLinkedListNode {
  constructor(data, next = null) {
    this.data = data;
    this.next = next;
  }
}
class SinglyLinkedList {
  constructor() {
    this.head = null;
    this.size = 0;
  }
  insertNode(data) {
    let newNode = new SinglyLinkedListNode(data);

    if (this.head === null) {
      this.head = newNode;
    } else {
      let currentNode = this.head;
      while (!!currentNode.next) {
        currentNode = currentNode.next;
      }

      currentNode.next = newNode;
    }
    this.size++;
  }
}
// Complete the hasCycle function below.
function hasCycle(head) {
  let fast = head;
  let slow = head;

  while (fast && fast.next) {
    fast = fast.next.next;
    slow = slow.next;

    if (fast === slow) return 1;
  }
  return 0;
}

function main() {
  console.log("inputString ", inputString);

  const tests = parseInt(readLine(), 10);

  for (let testsItr = 0; testsItr < tests; testsItr++) {
    const index = parseInt(readLine(), 10);

    const llistCount = parseInt(readLine(), 10);

    let llist = new SinglyLinkedList();

    for (let i = 0; i < llistCount; i++) {
      const llistItem = parseInt(readLine(), 10);
      llist.insertNode(llistItem);
    }

    let extra = new SinglyLinkedListNode(-1);
    let temp = llist.head;

    for (let i = 0; i < llistCount; i++) {
      if (i == index) {
        extra = temp;
      }

      if (i != llistCount - 1) {
        temp = temp.next;
      }
    }

    temp.next = extra;

    let result = hasCycle(llist.head);

    console.log("result:", result);
  }
}

main();
