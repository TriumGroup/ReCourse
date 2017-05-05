angular
    .module('app')
    .controller('CourseUserListController', CourseUserListController);

function CourseUserListController($mdDialog, UserFactory, $stateParams, $controller, CourseFactory) {
    var self = this;
    $controller('UserListController', {self: self});

    self.title = 'Course';
    self.subtitle = 'Students';
    self.course = $stateParams.id;
    self.users = [];
    self.registerStudent = registerStudent;
    self.unregisterStudent = unregisterStudent;

    refresh();

    function refresh() {
        UserFactory.getForCourse({ id: self.course }).$promise.then(function (result) {
            self.users = result;
        });
    }

    function registerStudent() {
        openRegisterModal();
    }

    function unregisterStudent(user) {
        var confirm = $mdDialog.confirm()
                        .title('Would you like to unregister student from this course?')
                        .ok('Yes!')
                        .cancel('No');

        $mdDialog.show(confirm).then(function () {
            CourseFactory.unregisterStudent({ id: self.course, studentId: user.id }, refresh);
        }, function() {});
    }

    function openRegisterModal() {
        $mdDialog.show({
            controller: 'CourseRegistrationModalController as self',
            templateUrl: 'templates/users/registrationModal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                courseId: self.course
            }
        }).then(refresh, refresh);
    }
}


