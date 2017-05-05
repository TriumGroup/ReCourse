angular
    .module('app')
    .controller('LessonListController', LessonListController);

function LessonListController(AuthService, self) {
    self.title = '';
    self.lessons = [];
    self.pagination = {page: 1, limit: 7};

    self.isTeacherLessons = isTeacherLessons;
    self.isStudentLessons = isStudentLessons;
    self.isAdminLessons = isAdminLessons;

    function isTeacherLessons() {
        return AuthService.role === 'TEACHER';
    }

    function isAdminLessons() {
        return AuthService.role === 'ADMIN';
    }

    function isStudentLessons() {
        return AuthService.role === 'STUDENT';
    }
}


