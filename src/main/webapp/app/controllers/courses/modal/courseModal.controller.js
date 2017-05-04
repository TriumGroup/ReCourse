angular
    .module('app')
    .controller('CourseModalController', CourseModalController);

function CourseModalController($mdDialog, CourseFactory, course) {
    var self = this;

    self.course = course;
    self.saveCourse = saveCourse;
    self.cancel = cancel;
    self.updateMode = !!self.course;

    self.isAdminCourses = isAdminCourses;
    self.isAvailableStudentCourses = isAvailableStudentCourses;

    self.statuses = ['DRAFT', 'PUBLISHED', 'FINISHED'];

    function saveCourse() {
        if (self.updateMode){
            CourseFactory.update(self.course, $mdDialog.hide);
        } else {
            CourseFactory.save(self.course, $mdDialog.hide);
        }
    }

    function isAdminCourses() {
        return $state.current.name === 'admin-courses';
    }

    function isAvailableStudentCourses() {
        return $state.current.name === 'student-available-courses';
    }

    function showLessons(course) {
        $state.go('course-lessons', { course: course.id });
    }

    function cancel() {
        $mdDialog.cancel();
    }
}