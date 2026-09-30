# Getting Started with Java in IntelliJ IDEA
Welcome to our Java course! In this guide, we'll walk you through creating your first Java class using IntelliJ IDEA, a powerful 
integrated development environment (IDE) for Java development.
### Prerequisites
Before getting started, ensure that you have the following installed on your system:
- IntelliJ IDEA (Community or Ultimate edition) https://www.jetbrains.com/idea/download/
- Java Development Kit (JDK) installed on your machine https://docs.aws.amazon.com/corretto/latest/corretto-25-ug/downloads-list.html
### Steps to Create Your First Java Class
1. #### Open IntelliJ IDEA : Launch IntelliJ IDEA on your system.
2. #### Create a New Project :
     - Click on Create New Project on the welcome screen or go to File > New > Project.
3. #### Set Up Your Project :
     - Enter a Project Name for your project (HelloWorld).
     - Choose a Project Location where you want to save your project files.
     - Select the Language as Java.
     - Build system as IntelliJ.
     - Add JDK as corretto-25 or click on New... and locate your JDK installation directory.
     - Click on Create to create the project.
4. #### Create a Java Class :
     - In the Project tool window (usually located on the left-hand side), right-click on the src folder.
     - Go to New > Java Class.
     - Enter a name for your class in the Name field (e.g., HelloWorld).
     - Optionally, you can specify a Package for your class. If you're just starting out, you can leave it as the default package.
     - Click OK to create the class.
5. #### Write Your First Java Code :
     - IntelliJ IDEA will create a Java class file with a template for you.
     - You can start writing your Java code inside the class.
 ```java
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello Madan");
    }

}
```

6. #### Run Your Java Program :
   - Click on the green Run icon next to the main method or right-click anywhere inside the main method and select Run 'HelloWorld.main()'.
7. #### View Output :
   - You should see the output "Hello Madan" printed in the Run tool window at the bottom of the IntelliJ IDEA window.

## Launch Single-File & Multi-File Source-Code Programs

The Single-File Source-Code Program feature, introduced in `Java 11`, allows developers to run Java programs directly from a source file without the need for separate compilation.

This was later enhanced in `Java 22` to support Multi-File Source-Code Programs, enabling the execution of Java applications that span across multiple `.java` files — still without explicit compilation.

### ⚙️ Traditional Java (Before Java 11)

Prior to `Java 11`, you had to compile your Java code before running it:

### 🧾 Example
```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello World...");
    }
}
```

▶️ Commands
```
javac Hello.java   # Compilation
java Hello         # Execution
```
Two distinct steps were required — compile first, then run.

### 🚀 From Java 11 — Single-File Source Programs

Starting in Java 11, you can execute a Java program directly from its source file using a single command.

### 🧾 Example
```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello World...");
    }
}
```

▶️ Command
```
java Hello.java
```
✅ The JVM automatically compiles and runs the file in one step.

**⚠️ Limitation :** Works only if all the code is within a single file.
If your logic spans multiple source files, Java 11 cannot handle it.


### 🧱 From Java 22 — Multi-File Source Programs

Java 22 extends this functionality — now you can run Java programs that use multiple source files (with dependencies) without manual compilation.

### 🧾 Example
```java
public class Hello {
    public static void main(String[] args) {
        Greetings.sayHello();
    }
}

public class Greetings {
    public static void sayHello() {
        System.out.println("Hello World...");
    }
}
```

▶️ Command
```
java Hello.java
```

✅ Even though Hello depends on another class (Greetings), `Java 22` automatically detects and compiles both files before execution.

### 🧠 How It Works

| Java Version       | Behavior                                                                      |
| ------------------ | ----------------------------------------------------------------------------- |
| **Before Java 11** | Must compile manually using `javac` before running.                           |
| **Java 11**        | Can run a **single file** directly (`java Hello.java`).                       |
| **Java 22**        | Can run **multi-file source programs** — dependencies included automatically. |

### 💡 Key Benefits

- **Faster Prototyping :** No need to compile separately.

- **Simpler Workflow :** Run `.java` files directly like scripts.

- **Multi-File Support :** `Java 22` expands usability to larger projects.

- **Perfect for Demos, Learning, and Automation Scripts**.

### 🧾 Compilation & Execution Summary
| Step                        | Before Java 11     | From Java 11              | From Java 22              |
| --------------------------- | ------------------ | ------------------------- | ------------------------- |
| **Compile**                 | `javac Hello.java` | *(Handled automatically)* | *(Handled automatically)* |
| **Execute**                 | `java Hello`       | `java Hello.java`         | `java Hello.java`         |
| **Supports Multiple Files** | ❌                  | ❌                         | ✅                         |
| **Ease of Use**             | Manual             | Simplified                | Seamless                  |

## Compact Source Files & Instance Main Methods

The Compact Source Files and Instance Main Methods features introduced in Java 25 make Java simpler and more approachable for beginners, while still being powerful for experienced developers.

These features reduce boilerplate and let you start coding Java applications without needing to define classes or static methods.

### 💻 Traditional vs. Compact Example
🧾 Before (Classic Java Program)
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

✅ After (Compact Source File in Java 25)
```java
void main() {
    IO.println("Hello World!");
}
```

### 🧠 Explanation

- Compact Source Files remove the need for explicit class definitions in simple programs.
- Instance Main Methods allow main() to be defined without the static keyword.
- This keeps code concise, beginner-friendly, and ideal for quick prototypes or lightweight scripts.

💡 The Java compiler automatically creates an implicit top-level class for compact source files.

### ⚙️ Instance Main Method Explained

Traditionally, Java required main() to be static:
```java

public static void main(String[] args) {  }
```

Now, in Java 25, the `main()` method can be non-static — the JVM automatically instantiates the implicit class to execute it.

🧾 Allowed Variations of main() in Compact Source Files
```java
void main() {}
public void main() {}
static void main() {}
public static void main() {}
public void main(String[] args) {}
public static void main(String[] args) {}
```
🧩 If both a `main(String[] args)` and a no-argument `main()` exist, the JVM prefers `main(String[] args)` as the program’s entry point.

### 💬 Why It Matters

- Beginner-Friendly: No need to understand classes, objects, or static context before writing your first program.
- Less Boilerplate: Write quick experiments, demos, or simple utilities faster.
- Flexible for Experts: Enables rapid prototyping before scaling into larger applications.



### 🔖 Summary
| Feature                   | Description                                       |
| ------------------------- | ------------------------------------------------- |
| **Compact Source Files**  | Write Java code without explicit class declarations. |
| **Instance Main Methods** | Define `main()` without `static`. JVM handles instantiation. |
| **Goal**                  | Simplify Java for beginners and enable quick prototyping. |
| **Status**                | Available in Java 25                              |

Congratulations! You've successfully created and executed your first Java class using IntelliJ IDEA.
