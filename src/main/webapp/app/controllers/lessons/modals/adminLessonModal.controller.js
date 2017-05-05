angular
    .module('app')
    .controller('AdminLessonModalController', AdminLessonModalController);

function AdminLessonModalController($mdDialog, $controller, UserFactory, LessonFactory, courseId, lesson) {
    var self = this;
    $controller('LessonModalController', { self: self, lesson: lesson });

    self.saveLesson = saveLesson;
    self.updateMode = !!self.lesson;
    self.courseId = courseId;
    self.teachers = [];

    if (self.updateMode) {
        self.title = 'Update Lesson';
    } else {
        self.title = 'Create Lesson'
    }

    UserFactory.query().$promise.then(function (result) {
        self.teachers = result.filter(function (user) { return user.role === 'TEACHER' });
    });

    function saveLesson() {
        self.lesson.courseId = courseId;
        if (self.updateMode){
            LessonFactory.update(self.lesson, $mdDialog.hide);
        } else {
            LessonFactory.save(self.lesson, $mdDialog.hide);
        }
    }
}