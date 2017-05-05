angular
    .module('app')
    .controller('TeacherLessonListController', TeacherLessonListController);

function TeacherLessonListController($controller, $mdDialog, AuthService, LessonFactory, self) {
    $controller('LessonListController', { self: self });

    self.isFutureLessons = isFutureLessons;
    self.isPastLessons = isPastLessons;
    self.refresh = refresh;
    self.openShowModal = openShowModal;
    self.showLesson = showLesson;

    AuthService.prepareAuthInfo().then(function() {
        self.teacherId = AuthService.user.id;
        refresh();
    });

    function refresh() {
        LessonFactory.getForTeacher({ id: self.teacherId }).$promise.then(self.filterLessons)
    }

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
            controller: 'TeacherLessonModalController as self',
            templateUrl: 'templates/lessons/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                lesson: angular.copy(lesson)
            }
        }).then(self.refresh, self.refresh);
    }
}


