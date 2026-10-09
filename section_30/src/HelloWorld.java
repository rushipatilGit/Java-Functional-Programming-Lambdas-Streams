String greeting = "Hello, World!";

String greeting() {
    return "Hello, World!";
}

void main() {
    var name = IO.readln("Enter your name:");
    IO.println("Hello :" +name);
    IO.println(greeting());

    var authors = List.of("James", "Bill", "Guy", "Alex", "Dan", "Gavin");
    for (var author : authors) {
        IO.println(author + ": " + author.length());
    }

}