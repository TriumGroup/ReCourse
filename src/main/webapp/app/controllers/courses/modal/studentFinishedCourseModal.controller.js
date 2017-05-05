angular
    .module('app')
    .controller('StudentFinishedCourseModalController', StudentFinishedCourseModalController);

function StudentFinishedCourseModalController($controller, $mdDialog, FeedbackFactory, course) {
    var self = this;
    $controller('CourseModalController', { self: self, course: course });

    self.title = 'Finished course';
    self.addFeedback = addFeedback;

    function addFeedback () {
        FeedbackFactory.add({ id: self.course.id }, $mdDialog.hide);
    }
}