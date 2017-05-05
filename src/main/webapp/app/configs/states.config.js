angular
    .module('app')
    .config(AppStates);

function AppStates($stateProvider, $urlRouterProvider) {
    [
        { name: 'about', url: '/about', templateUrl: 'templates/about.html' },
        {
            name: 'root',
            url: '/',
            controller: 'RootController'
        },
        {
            name: 'signIn',
            url: '/sign_in',
            controller: 'SignInController as self',
            templateUrl: 'templates/sign_in.html'
        },
        {
            name: 'signUp',
            url: '/sign_up',
            controller: 'SignUpController as self',
            templateUrl: 'templates/sign_up.html'
        },
        {
            name: 'users',
            url: '/users',
            controller: 'AdminUserListController as self',
            templateUrl: 'templates/users/index.html'
        },

        {
            name: 'course-users',
            url: '/courses/:id/users',
            controller: 'CourseUserListController as self',
            templateUrl: 'templates/users/index.html'
        },
        {
            name: 'admin-courses',
            url: '/courses',
            controller: 'AdminCourseListController as self',
            templateUrl: 'templates/courses/index.html'
        },
        {
            name: 'feedbacks',
            url: '/feedbacks?course',
            controller: 'CourseFeedbackListController as self',
            templateUrl: 'templates/feedbacks/index.html'
        },
        {
            name: 'course-lessons',
            url: '/lessons?course',
            controller: 'AdminLessonListController as self',
            templateUrl: 'templates/lessons/index.html'
        },
        {
            name: 'lesson-solutions',
            url: '/lessons/:id/solutions',
            controller: 'AdminSolutionListController as self',
            templateUrl: 'templates/solutions/index.html'
        },
        {
            name: 'teacher-solutions',
            url: '/teacher/lessons/:id/solutions',
            controller: 'TeacherSolutionListController as self',
            templateUrl: 'templates/solutions/index.html'
        },
        {
            name: 'teacher-lessons',
            url: '/teacher/lessons',
            views: {
                '': { templateUrl: 'templates/lessons/lessons.future.past.html'},
                'future@teacher-lessons':{
                    controller: 'TeacherFutureLessonListController as self',
                    templateUrl: 'templates/lessons/index.html'
                },
                'past@teacher-lessons': {
                    controller: 'TeacherPastLessonListController as self',
                    templateUrl: 'templates/lessons/index.html'
                }
            }
        },
        {
            name: 'student-available-courses',
            url: '/student/available_courses',
            controller: 'StudentAvailableCourseListController as self',
            templateUrl: 'templates/courses/index.html'
        },
        {
            name: 'student-my-courses',
            url: '/student/courses',
            controller: 'StudentMyCoursesListController as self',
            templateUrl: 'templates/courses/index.html'
        },
        {
            name: 'student-lessons',
            url: '/student/lessons',
            views: {
                '': { templateUrl: 'templates/lessons/lessons.future.past.html' },
                'future@student-lessons': {
                    controller: 'StudentFutureLessonListController as self',
                    templateUrl: 'templates/lessons/index.html'
                },
                'past@student-lessons': {
                    controller: 'StudentPastLessonListController as self',
                    templateUrl: 'templates/lessons/index.html'
                }
            }
        },
        {
            name: 'profile',
            url: '/profile',
            controller: 'ProfileController as self',
            templateUrl: 'templates/users/profile.html'
        },
        { name: 'otherwise', url: '/otherwise', template: '<h1>404</h1>' }
    ].forEach(function(state) { $stateProvider.state(state) });

    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise('/otherwise');
}
