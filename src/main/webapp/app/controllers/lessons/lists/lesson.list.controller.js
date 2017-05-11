angular
    .module('app')
    .controller('LessonListController', LessonListController);

function LessonListController(AuthService, self, $state,DocumentDownloaderService) {
    self.title = '';
    self.lessons = [];
    self.pagination = {page: 1, limit: 7};

    self.isTeacherLessons = isTeacherLessons;
    self.isStudentLessons = isStudentLessons;
    self.isAdminLessons = isAdminLessons;
    self.isAllLessons = isAllLessons;
    self.downloadLessons = downloadLessons;

    function isAllLessons() {
        return $state.current.name === 'course-lessons' || $state.current.name === 'student-available-lessons';
    }

    function isTeacherLessons() {
        return AuthService.role === 'TEACHER';
    }

    function isAdminLessons() {
        return AuthService.role === 'ADMIN';
    }

    function isStudentLessons() {
        return AuthService.role === 'STUDENT';
    }

    function downloadLessons(type) {
        DocumentDownloaderService.downloadDocument('api/courses/' + self.courseId + '/lessons/export', type);
    }
}


