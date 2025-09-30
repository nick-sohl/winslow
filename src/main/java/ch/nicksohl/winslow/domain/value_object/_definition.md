### Definition
- A value object is immutable
- Only accepts valid values (as arguments in its constructor)
- If one of the values is invalid, a meaningful exception must be thrown.

### Checklist
- it is immutable and no setters defined;
- it reflects the semantics of the domain;
- it shows how information flows and is transformed during runtime;
- it hasn’t default or useless getter methods;
- it can be compared to other Value Objects of the same class by reading private properties directly.

### Example
``` java
// Value Object with record class
public record Email(String value) {
    public Email {
        if (!value.matches("^[^@]+@[^@]+\\.[^@]+$")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}
```