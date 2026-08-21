public class Task {
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void setStatus(boolean status){
        this.isDone = status;
    }

    @Override
    public String toString(){
        return this.isDone? "[X] " + this.description: "[ ] " + this.description;
    }
}
