angular
    .module('app')
    .controller('TeacherFutureLessonListController', TeacherFutureLessonListController);

function TeacherFutureLessonListController($controller) {
    var self = this;
    self.filterLessons = filterLessons;
    $controller('TeacherLessonListController', { self: self });
    self.title = 'Future lessons';
    self.isUpdatingChosen = false;
    self.lessonsType = 'future';

    function filterLessons(lessons) {
        self.lessons = lessons.filter(function (lesson) {
            return lesson.startTime > (+new Date());
        });
    }
}


