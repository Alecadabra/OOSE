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

#### Actors

Autopilot software system, pilot, copilot, control tower communication, sensors, navigation system, UI

### (b)

#### Model (Storage of things)

Player character, inventory

#### View (User interaction)

UI, Menu, graphics, user input

#### Controller (Algorithms)

Physics engine, networking, movement

### (c)

UI, networking, play/pause, decoding and compression

## Q3

### (a)

Pass exceptions to the Player class. This means exception messages are handled by the right class to maintain cohesion. It can't modify or catch the exceptions because it is an interface.

### (b)

The player class should not be aware that an exception is a `PodDownloadException` or a `StreamConnectException` as these are specific to the implementation of the MediaLoader interface.

~~Player should catch an exception higher on the inheritance tree than `PodDownloadExceptiobn` and `StreamConnectException` and send the message to be handled by `UserInterface.showError()`.~~

Create a new exception eg. `MediaLoaderException`. Handle `PodDownloadException` and `StreamConnectException` and throw just the `MediaLoaderException`.

`play()` should throw just the `MediaLoaderException`.

```java
public Media load() throws MediaLoaderException
{
    ...
    catch(MediaLoaderException | PodDownloadException e)
    {
        ... // Handle exception properly
        throw new MediaLoaderException(e);
    }
    ...
}
```

## Q4

See AddressBook directory
