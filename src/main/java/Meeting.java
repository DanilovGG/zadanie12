public class Meeting extends Task {
    private final String topic;
    private final String project;
    private final String startTime;

    public Meeting(int id, String topic, String project, String startTime) {
        super(id);
        this.topic = topic;
        this.project = project;
        this.startTime = startTime;
    }

    @Override
    public boolean matches(String query) {
        if (topic.contains(query)) {
            return true;
        }
        if (project.contains(query)) {
            return true;
        }
        return false;
    }
}