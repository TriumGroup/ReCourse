angular
    .module('app')
    .controller('StudentOngoingCourseModalController', StudentOngoingCourseModalController);

function StudentOngoingCourseModalController($controller, $mdDialog, CourseFactory, course) {
    var self = this;
    $controller('CourseModalController', { self: self, course: course });

    self.title = 'Course';
    self.unregisterFromCourse = unregisterFromCourse;

    function unregisterFromCourse () {
        CourseFactory.unregisterSelf({ id: self.course.id }).$promise.then($mdDialog.hide);
    }
}