import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TasksTest {

    private SimpleTask simpleTask;
    private Epic epic;
    private Meeting meeting;

    @BeforeEach
    public void setUp() {
        simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        epic = new Epic(55, subtasks);
        meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
    }

    @Test
    public void testSimpleTaskMatches() {
        Assertions.assertTrue(simpleTask.matches("родителям"));
        Assertions.assertFalse(simpleTask.matches("покупки"));
    }

    @Test
    public void testEpicMatches() {
        Assertions.assertTrue(epic.matches("Молоко"));
        Assertions.assertFalse(epic.matches("Сыр"));
    }

    @Test
    public void testMeetingMatches() {
        Assertions.assertTrue(meeting.matches("приложения"));
        Assertions.assertFalse(meeting.matches("отчёта"));
    }

    @Test
    public void testMeetingMatchesWithQueryInProject() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Assertions.assertTrue(meeting.matches("НетоБанка"));
    }

    @Test
    public void testDefaultMatchesBehavior() {
        Task task = new Task(1);
        Assertions.assertFalse(task.matches("что-то"));
    }

    @Test
    public void testHashCodeBehavior() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Assertions.assertEquals(task1.hashCode(), task2.hashCode());
        Task task3 = new Task(2);
        Assertions.assertNotEquals(task1.hashCode(), task3.hashCode());
    }

    @Test
    public void testEqualsSameObject() {
        Task task = new Task(1);
        Assertions.assertTrue(task.equals(task));
    }

    @Test
    public void testEqualsNull() {
        Task task = new Task(1);
        Assertions.assertFalse(task.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        Task task = new Task(1);
        Assertions.assertFalse(task.equals("некоторая строка"));
    }

    @Test
    public void testEqualsIdenticalInstance() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Assertions.assertTrue(task1.equals(task2));
    }

    @Test
    public void testEqualsDifferentID() {
        Task task1 = new Task(1);
        Task task2 = new Task(2);
        Assertions.assertFalse(task1.equals(task2));
    }
}
