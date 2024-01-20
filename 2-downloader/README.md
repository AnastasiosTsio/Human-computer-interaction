# Java SWING - Downloader
A lightweight file downloader written in Java that supports pausing, resuming, and displaying the progress of downloads in a progress bar.

### Usage

To use the downloader, first create a `Main` object and pass it the list of URLs of the files you want to download. Then, call the `downloadAll()` method to start all of the downloads. You can also call the `download()` method to download a single file.

```java
String[] urls = {"[https://example.com/file.zip](https://example.com/file.zip): [https://example.com/file.zip](https://example.com/file.zip)"};
new Main(urls).setVisible(true);
```

### Features

* Pause and resume downloads
* Display progress in a progress bar
* Download multiple files at the same time
* Download files based on filenames

### Requirements

* Java 8 or higher

### Building

To build the downloader, you will need to have Maven installed. Then, run the following command in the project directory:

bash
mvn clean install


This will create a JAR file called `downloader-0.0.1-SNAPSHOT.jar` in the `target` directory. You can then run the downloader by running the following command:

```bash
java -jar downloader-0.0.1-SNAPSHOT.jar <urls>
```
