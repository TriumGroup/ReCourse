angular
    .module('app')
    .controller('StudentCourseRegistrationModalController', StudentCourseRegistrationModalController);

function StudentCourseRegistrationModalController($controller, $mdDialog, CourseFactory, course) {
    var self = this;
    $controller('CourseModalController', { self: self, course: course });

    self.title = 'Register on Course';
    self.registerOnCourse = registerOnCourse;

    function registerOnCourse() {
        CourseFactory.registerSelf({ id: self.course.id }).$promise.then($mdDialog.hide);
    }
}