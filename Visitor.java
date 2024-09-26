public class Visitor extends Person {
    
    public Visitor(){
        super();
    }

    public Visitor(String name, int age, int id){
        super (name, age, id);
    }

    //Most functionality of visitors will include stuff involving TICKETS and RIDES.

    @Override
    public void addToPark(Park park) {
        // Add visitor to the park's visitor set
        if (park.getVisitors().add(this)) {
            System.out.println("Visitor: " + getName() + " added to the park");
        } else {
            System.out.println("Visitor: " + getName() + " already in the park");
        }
    }

    @Override
    public void removeFromPark(Park park) {
        // Remove the visitor from the park's visitor set
        if (park.getVisitors().remove(this)) {
            System.out.println("Visitor: " + getName() + " removed from the park.");
        } else {
            System.out.println("Visitor: " + getName() + " not found in the park.");
        }
    }

    @Override
    public void personType(){
        System.out.println("Visitor");
    }
}
