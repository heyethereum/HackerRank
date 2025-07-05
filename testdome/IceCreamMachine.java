import java.util.*;

public class IceCreamMachine {
    public String[] ingredients;
    public String[] toppings;
    
    public static class IceCream {
        public String ingredient;
        public String topping;

        public IceCream(String ingredient, String topping) {
            this.ingredient = ingredient;
            this.topping = topping;
        }
    }

    public IceCreamMachine(String[] ingredeints, String[] toppings) {
        this.ingredients = ingredeints;
        this.toppings = toppings;
    }

    public List<IceCream> scoops() {
        if(ingredients.length == 0 || toppings.length == 0) {
            return new ArrayList<>();    
        }
        List<IceCream> result = new ArrayList<>();
        
        for(String ingredeint: ingredients) {
            for (String topping: toppings){
                result.add(new IceCream(ingredeint, topping));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        IceCreamMachine machine = new IceCreamMachine(new String[]{
                "vanilla", "chocolate"
        }, new String[]{
                "chocolate sauce"
        });
        List<IceCream> scoops = machine.scoops();

        /*
         * Should print:
         * vanilla, chocolate sauce
         * chocolate, chocolate sauce
         */
        for (IceCream iceCream : scoops) {
            System.out.println(iceCream.ingredient + ", " + iceCream.topping);
        }
    }
}