import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TasksTest {

    private Meeting meeting;
    private SimpleTask simpleTask;
    private Epic epic;

    @BeforeEach
    public void setUp() {
        meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "12-01-2023 18:00");
        simpleTask = new SimpleTask(5, "Позвонить родителям");
        epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
    }

    @Test
    public void testSimpleTaskMatches() {
        Assertions.assertTrue(simpleTask.matches("родителям"));
        Assertions.assertFalse(simpleTask.matches("Молоко"));
    }

    @Test
    public void testEpicMatches() {
        Assertions.assertTrue(epic.matches("Молоко"));
        Assertions.assertTrue(epic.matches("Яйца"));
        Assertions.assertTrue(epic.matches("Хлеб"));
        Assertions.assertFalse(epic.matches("Приложение"));
    }

    @Test
    public void testMeetingMatches() {
        Assertions.assertTrue(meeting.matches("приложения"));
        Assertions.assertTrue(meeting.matches("НетоБанка"));
        Assertions.assertFalse(meeting.matches("Молоко"));
    }

    @Test
    public void testDefaultMatchesBehavior() {
        Task task = new Task(1);
        Assertions.assertFalse(task.matches("что угодно"));
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
        Assertions.assertFalse(task.equals("some string"));
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
