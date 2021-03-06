angular
    .module('app')
    .controller('CourseFeedbackListController', CourseFeedbackListController);

function CourseFeedbackListController($mdDialog, CourseFactory, FeedbackFactory, $stateParams, DocumentDownloaderService) {
    var self = this;

    self.title = 'Course Feedbacks';
    self.courseId = $stateParams.course;
    self.feedbacks = [];
    self.isUpdatingChosen = false;
    self.pagination = { page: 1, limit: 7 };

    self.addFeedback = addFeedback;
    self.deleteFeedback = deleteFeedback;
    self.editFeedback = editFeedback;
    self.downloadFeedbacks = downloadFeedbacks;

    refresh();

    function refresh() {
        CourseFactory.getFeedbacks({ id: self.courseId }).$promise.then(function (result) {
            self.feedbacks = result;
        });
    }

    function addFeedback() {
        openModal();
    }

    function deleteFeedback(feedback) {
        var confirm = $mdDialog.confirm()
            .title('Would you like to delete this feedback?')
            .ok('Yes!')
            .cancel('No');

        $mdDialog.show(confirm).then(function () {
            FeedbackFactory.delete({ id: feedback.id }, refresh);
        }, function() {});
    }

    function editFeedback(feedback) {
        openModal(feedback);
    }

    function openModal(feedback) {
        $mdDialog.show({
            controller: 'AdminCourseFeedbackModalController as self',
            templateUrl: 'templates/feedbacks/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                feedback: angular.copy(feedback),
                courseId: self.courseId
            }
        }).then(refresh, refresh);
    }

    function downloadFeedbacks(type) {
        DocumentDownloaderService.downloadDocument('api/courses/' + self.courseId + '/feedbacks/export', type);
    }
}


