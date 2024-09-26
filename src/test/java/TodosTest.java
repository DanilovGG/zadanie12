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
        todos.add(new SimpleTask(5, "Позвонить родителям после обеда"));
        todos.add(new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"}));
        todos.add(new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        ));
    }

    @Test
    void testSearch_FindMultipleTasks() {
        String query = "после обеда"; // Поиск по подстроке в названии задачи
        Task[] expectedResult = new Task[]{todos.findById(5), todos.findById(555)};

        Task[] actualResult = todos.search(query);

        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void testSearch_FindOneTask() {
        String query = "Молоко";
        Task[] expectedResult = new Task[]{todos.findById(55)};

        Task[] actualResult = todos.search(query);

        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void testSearch_NoMatchFound() {
        String query = "Неправильное слово";
        Task[] expectedResult = new Task[0];

        Task[] actualResult = todos.search(query);

        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void testFindById_ReturnNullWhenNotFound() {
        int invalidId = 999;
        Task expectedResult = null;

        Task actualResult = todos.findById(invalidId);

        Assertions. assertEquals(expectedResult, actualResult);
    }
}