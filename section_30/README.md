# 🧩 **Java 25 Features**

## **Module Import Declarations**

### 📘 Definition
The **Module Import Declarations** feature introduced in **Java 25** allows importing entire modules directly within a source file.  
Instead of importing individual packages or classes, developers can use a single statement such as:
```java
import module java.base;
```
This automatically includes all the packages exported by that module (for example, java.util, java.io, etc.), removing the need for 
multiple import statements.
### 💻 Code Example

🧾 Before (Traditional Imports)
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImportModule {
    public static void main(String[] args) {
        // your logic here
    }
}
```
✅ After (Using import module)
```java
import module java.base;

public class ImportModule {
    public static void main(String[] args) {
        // your logic here
    }
}
```
### 🧠 Code Explanation

- import module `java.base` → Imports the entire `java.base` module, making all exported packages available.
- Removes the need to explicitly import every class or package individually.
- Works perfectly for modular and single-file Java programs.

### ⚠️ Ambiguous Imports
When importing multiple modules, you might encounter classes with the same simple name (for example, java.util.Date 
and java.sql.Date), causing ambiguity.

Example — Ambiguous Import

```java
import module java.base;  // exports java.util.Date
import module java.sql;   // exports java.sql.Date

import java.util.Date;

Date d = new Date(); // ❌ Error: Ambiguous name!
```
Solution — Use a Single-Type Import
```java
import module java.base;  // exports java.util.Date
import module java.sql;   // exports java.sql.Date
import java.sql.Date;     // resolves the ambiguity

Date d = new Date(); // ✅ Now refers to java.sql.Date
```

🧩 Use single-type imports when multiple modules export classes with the same simple name.


### ⚙️ Additional Details
🔹 **Performance Impact**

Whether you import a specific class, or an entire package, or an entire module, only the classes that your code actually uses are 
loaded at runtime.

➡️ So, there’s no performance overhead with module imports.

🔹 **Compact Source File**

The java.base module is automatically imported on demand in a compact source file, reducing the boilerplate and keeping your code 
clean and minimal.

🔹 **Unnamed Module**

The import module statement requires a module name.  Hence, packages from the unnamed module (i.e., classpath code) cannot be 
imported using this feature.

## **Compact Source Files & Instance Main Methods**

Java 25 introduces Compact Source Files and Instance Main Methods to make Java more approachable and beginner-friendly.
These features simplify how you write, compile, and run Java code — reducing boilerplate and allowing programs to start without 
explicit class declarations or traditional main static methods.

### 💻 Classic vs. Compact Example
🧾 Before (Traditional HelloWorld)
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

✅ After (Compact Source File in Java 25)
```java
void main() {
    IO.println("Hello, World!");
}
```
### 🧠 Code Explanation

- **No class declaration needed :**  
The compiler automatically creates an implicit top-level class behind the scenes.
- **No static keyword required :**  
The main method can now be an instance method — Java will automatically create an instance to run it.
- **Automatic module import :**  
All public types from the java.base module (like List, Math, IO, etc.) are available automatically.

### 🧩 Key Features
#### 🧱 Implicit Class

When you write a compact source file:

- The compiler generates a final, top-level class automatically.
- The class extends `java.lang.Object` and has no explicit constructor or interfaces.
- All methods and fields in the file become members of that implicit class.
- It must include a launchable main method, or it will fail to compile.

#### ⚙️ Automatic Module Import
In compact source files, Java automatically imports everything from the `java.base` module.

```java

