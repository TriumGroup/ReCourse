angular
    .module('app')
    .controller('CourseModalController', CourseModalController);

function CourseModalController(self, $state, $mdDialog) {
    self.title = '';
    self.statuses = ['DRAFT', 'PUBLISHED', 'FINISHED'];

    self.cancel = cancel;
    self.isAdminCourses = isAdminCourses;
    self.isAvailableStudentCourses = isAvailableStudentCourses;

    function isAdminCourses() {
        return $state.current.name === 'admin-courses';
    }

    function isAvailableStudentCourses() {
        return $state.current.name === 'student-available-courses';
    }

    function cancel() {
        $mdDialog.cancel();
    }
}