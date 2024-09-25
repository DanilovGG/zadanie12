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
    public void testSearchWithQueryParents() {
        Task[] results = todos.search("родителям");
        Assertions.assertEquals(1, results.length);
        Assertions.assertEquals(5, results[0].getId());
    }

    @Test
    public void testSearchWithQueryMilk() {
        Task[] results = todos.search("Молоко");
        Assertions.assertEquals(1, results.length);
        Assertions.assertEquals(55, results[0].getId());
    }

    @Test
    public void testSearchWithQueryApplication() {
        Task[] results = todos.search("приложения");
        Assertions.assertEquals(1, results.length);
        Assertions.assertEquals(555, results[0].getId());
    }
}