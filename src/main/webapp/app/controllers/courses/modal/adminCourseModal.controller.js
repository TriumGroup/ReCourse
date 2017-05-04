angular
    .module('app')
    .controller('AdminCourseModalController', AdminCourseModalController);

function AdminCourseModalController($controller, $mdDialog, CourseFactory, course) {
    var self = this;
    $controller('CourseModalController', { self: self });

    self.course = course;
    self.saveCourse = saveCourse;
    self.updateMode = !!self.course;

    if (self.updateMode){
        self.title = 'Update Course';
    } else {
        self.title = 'Create Course';
    }

    function saveCourse() {
        if (self.updateMode){
            CourseFactory.update(self.course, $mdDialog.hide);
        } else {
            CourseFactory.save(self.course, $mdDialog.hide);
        }
    }
}