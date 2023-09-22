# FileIOPlus - Enhanced File I/O Library for Java

FileIOPlus is a Java library designed to simplify file input/output operations. It provides convenient methods and mechanism for setting, storing and retrieving key-value pairs within a text file.

## Features

* Create, read, and modify files.
* Store and retrieve key-value pairs within a text file.
* Easily set and get integer and boolean values for specific keys.
* Add or subtract integers from existing values.
* Option to enable console output for debugging and informational purposes.

## Getting Started

### Prerequisites

#### To use FileIOPlus, you need:

* Java Development Kit (JDK) installed on your system.
* A Java project where you can include the FileIOPlus package.

### Installation

* Download the FileIOPlus package.
* Add the FileIOPlus package to your Java project.

### Usage

Here's a basic example of how to use FileIOPlus in your Java project:

```java
import plusfile.PlusFile;

public class FileIOExample {
    public static void main(String[] args) {
        // Create a new PlusFile instance
        PlusFile file = new PlusFile("data/mydata.txt", true);

        // Get the value associated with a key
        String value = file.getValue("myKey");
        System.out.println("Value for 'myKey': " + value);

        // Set a new value for a key
        file.setValue("newKey", "Hello, FileIOPlus!");

        // Get and print the updated value
        value = file.getValue("newKey");
        System.out.println("Value for 'newKey': " + value);
    }
}
```

Output:

```txt
New File: /home/markus/Documents/Projects/Java/FileManager_Test/data/mydata.txt
Creating File: /home/markus/Documents/Projects/Java/FileManager_Test/data/mydata.txt
Value for 'myKey': null
Value for 'newKey': Hello, FileIOPlus!
```

This creates the following file

```txt
newKey=Hello, FileIOPlus!

```

In this example, we create a `PlusFile` instance, retrieve and set values associated with keys, and enable console output for debugging.

## Documentation

For detailed documentation and API reference, please refer to the code comments within the `PlusFile` class.

## Contributing

## License
This project is licensed under the MIT License - see the [LICENSE]() file for details.