void main() {
    var list = List.of("Alice", "Bob", "Charlie");
    for (var name : list) {
        IO.println("Hello, " + name);
    }
}
```
➡️ No need to write import `java.util.List`

#### 💡 Instance Main Method Variants

The following method signatures are allowed:
```java
void main() {}
public void main() {}
static void main() {}
public static void main() {}
public static void main(String[] args) {}
```

When both `main(String[] args)` and `main()` exist, Java prioritizes `main(String[] args)`.

#### 🖥️ Console Interaction Simplified
A new utility class — `java.lang.IO` — allows simpler input/output:
```java
void main() {
    var name = IO.readln("Enter your name: ");
    IO.println("Hello, " + name);
}
```
- No need for Scanner or System.out.println.
- Makes interactive console programs beginner-friendly.

### 📝 How It Works

Compact source files:
- Are compiled directly (no class keyword required).
- Automatically import java.base.
- Must have a valid `main()` method.
- Are great for quick prototypes, educational purposes, and scripting.


## Flexible Constructor Bodies

The Flexible Constructor Bodies feature in Java 25 relaxes a long-standing rule in Java constructors.  Traditionally, the first 
statement of any constructor had to be a call to `super(...)` or `this(...)`.
With this enhancement, you can now execute statements before these calls, allowing for argument validation and preprocessing.

### 💻 Traditional vs. Flexible Example
🧾 Before (Strict Constructor Order)
```java
class Car extends Vehicle {
    Car(int speed) {
        super(speed); // Must be the first statement
        if (speed > 200) throw new IllegalArgumentException("Car too fast!");
    }
}
```

✅ After (Flexible Constructor Bodies in Java 25)
```java
class Car extends Vehicle {
    Car(int speed) {
        if (speed < 0) throw new IllegalArgumentException("Invalid speed");
        if (speed > 200) throw new IllegalArgumentException("Car too fast!");
        super(speed); // Can now be called later
    }
}
```

### 🧠Code Explanation

- **Before Java 25 :**   
The constructor chaining rule enforced `super()` or `this()` as the first statement.  Developers had to use static helper methods 
to validate or process arguments.
- **Now (Java 25) :**  
You can perform validations or compute values before invoking the superclass constructor.  This gives you flexibility without breaking 
the initialization order.

### ⚙️ Benefits
#### 🧩 1. Argument Validation
Developers can now check arguments before calling super()
#### 🧩 2. Improved Code Readability
No need for helper methods or static validators — logic stays within the constructor.
#### 🧩 3. Safer Initialization
Fixes issues where superclass constructors accidentally access uninitialized subclass fields:
```java

class Account {
    public Account() { check(); }
    public void check() {}
}

class SavingsAccount extends Account {
    private String accountNumber;
    public SavingsAccount(String number) {
        this.accountNumber = number;
        super(); // allowed after initialization
    }

    @Override
    public void check() {
        if (accountNumber.isEmpty()) throw new RuntimeException("Invalid account");
    }
}
```
### ⚠️ Restrictions

While this feature offers flexibility, certain rules still apply:
- ❌ Cannot use this or super before their invocation.
- ❌ Cannot access instance fields or methods before calling `super()` or `this()`.
- ✅ You can assign values to only uninitialized fields.
- ✅ You can perform parameter checks, or static method calls safely.

### 🧪 Example — Invalid Use
```java
class Book extends Page {
    String title;
    int pages = 100;

    Book() {
        System.out.println(this); // ❌ Not allowed (implicit this)
        title = "Java 25";        // ✅ Allowed (uninitialized field)
        pages = 200;              // ❌ Error (already initialized)
        super();                  // Constructor call
    }
}
```
### Example — Executing Statements Before `this()`
```java
class Course {
    String courseName;
    int duration;

    public Course(String name, int duration) {
        this.courseName = name;
        this.duration = duration;
    }

    public Course(String prefix, String name, int duration) {
        Objects.requireNonNull(prefix);
        Objects.requireNonNull(name);
        if (duration <= 0) throw new IllegalArgumentException("Invalid duration");
        this(prefix + name, duration); // ✅ Safe delegation
    }
}
```

## Scoped Values

The Scoped Values feature introduced in Java 25 provides a new, safer, and faster mechanism to share immutable data between 
methods and threads without explicit parameter passing.

It acts as a modern alternative to `ThreadLocal` — easier to reason about, more efficient, and less prone to memory leaks.

### 💡 Why Scoped Values?

In traditional Java applications, contextual data (like UserContext, Locale, or Transaction) is often passed as method parameters 
or stored in ThreadLocal variables.

Both approaches have limitations:

- **Parameter Passing Hell** — every method needs a context argument, even if unused.
- **ThreadLocal Problems** — mutable, requires manual cleanup, and may cause memory leaks.

### 💻 Example – Parameter Passing Hell
```java
public class OrderService {

    public void processOrder(Order order, UserContext context) {
        validateOrder(order, context);
        calculatePrice(order, context);
        saveOrder(order, context);
    }

    private void validateOrder(Order order, UserContext context) {
        checkUserPermissions(context);
    }

    private void calculatePrice(Order order, UserContext context) {
        applyUserDiscount(order, context);
    }
}
```
❌ Problem: The context parameter must be passed around everywhere, even to methods that don’t use it.

### 🧩 ThreadLocal Approach
```java
public class OrderService {
    private static final ThreadLocal<UserContext> CONTEXT = new ThreadLocal<>();

