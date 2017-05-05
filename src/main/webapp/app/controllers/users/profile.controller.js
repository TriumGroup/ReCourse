angular
    .module('app')
    .controller('ProfileController', ProfileController);

function ProfileController(UserFactory, AuthService) {
    var self = this;
    self.user = {};

    self.genders = ['MALE', 'FEMALE'];
    self.saveUser = saveUser;
    self.updatePassword = updatePassword;
    self.password = '';
    self.passwordConfirmation = '';

    refresh();

    function refresh() {
        AuthService.refreshUserInfo().then(function() {
            self.user = angular.copy(AuthService.user);
        });
    }

    function saveUser() {
        UserFactory.update(self.user, function(){
            refresh();
        });
    }

    function updatePassword() {
        UserFactory.updatePassword({password: self.password, passwordConfirmation: self.passwordConfirmation}, function() {
            self.password = '';
            self.passwordConfirmation = '';
        } );
    }
}