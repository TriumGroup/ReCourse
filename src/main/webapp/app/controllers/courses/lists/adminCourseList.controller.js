angular
    .module('app')
    .controller('AdminCourseListController', AdminCourseListController);

function AdminCourseListController($controller, $mdDialog, CourseFactory, $state) {
    var self = this;
    $controller('CourseListController', { self: self });

    self.title = 'Courses';
    self.addCourse = addCourse;
    self.deleteCourse = deleteCourse;
    self.editCourse = editCourse;
    self.showStudents = showStudents;
    self.showFeedbacks = showFeedbacks;

    refresh();

    function refresh() {
        CourseFactory.query().$promise.then(function (result) {
            self.courses = result;
        });
    }

    function addCourse() {
        openModal();
    }

    function deleteCourse(course) {
        var confirm = $mdDialog.confirm()
            .title('Would you like to delete this course?')
            .ok('Yes!')
            .cancel('No');

        $mdDialog.show(confirm).then(function () {
            CourseFactory.delete(course, refresh);
        }, function() {});
    }

    function editCourse(course) {
        openModal(course);
    }

    function showStudents(course) {
        $state.go('course-users', { id: course.id });
    }

    function showFeedbacks(course) {
        $state.go('feedbacks', { course: course.id });
    }

    function openModal(course) {
        $mdDialog.show({
            controller: 'AdminCourseModalController as self',
            templateUrl: 'templates/courses/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                course: angular.copy(course)
            }
        }).then(refresh, refresh);
    }
}