    public void processOrder(Order order, UserContext context) {
        CONTEXT.set(context); // Set context
        try {
            validateOrder(order);
            calculatePrice(order);
            saveOrder(order);
        } finally {
            CONTEXT.remove(); // ❗ Must be cleaned up manually
        }
    }

    private void validateOrder(Order order) {
        UserContext context = CONTEXT.get(); // Get context
        checkUserPermissions(context);
    }
}
```

### ⚠️ Problems with ThreadLocal

- **Unconstrained Mutability** — any code can modify the context.
- **Unbounded Lifetime** — forgetting `remove()` causes memory leaks.
- **Expensive Inheritance** — copying all `ThreadLocals` to child threads increases memory overhead.

### ✅ Scoped Values – The Modern Solution

`ScopedValue` addresses all these issues by:
- Storing immutable data that can’t be changed once set.
- Being automatically destroyed after its scope ends.
- Having no cleanup responsibilities for the developer.

### 💻 Example – Scoped Values in Action
```java
public class OrderService {

    private static final ScopedValue<UserContext> CONTEXT =
        ScopedValue.newInstance();

    public void processOrder(Order order, UserContext context) {
        ScopedValue.where(CONTEXT, context).run(() -> {
            validateOrder(order);
            calculatePrice(order);
            saveOrder(order);
        });
        // Context automatically destroyed here
    }

    private void validateOrder(Order order) {
        UserContext context = CONTEXT.get(); // Read context
        checkUserPermissions(context);
    }
}
```
### ✅ Key Points
- Scoped values are immutable — once set, they can’t be modified.
- They are automatically cleaned up after the run() block ends.
- They work seamlessly with virtual threads and concurrent code.

### 🧪 Simple Example
```java
public class SimpleExample {

    private static final ScopedValue<String> USERNAME =
        ScopedValue.newInstance();

    public void doWork() {
        ScopedValue.where(USERNAME, "Alice").run(() -> {
            processTask(); // Can access USERNAME
        });
        // USERNAME no longer accessible here
    }

    private void processTask() {
        String user = USERNAME.get(); // Returns "Alice"
        System.out.println("Processing for: " + user);
        callAnotherMethod();
    }

    private void callAnotherMethod() {
        String user = USERNAME.get(); // Still returns "Alice"
        System.out.println("Still processing for: " + user);
    }
}
```

### 🧠 Understanding Dynamic Scope

Scoped values follow dynamic scoping, not lexical scoping. That means they are accessible only during the lifetime of the run() block 
and within any method invoked from it.
```java

public void methodA() {
    ScopedValue.where(NAME, "John").run(() -> {
        methodB(); // NAME accessible here
    });
    // NAME not accessible here
}

public void methodB() {
    methodC(); // Still within dynamic scope
}

public void methodC() {
    String name = NAME.get(); // Returns "John"
}
```
🔄 Scoped values propagate dynamically across call chains during runtime

### 🔁 Rebinding (Nested Scopes)
Scoped values can be rebound — meaning you can assign a new value temporarily within a nested scope.
```java
private static final ScopedValue<String> LEVEL =
    ScopedValue.newInstance();

public void outerMethod() {
    ScopedValue.where(LEVEL, "Level 1").run(() -> {
        System.out.println(LEVEL.get()); // Level 1
        innerMethod();
        System.out.println(LEVEL.get()); // Level 1
    });
}

public void innerMethod() {
    System.out.println(LEVEL.get()); // Level 1
    ScopedValue.where(LEVEL, "Level 2").run(() -> {
        System.out.println(LEVEL.get()); // Level 2
        deepMethod();
    });
    System.out.println(LEVEL.get()); // Back to Level 1
}

