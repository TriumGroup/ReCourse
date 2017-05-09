angular
    .module('app')
    .controller('AdminLessonListController', AdminLessonListController);

function AdminLessonListController($controller, $mdDialog, $stateParams, CourseFactory, LessonFactory, $state, DocumentDownloaderService) {
    var self = this;
    $controller('LessonListController', {self: self});
    self.lessons = [];
    self.title = 'Course lessons';
    self.isUpdatingChosen = false;

    self.addLesson = addLesson;
    self.deleteLesson = deleteLesson;
    self.editLesson = editLesson;
    self.showSolutions = showSolutions;
    self.courseId = $stateParams.course;
    self.downloadLessons = downloadLessons;

    refresh();

    function refresh() {
        CourseFactory.getLessons({id: self.courseId}).$promise.then(function (result) {
            self.lessons = result;
        });
    }


    function addLesson() {
        openEditModal();
    }

    function deleteLesson(lesson ) {
        var confirm = $mdDialog.confirm()
            .title('Would you like to delete this lesson from course?')
            .ok('Yes!')
            .cancel('No');

        $mdDialog.show(confirm).then(function () {
            LessonFactory.delete(lesson, refresh);
        }, function() {});
    }

    function editLesson (lesson) {
        openEditModal(lesson);
    }

    function showSolutions(lesson) {
        $state.go('lesson-solutions', { id: lesson.id });
    }

    function openEditModal(lesson) {
        $mdDialog.show({
            controller: 'AdminLessonModalController as self',
            templateUrl: 'templates/lessons/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                lesson: angular.copy(lesson),
                courseId: self.courseId
            }
        }).then(refresh, refresh);
    }

    function downloadLessons(type) {
        DocumentDownloaderService.downloadDocument('api/courses/' + self.courseId + '/lessons/export', type);
    }
}


