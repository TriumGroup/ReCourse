angular
    .module('app')
    .controller('CourseFeedbackModalController', CourseFeedbackModalController);

function CourseFeedbackModalController($mdDialog, $state, self) {
    self.cancel = cancel;
    self.isAdminCourseFeedback = isAdminCourseFeedback;
    self.isStudentCourseFeedback = isStudentCourseFeedback;


    function cancel() {
        $mdDialog.cancel();
    }

    function isAdminCourseFeedback() {
        return $state.current.name === 'feedbacks';
    }

    function isStudentCourseFeedback() {
        return $state.current.name === 'student-my-courses';
    }
}