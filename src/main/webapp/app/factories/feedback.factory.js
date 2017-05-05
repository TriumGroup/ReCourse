angular
    .module('app')
    .factory('FeedbackFactory', FeedbackFactory);

function FeedbackFactory($resource) {
    return $resource('api/courses/feedbacks/:id', { id: '@id' }, {
        update: { method: 'PUT' } ,
        getForStudentAndCourse: {
            method: 'GET',
            url: 'api/courses/feedbacks/course/:courseId/student/:studentId'
        }
    });
}
