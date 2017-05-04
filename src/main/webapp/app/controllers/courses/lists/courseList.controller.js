angular
    .module('app')
    .controller('CourseListController', CourseListController);

function CourseListController(self, $state) {
    self.title = '';
    self.courses = [];
    self.pagination = { page: 1, limit: 7 };

    self.isAdminCourses = isAdminCourses;
    self.isAvailableStudentCourses = isAvailableStudentCourses;
    self.showLessons = showLessons;

    function isAdminCourses() {
        return $state.current.name === 'admin-courses';
    }

    function isAvailableStudentCourses() {
        return $state.current.name === 'student-available-courses';
    }

    function showLessons(course) {
        $state.go('course-lessons', { course: course.id });
    }
}