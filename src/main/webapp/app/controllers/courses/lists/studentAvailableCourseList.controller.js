angular
    .module('app')
    .controller('StudentAvailableCourseListController', StudentAvailableCourseListController);

function StudentAvailableCourseListController($mdDialog, $controller, CourseFactory, AuthService) {
    var self = this;
    $controller('CourseListController', { self: self });

    self.title = 'Available Courses';

    self.registerStudent = registerStudent;

    AuthService.prepareAuthInfo().then(function () {
        self.studentId = AuthService.user.id;
        refresh();
    });

    function refresh() {
        CourseFactory.availableForStudent({studentId : self.studentId}).$promise.then(function (result) {
            self.courses = result;
        });
    }

    function registerStudent(course) {
        openModal(course);
    }

    function openModal(course) {
        $mdDialog.show({
            controller: 'StudentCourseRegistrationModalController as self',
            templateUrl: 'templates/courses/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                course: angular.copy(course)
            }
        }).then(refresh, refresh);
    }
}

