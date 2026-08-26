public class Todo extends Task{
    public Todo(String description){
        super(description);
    }

    @Override
    public String reformat(){
        return "T | " + super.reformat();
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
