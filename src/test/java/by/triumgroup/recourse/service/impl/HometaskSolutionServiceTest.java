package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.HometaskSolutionRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSolutionSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import by.triumgroup.recourse.validation.validator.HometaskSolutionReferenceValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Pair;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class HometaskSolutionServiceTest extends CrudServiceTest<HometaskSolution, Integer> {
    private final LessonSupplier lessonSupplier;
    private CourseRepository courseRepository;
    private LessonRepository lessonRepository;
    private HometaskSolutionService hometaskSolutionService;
    private HometaskSolutionRepository hometaskSolutionRepository;
    private HometaskSolutionSupplier hometaskSolutionSupplier;
    private UserRepository userRepository;
    private UserSupplier userSupplier;
    private CourseSupplier courseSupplier;

    public HometaskSolutionServiceTest() {
        hometaskSolutionRepository = Mockito.mock(HometaskSolutionRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        lessonRepository = Mockito.mock(LessonRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        HometaskSolutionReferenceValidator hometaskSolutionReferenceValidator = Mockito.mock(HometaskSolutionReferenceValidator.class);
        when(hometaskSolutionReferenceValidator.supports(HometaskSolution.class)).thenReturn(true);
        hometaskSolutionService = new HometaskSolutionServiceImpl(
                hometaskSolutionRepository,
                userRepository,
                lessonRepository,
                courseRepository,
                hometaskSolutionReferenceValidator);
        hometaskSolutionSupplier = new HometaskSolutionSupplier();
        userSupplier = new UserSupplier();
        lessonSupplier = new LessonSupplier();
        courseSupplier = new CourseSupplier();
    }

    @Override
    protected CrudService<HometaskSolution, Integer> getCrudService() {
        return hometaskSolutionService;
    }

    @Override
    protected PagingAndSortingRepository<HometaskSolution, Integer> getCrudRepository() {
        return hometaskSolutionRepository;
    }

    @Override
    protected EntitySupplier<HometaskSolution, Integer> getEntitySupplier() {
        return hometaskSolutionSupplier;
    }

    @Override
    public void addValidEntityTest() throws Exception {
        HometaskSolution expectedEntity = getEntitySupplier().getValidEntityWithoutId();
        Lesson lesson = lessonSupplier.getValidEntityWithId();
        lesson.setStartTime(Timestamp.from(Instant.MIN));
        User user = userSupplier.getWithRole(User.Role.STUDENT);
        expectedEntity.setStudent(user);
        Course course = courseSupplier.getValidEntityWithId();
        Set<User> students = new HashSet<>();
        students.add(user);
        course.setStudents(students);
        when(getCrudRepository().save(expectedEntity)).thenReturn(expectedEntity);
        when(lessonRepository.findOne(expectedEntity.getLessonId())).thenReturn(lesson);
        when(userRepository.findOne(expectedEntity.getStudent().getId())).thenReturn(user);
        when(courseRepository.findOne(lesson.getCourseId())).thenReturn(course);

        Optional<HometaskSolution> actualResult = getCrudService().add(expectedEntity);

        verify(getCrudRepository(), times(1)).save(expectedEntity);
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
    }

    @Test
    @Override
    public void addEntityWithExistingIdTest() throws Exception {
        HometaskSolution entity = getEntitySupplier().getValidEntityWithId();
        Lesson lesson = lessonSupplier.getValidEntityWithId();
        lesson.setStartTime(Timestamp.from(Instant.MIN));
        User user = userSupplier.getWithRole(User.Role.STUDENT);
        entity.setStudent(user);
        Course course = courseSupplier.getValidEntityWithId();
        Set<User> students = new HashSet<>();
        students.add(user);
        course.setStudents(students);
        when(getCrudRepository().save(entity)).thenReturn(entity);
        when(lessonRepository.findOne(entity.getLessonId())).thenReturn(lesson);
        when(userRepository.findOne(entity.getStudent().getId())).thenReturn(user);
        when(courseRepository.findOne(lesson.getCourseId())).thenReturn(course);
//        setupAllowedRoles(entity);

        getCrudService().add(entity);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(entity);
        Assert.assertNull(captor.getValue().getId());
    }

    @Test
    @Override
    public void updateEntityWithDifferentParameterIdTest() throws Exception {
        Pair<Integer, Integer> ids = getEntitySupplier().getDifferentIds();
        Integer entityId = ids.getFirst();
        Integer parameterId = ids.getSecond();
        HometaskSolution newEntity = getEntitySupplier().getValidEntityWithoutId();
        HometaskSolution databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        databaseEntity.setId(parameterId);
        newEntity.setId(entityId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<HometaskSolution> actualResult = hometaskSolutionService.update(newEntity, parameterId, true);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<HometaskSolution>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Override
    public void updateEntityExceptionTest() throws Exception {
        HometaskSolution entity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().save(Matchers.<HometaskSolution>any())).thenThrow(new DataIntegrityViolationException(""));
        when(getCrudRepository().exists(any())).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(entity);
        setupAllowedRoles(entity);

        thrown.expect(ServiceException.class);

        hometaskSolutionService.update(entity, parameterId, true);

        verify(getCrudRepository(), times(1)).save(Matchers.<HometaskSolution>any());
    }

    @Override
    public void updateEntityWithoutIdTest() throws Exception {
        HometaskSolution newEntity = getEntitySupplier().getValidEntityWithoutId();
        HometaskSolution databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        databaseEntity.setId(parameterId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<HometaskSolution> actualResult = hometaskSolutionService.update(newEntity, parameterId, true);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<HometaskSolution>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Override
    public void updateNotExistingEntityTest() throws Exception {
        HometaskSolution entity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().exists(parameterId)).thenReturn(false);
        when(getCrudRepository().findOne(parameterId)).thenReturn(null);

        Optional<HometaskSolution> actualResult = hometaskSolutionService.update(entity, parameterId, true);

        verify(getCrudRepository(), times(0)).save(entity);
        Assert.assertFalse(actualResult.isPresent());
    }

    @Test
    public void findByExistingStudentIdAndHometaskIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.STUDENT);
        HometaskSolution solution = hometaskSolutionSupplier.getValidEntityWithId();
        when(userRepository.findOne(anyInt())).thenReturn(user);
        when(lessonRepository.exists(anyInt())).thenReturn(true);
        when(hometaskSolutionRepository.findByStudentIdAndLessonId(anyInt(), any()))
                .thenReturn(solution);

        Optional<HometaskSolution> courses = hometaskSolutionService.findByStudentIdAndLessonId(user.getId(), solution.getId());

        verify(userRepository, times(1)).findOne(anyInt());
        verify(lessonRepository, times(1)).exists(anyInt());
        verify(hometaskSolutionRepository, times(1)).findByStudentIdAndLessonId(anyInt(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByNotStudentIdAndHometaskIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.TEACHER);
        when(userRepository.findOne(anyInt())).thenReturn(user);

        Optional<HometaskSolution> courses = hometaskSolutionService.findByStudentIdAndLessonId(user.getId(), 1);

        verify(userRepository, times(1)).findOne(anyInt());
        assertFalse(courses.isPresent());
    }

    @Test
    public void findByExistingStudentIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.STUDENT);
        when(userRepository.findOne(anyInt())).thenReturn(user);
        when(hometaskSolutionRepository.findByStudentId(anyInt(), any()))
                .thenReturn(Collections.singletonList(hometaskSolutionSupplier.getValidEntityWithId()));

        Optional<List<HometaskSolution>> courses = hometaskSolutionService.findByStudentId(user.getId(), null);

        verify(userRepository, times(1)).findOne(anyInt());
        verify(hometaskSolutionRepository, times(1)).findByStudentId(anyInt(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByNotExistingStudentIdTest() throws Exception {
        when(userRepository.findOne(anyInt())).thenReturn(null);

        Optional<List<HometaskSolution>> courses = hometaskSolutionService.findByStudentId(1, null);

        verify(userRepository, times(1)).findOne(anyInt());
        assertFalse(courses.isPresent());
    }

    @Test
    public void findByExistingHometaskIdTest() throws Exception {
        when(lessonRepository.exists(anyInt())).thenReturn(true);
        when(hometaskSolutionRepository.findByLessonId(anyInt(), any()))
                .thenReturn(Collections.singletonList(hometaskSolutionSupplier.getValidEntityWithId()));

        Optional<List<HometaskSolution>> courses = hometaskSolutionService.findByLessonId(1, null);

        verify(lessonRepository, times(1)).exists(anyInt());
        verify(hometaskSolutionRepository, times(1)).findByLessonId(anyInt(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByNotExistingHometaskIdTest() throws Exception {
        when(lessonRepository.exists(anyInt())).thenReturn(false);

        Optional<List<HometaskSolution>> courses = hometaskSolutionService.findByLessonId(1, null);

        verify(lessonRepository, times(1)).exists(anyInt());
        assertFalse(courses.isPresent());
    }

    @Override
    protected void setupAllowedRoles(HometaskSolution entity) {
        Integer studentId = entity.getStudent().getId();
        when(userRepository.findOne(studentId)).thenReturn(userSupplier.getWithRole(User.Role.STUDENT));
    }

}
