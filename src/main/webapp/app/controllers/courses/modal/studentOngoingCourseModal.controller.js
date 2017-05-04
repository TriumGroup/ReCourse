angular
    .module('app')
    .controller('StudentOngoingCourseModalController', StudentOngoingCourseModalController);

function StudentOngoingCourseModalController($controller, $mdDialog, CourseFactory, course) {
    var self = this;
    $controller('CourseModalController', { self: self });

    self.title = 'Course';
    self.course = course;
    self.unregisterFromCourse = unregisterFromCourse;

    function unregisterFromCourse () {
        CourseFactory.unregisterSelf({ id: self.course.id }).$promise.then($mdDialog.hide);
    }
}