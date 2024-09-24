import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodosTest {

    private Todos todos;

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @BeforeEach
    public void setUp() {
        todos = new Todos();
        todos.add(new SimpleTask(5, "Позвонить родителям"));
        todos.add(new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"}));
        todos.add(new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        ));
    }

    @Test
    public void testSearchByQueryInTitle() {
        Task[] expected = {todos.findById(5)};
        Task[] actual = todos.search("родителям");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchByQueryInSubtasks() {
        Task[] expected = {todos.findById(55)};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchByQueryInTopicOrProject() {
        Task[] expected = {todos.findById(555)};
        Task[] actual = todos.search("приложения");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testFindByIdNotFound() {
        Task task = todos.findById(100);
        Assertions.assertNull(task);
    }

    @Test
    public void testFindByIdMultiple() {
        Task[] expected = {todos.findById(5), todos.findById(55), todos.findById(555)};
        Task[] actual = new Task[]{todos.findById(5), todos.findById(55), todos.findById(555)};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testFindByIdSingle() {
        Task task = todos.findById(5);
        Assertions.assertNotNull(task);
        Assertions.assertEquals(5, task.getId());
    }

    @Test
    public void testFindByIdNoResults() {
        Task[] expected = {};
        Task[] actual = new Task[0];
        Assertions.assertArrayEquals(expected, actual);
    }
}
