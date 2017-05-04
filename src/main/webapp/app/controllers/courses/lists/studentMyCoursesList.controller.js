angular
    .module('app')
    .controller('StudentMyCoursesListController', StudentMyCoursesListController);

function StudentMyCoursesListController($mdDialog, $controller, CourseFactory, AuthService, FeedbackFactory) {
    var self = this;
    $controller('CourseListController', { self: self });

    self.title = 'My Courses';

    self.unregisterStudent = unregisterStudent;
    self.addFeedback = addFeedback;

    AuthService.prepareAuthInfo().then(function () {
        self.studentId = AuthService.user.id;
        refresh();
    });

    function refresh() {
        CourseFactory.getStudentCourses({id : self.studentId}).$promise.then(function (result) {
            self.courses = result;
            for (var i = 0; i < self.courses.length; i++) {
                const index = i;
                FeedbackFactory.getForStudentAndCourse({studentId: self.studentId, courseId: self.courses[index].id}).$promise.then(function (result) {
                    self.courses[index].feedback = result;
                }, function(result) {
                    console.log(result); //TODO: suppress error somehow
                })
            }
        });
    }

    function unregisterStudent(course) {
        openUnregisterModal(course);
    }

    function openUnregisterModal(course) {
        $mdDialog.show({
            controller: 'StudentOngoingCourseModalController as self',
            templateUrl: 'templates/courses/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                course: angular.copy(course)
            }
        }).then(refresh, refresh);
    }

    function addFeedback(course) {
        $mdDialog.show({
            controller: 'StudentCourseFeedbackModalController as self',
            templateUrl: 'templates/feedbacks/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                courseId: course.id
            }
        }).then(refresh, refresh);
    }
}

