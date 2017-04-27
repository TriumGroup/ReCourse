angular
    .module('app')
    .controller('LessonSolutionListController', LessonSolutionListController);

function LessonSolutionListController($mdDialog, SolutionFactory, $stateParams) {
    var self = this;

    self.lessonId = $stateParams.id;
    self.solutions = [];
    self.isUpdatingChosen = false;

    self.addSolution = addSolution;
    self.deleteSolution = deleteSolution;
    self.editSolution = editSolution;

    refresh();

    function refresh() {
        SolutionFactory.getForLesson({ id: self.lessonId }).$promise.then(function (result) {
            self.solutions = result;
        });
    }

    function addSolution() {
        openModal();
    }

    function deleteSolution(solution) {
        SolutionFactory.delete({id: solution.id}, refresh);
    }

    function editSolution (solution) {
        openModal(solution);
    }

    function openModal(solution) {
        $mdDialog.show({
            controller: 'LessonSolutionModalController as self',
            templateUrl: 'templates/crud/lessons/solutions/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                solution: angular.copy(solution),
                lessonId: self.lessonId
            }
        }).then(refresh, refresh);
    }
}

