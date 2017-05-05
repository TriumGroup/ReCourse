angular
    .module('app')
    .controller('LessonModalController', LessonModalController);

function LessonModalController(self, $mdDialog, lesson, AuthService) {
    if (lesson && lesson.startTime) {
        lesson.startTime = new Date(lesson.startTime);
    }

    self.lesson = lesson;
    self.cancel = cancel;
    self.now = now;
    self.isAdminLesson  = isAdminLesson;
    self.isTeacherLesson  = isTeacherLesson;
    self.isStudentLesson  = isStudentLesson;
    self.isPastLesson = isPastLesson;
    self.canSave = canSave;
    self.canEditHometask = canEditHometask;

    AuthService.prepareAuthInfo().then(function () {
       self.userRole =  AuthService.role;
    });

    function isAdminLesson() {
        return self.userRole === 'ADMIN';
    }

    function isTeacherLesson() {
        return self.userRole === 'TEACHER';
    }

    function isStudentLesson() {
        return self.userRole === 'STUDENT';
    }

    function isPastLesson() {
        return self.lesson.startTime < now();
    }

    function canSave() {
        return self.isAdminLesson() || (self.isTeacherLesson() && !self.isPastLesson());
    }

    function canEditHometask() {
        return (!self.isPastLesson() && self.isTeacherLesson()) || self.isAdminLesson();
    }

    function now() {
        return +new Date();
    }

    function cancel() {
        $mdDialog.cancel();
    }
}