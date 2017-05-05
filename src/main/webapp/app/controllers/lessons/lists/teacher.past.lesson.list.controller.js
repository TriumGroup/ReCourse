angular
    .module('app')
    .controller('TeacherPastLessonListController', TeacherPastLessonListController);

function TeacherPastLessonListController($controller, $state) {
    var self = this;
    self.filterLessons = filterLessons;
    $controller('TeacherLessonListController', { self: self });
    self.title = 'Past lessons';
    self.isUpdatingChosen = false;
    self.lessonsType = 'past';
    self.showSolutions = showSolutions;

    function filterLessons(lessons) {
        self.lessons = lessons.filter(function (lesson) {
            return lesson.startTime <= (+new Date());
        });
    }

    function showSolutions(lesson) {
        $state.go('teacher-solutions', { id: lesson.id });
    }
}


