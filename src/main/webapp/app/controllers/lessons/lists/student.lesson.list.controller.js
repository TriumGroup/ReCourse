angular
    .module('app')
    .controller('StudentLessonListController', StudentLessonListController);

function StudentLessonListController($controller, self, $mdDialog) {
    $controller('LessonListController', { self: self });

    self.isFutureLessons = isFutureLessons;
    self.isPastLessons = isPastLessons;
    self.openShowModal = openShowModal;
    self.showLesson = showLesson;

    function isFutureLessons() {
        return self.lessonsType === 'future';
    }

    function isPastLessons() {
        return !isFutureLessons();
    }

    function showLesson(lesson) {
        self.openShowModal(lesson);
    }

    function openShowModal(lesson) {
        $mdDialog.show({
            controller: 'StudentLessonModalController as self',
            templateUrl: 'templates/lessons/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                lesson: angular.copy(lesson)
            }
        }).then(self.refresh, self.refresh);
    }
}