public void deepMethod() {
    System.out.println(LEVEL.get()); // Level 2
}
```
Each `ScopedValue.where()` call defines a new nested scope. Once the inner scope ends, the value automatically reverts to its 
outer context.


### 🚀 Conclusion

Scoped Values modernize context sharing in Java by replacing ThreadLocal with a more efficient, safer, and immutable alternative.
They make context handling:
- Cleaner 🧼
- Faster ⚡
- Safer 🔒
- Perfectly aligned with virtual threads and structured concurrency.

Java 25’s Scoped Values = Simpler, safer, and smarter context propagation 🌐

## Ahead-of-Time (AOT) Class Loading and Linking

The Ahead-of-Time (AOT) feature in Java 25 significantly improves application startup time by allowing the JVM to preload, 
prelink, and cache classes before execution.

Instead of performing class loading, verification, and linking every time the JVM starts, AOT does this once during a training run 
and reuses the cached results for subsequent runs.

Think of AOT as meal prepping for your JVM — instead of cooking from scratch every day (JIT), you cook once and reheat quickly 
when needed 🍱⚡.

### 🚀 Traditional Java Startup (JIT Compilation)

When you run:
```
java MyApp
```
The JVM performs these steps:

**1**. Starts the JVM.
**2**. Reads JAR files from disk.
**3**. Parses all class files.
**4**. Loads classes into memory.
**5**. Links classes together (verifies and resolves references).
**6**. Runs static initializers.
**7**. Finally, starts your application.

➡️ This happens every single time the application starts.

### 💡 Ahead-of-Time (AOT) Approach

Instead of doing all this work during startup, AOT performs it once ahead-of-time and caches the results for reuse.

#### 🔁 Workflow Overview
Training Run (Recording Phase):
- Run your app once.
- The JVM records all classes loaded during execution.
- It saves that information in a configuration file.
Production Run (Execution Phase):
- JVM loads preprocessed classes directly from the cache.
- Application starts almost instantly.

**Result :** 🚀 Drastically faster startup times!

### 💻 Step-by-Step — AOT in Action
#### 🧾 Step 1: Training Run (Record)
Run your app once to record what classes it loads.
```
java -XX:AOTMode=record -XX:AOTConfiguration=app.aotconf \
     -cp app.jar com.example.App
```

- JVM monitors the application’s class loading.
- Saves all loaded classes to a config file app.aotconf.

#### 🧱 Step 2: Create AOT Cache

Generate the prelinked and preverified cache.
```
java -XX:AOTMode=create -XX:AOTConfiguration=app.aotconf \
     -XX:AOTCache=app.aot -cp app.jar
```

This processes and stores prelinked class metadata into the cache file app.aot.

#### ⚡ Step 3: Production Run (Use the Cache)

Finally, run your app using the cached data.
```
java -XX:AOTCache=app.aot -cp app.jar com.example.App
```
✅ Classes are instantly loaded from cache — no parsing, linking, or verification needed!

### 🧩 Simplified One-Step AOT Command (Java 25 Enhancement)

In JDK 25, the AOT process is streamlined into a single command, eliminating multiple steps.

✅ One-Step Cache Creation
```
java -XX:AOTCacheOutput=app.aot -cp app.jar com.example.App
```
Then, run the app normally using:
```
java -XX:AOTCache=app.aot -cp app.jar com.example.App
```

### 💪 Benefits of the New Command

- **Simpler :** Only one command (training + creation combined).
- **Cleaner :** No leftover configuration files.
- **Automation-Friendly :** Perfect for CI/CD and build pipelines.

### 🧠 What’s Inside an AOT Cache?
**✅ Classes That Can Be Cached**
- **JDK Classes :** String, ArrayList, HashMap, etc.
- **Application Classes :** Your app’s compiled classes and JARs.
- **Library Classes :** Frameworks like Spring, Hibernate, etc.
- Classes must be loaded by built-in classloaders.

❌ Classes That Cannot Be Cached
- Classes loaded via custom classloaders.
- Signed classes (restricted for security).
- Old bytecode requiring legacy verification.
- Dynamically generated classes at runtime.

### 🧮 What’s Precomputed and Cached?

AOT precomputes:

- ✅ Parsed bytecode → ready-to-use class structures.
- ✅ Resolved references → all links between classes established.
- ✅ Verified code → bytecode verified once.
- ✅ Loaded metadata → class, field, and method details stored.

This makes runtime startup instant — JVM just reads from the cache instead of redoing work.

### 💼 Using AOT in Spring Boot Applications

**Step 1: Build your Spring Boot JAR**
```
mvn clean package
```
**Step 2: Create an AOT cache (Java 25 style)**
```
java -XX:AOTCacheOutput=myapp.aot -jar target/myapp.jar
```
**Step 3: Deploy and run with AOT cache**
```
java -XX:AOTCache=myapp.aot -jar target/myapp.jar
```
✅ The Spring Boot app now starts up significantly faster — ideal for serverless deployments and microservices.