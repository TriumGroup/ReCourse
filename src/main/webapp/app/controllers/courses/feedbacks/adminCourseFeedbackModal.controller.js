angular
    .module('app')
    .controller('AdminCourseFeedbackModalController', AdminCourseFeedbackModalController);

function AdminCourseFeedbackModalController($mdDialog, FeedbackFactory, UserFactory, feedback, courseId, $controller) {
    var self = this;
    $controller('CourseFeedbackModalController', {self: self});

    self.courseId = courseId;
    self.course = null;
    self.feedback = feedback;
    self.saveFeedback = saveFeedback;
    self.updateMode = !!self.feedback;

    UserFactory.query().$promise.then(function (result) {
        self.students = result.filter(function (user) { return user.role === 'STUDENT' });
    });

    function saveFeedback() {
        self.feedback.courseId = self.courseId;
        if (self.updateMode){
            FeedbackFactory.update(self.feedback, $mdDialog.hide);
        } else {
            FeedbackFactory.save(self.feedback, $mdDialog.hide);
        }
    }
}