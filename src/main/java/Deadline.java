public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String reformat(){
        return "D | " + super.reformat() + " | " + this.by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
