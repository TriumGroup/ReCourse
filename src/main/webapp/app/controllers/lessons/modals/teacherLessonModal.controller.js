angular
    .module('app')
    .controller('TeacherLessonModalController', TeacherLessonModalController);

function TeacherLessonModalController($controller, $mdDialog, LessonFactory, CourseFactory, lesson) {
    var self = this;
    $controller('LessonModalController', { self: self, lesson: lesson });

    self.title = 'My Lesson';
    self.course = {};

    self.saveLesson = saveLesson;

    function saveLesson() {
        LessonFactory.update(self.lesson, $mdDialog.hide);
    }

    CourseFactory.query().$promise.then(function (result) {
        self.course = result.find(function (course) {
            return course.id === self.lesson.courseId;
        });
    });
}