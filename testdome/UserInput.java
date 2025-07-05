public public class UserInput {
    
    public static class TextInput {
        private StringBuilder value;
        
        public TextInput() {
            this.value = new StringBuilder();
        }
        
        // Public method to add any character
        public void add(char c) {
            value.append(c);
        }
        
        // Public method to get current value
        public String getValue() {
            return value.toString();
        }
    }
    
    public static class NumericInput extends TextInput {
        
        // Override add method to only accept numeric characters
        @Override
        public void add(char c) {
            // Only add if character is a digit
            if (Character.isDigit(c)) {
                super.add(c); // Call parent's add method
            }
            // Ignore non-numeric characters silently
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== TextInput Demo ===");
        TextInput textInput = new TextInput();
        textInput.add('H');
        textInput.add('e');
        textInput.add('l');
        textInput.add('l');
        textInput.add('o');
        textInput.add('1');
        textInput.add('2');
        textInput.add('3');
        System.out.println("TextInput value: '" + textInput.getValue() + "'");
        
        System.out.println("\n=== NumericInput Demo ===");
        TextInput input = new NumericInput(); // Polymorphism
        input.add('1');
        input.add('a'); // This will be ignored
        input.add('0');
        System.out.println("NumericInput value: '" + input.getValue() + "'");
        
        System.out.println("\n=== Additional Test Cases ===");
        
        // Test case 1: Mixed characters
        NumericInput numInput1 = new NumericInput();
        numInput1.add('a');
        numInput1.add('1');
        numInput1.add('b');
        numInput1.add('2');
        numInput1.add('c');
        numInput1.add('3');
        System.out.println("Mixed characters (abc123): '" + numInput1.getValue() + "'");
        
        // Test case 2: No digits
        NumericInput numInput2 = new NumericInput();
        numInput2.add('x');
        numInput2.add('y');
        numInput2.add('z');
        System.out.println("No digits (xyz): '" + numInput2.getValue() + "'");
        
        // Test case 3: Only digits
        NumericInput numInput3 = new NumericInput();
        numInput3.add('4');
        numInput3.add('5');
        numInput3.add('6');
        System.out.println("Only digits (456): '" + numInput3.getValue() + "'");
        
        // Test case 4: Special characters and digits
        NumericInput numInput4 = new NumericInput();
        numInput4.add('7');
        numInput4.add('!');
        numInput4.add('8');
        numInput4.add('@');
        numInput4.add('9');
        numInput4.add('#');
        System.out.println("Special chars + digits (7!8@9#): '" + numInput4.getValue() + "'");
        
        // Test case 5: Empty input
        NumericInput numInput5 = new NumericInput();
        System.out.println("Empty input: '" + numInput5.getValue() + "'");
        
        // Demonstrate inheritance
        System.out.println("\n=== Inheritance Demonstration ===");
        TextInput[] inputs = {
            new TextInput(),
            new NumericInput()
        };
        
        for (int i = 0; i < inputs.length; i++) {
            inputs[i].add('A');
            inputs[i].add('1');
            inputs[i].add('B');
            inputs[i].add('2');
            
            String type = inputs[i].getClass().getSimpleName();
            System.out.println(type + " result: '" + inputs[i].getValue() + "'");
        }
    }
} {
    
}
