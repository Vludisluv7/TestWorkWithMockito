package ru.gb_spring.homeworkspring_05;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gb_spring.homeworkspring_05.model.Task;
import ru.gb_spring.homeworkspring_05.model.TaskStatus;
import ru.gb_spring.homeworkspring_05.repository.TaskRepository;
import ru.gb_spring.homeworkspring_05.services.TaskServices;

import static org.mockito.Mockito.verify;

// класс для тестов
@ExtendWith(MockitoExtension.class)
class TaskServicesTest {

    @InjectMocks
    private TaskServices realService;

    @Mock
    private TaskRepository virtualRep;

    private static Task testTask;
    // задаю параметры тестовому обьекту
    @BeforeEach
    public void setTestParameters(){
        testTask = new Task();
        testTask.setDescription("Сходить в универ");
        testTask.setId(1L);
        testTask.setStatus(TaskStatus.IN_PROGRESS);
    }
    //тестирую сохранение Task
    @Test
    public void savingTest(){
        realService.saveTask(testTask);

        verify(virtualRep).save(testTask);
    }
    // тестирую поиск по статусу
    @Test
    public void findByStatusTest(){
        realService.getTaskByStatus(TaskStatus.IN_PROGRESS);

        verify(virtualRep).findByStatus(testTask.getStatus());
    }
}
