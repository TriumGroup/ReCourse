angular
    .module('app')
    .controller('StudentLessonModalController', StudentLessonModalController);

function StudentLessonModalController($mdDialog, lesson) {
    var self = this;

    if (lesson && lesson.startTime) {
        lesson.startTime = new Date(lesson.startTime);
    }

    self.lesson = lesson;
    self.cancel = cancel;

    function cancel() {
        $mdDialog.cancel();
    }
}