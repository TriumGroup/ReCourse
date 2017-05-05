angular
    .module('app')
    .controller('NavigationController', NavigationController);

function NavigationController(AuthService) {
    var self = this;

    self.isAuthorized = isAuthorized;
    self.signOut = signOut;
    self.getRole = getRole;
    self.getUserId = getUserId;
    self.getUserName = getUserName;

    function isAuthorized() {
        return AuthService.isAuthorized;
    }

    function getRole() {
        return AuthService.role;
    }

    function getUserId() {
        return AuthService.user.id;
    }

    function signOut() {
        AuthService.signOut();
    }

    function getUserName() {
        return AuthService.user.name + ' ' + AuthService.user.surname;
    }
}
