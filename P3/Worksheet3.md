# Worksheet 3: Error Handling and Seperation of Concerns

## Q1

 * Empty catch block

 If an exception is thrown, it is ignored. This is worse than not catching the error in the first place

  * File not closed if exception is thrown before `writer.close();` line.

Fix with a `finally` block closing the file rather than inside the `try` block.

 * Print statement saying the `"File successfully written"` even if it isn't.

Print statements should be in the `try`/`catch` blocks and should be representative of what happened

 * Catching base exception

Not giving different behaviour based on the exception. Instead, have multiple catch blocks, eg. `FileNotFoundException` and `IOException`.

## Q2

### (a)

**Actors**

Autopilot software system, pilot, copilot, control tower communication, onboard sensors

**Concerns**

*Leaving for later*

## Q3

### (a)

Pass exceptions to the Player class. This means exception messages are handled by the right class to maintain cohesion. It can't modify or catch the exceptions because it is an interface.

### (b)

