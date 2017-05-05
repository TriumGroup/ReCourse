angular
    .module('app')
    .controller('StudentPastLessonListController', StudentPastLessonListController);

function StudentPastLessonListController($controller, $mdDialog, $state, LessonFactory, AuthService) {
    var self = this;
    $controller('StudentLessonListController', { self: self });

    self.title = 'Past lessons';
    self.isUpdatingChosen = false;
    self.lessonsType = 'past';
    self.lessons = [];

    self.refresh = refresh;

    AuthService.prepareAuthInfo().then(function() {
        self.studentId = AuthService.user.id;
        refresh();
    });

    function refresh() {
        LessonFactory.getPastForStudent({ id: self.studentId }).$promise.then(function (result) {
            self.lessons = result;
        })
    }
}


