public class Epic extends Task {
    private String[] subTasks;

    public Epic(int id, String[] subTasks) {
        super(id);
        this.subTasks = subTasks;
    }

    @Override
    public boolean matches(String query) {
        for (String subtask : subTasks) {
            if (subtask.toLowerCase().contains(query.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}