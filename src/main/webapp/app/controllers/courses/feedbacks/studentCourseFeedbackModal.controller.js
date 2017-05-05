angular
    .module('app')
    .controller('StudentCourseFeedbackModalController', StudentCourseFeedbackModalController);

function StudentCourseFeedbackModalController($mdDialog, FeedbackFactory, courseId, $controller, AuthService) {
    var self = this;
    $controller('CourseFeedbackModalController', {self: self});

    self.courseId = courseId;
    self.saveFeedback = saveFeedback;

    function saveFeedback() {
        AuthService.prepareAuthInfo().then(function() {
            self.feedback.courseId = self.courseId;
            self.feedback.student = AuthService.user;
            FeedbackFactory.save(self.feedback, $mdDialog.hide);
        });
    }
}