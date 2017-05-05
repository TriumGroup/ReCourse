angular
    .module('app')
    .controller('StudentCourseLessonsListController', StudentCourseLessonsListController);

function StudentCourseLessonsListController($controller, $stateParams, CourseFactory) {
    var self = this;
    $controller('StudentLessonListController', { self: self });

    self.lessons = [];
    self.title = 'Course lessons';
    self.courseId = $stateParams.course;

    refresh();

    function refresh() {
        CourseFactory.getLessons({id: self.courseId}).$promise.then(function (result) {
            self.lessons = result;
        });
    }
}


