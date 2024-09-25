public class Visitor extends Person {
    
    public Visitor(){
        super();
    }

    public Visitor(String name, int age, int id){
        super (name, age, id);
    }

    //Most functionality of visitors will include stuff involving TICKETS and RIDES.


    @Override
    public void personType(){
        System.out.println("Visitor");
    }
}
